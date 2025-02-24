package com.contacts.contacts_restfull;

import com.contacts.contacts_restfull.repository.ContactsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest
@AutoConfigureMockMvc
class ContactsRestfullApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ContactsRepository repository;

	// @Autowired
	// private TestRestTemplate testRestTemplate;

	private Contact[] contacts = new Contact[] {
			new Contact("1", "Jon Snow", "6135342524"),
			new Contact("2", "Tyrion Lannister", "4145433332"),
			new Contact("3", "The Hound", "3452125631"),
	};

	@BeforeEach
	void setup() {
		for (int i = 0; i < contacts.length; i++) {
			repository.addContact(contacts[i]);
		}
	}

	@AfterEach
	void clear() {
		repository.getContacts().clear();
	}

	@Test
	public void getContactByIdTest() throws Exception {

		// LoopResponseTest
		for (int i = 1; i < contacts.length + 5; i++) {
			if (i < contacts.length + 1) {
				mockMvc.perform(get("/" + i))
						.andExpect(status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			} else {
				mockMvc.perform(get("/" + i))
						.andExpect(status().isNotFound())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			}
		}

		// ResponseFieldCheck
		mockMvc.perform(get("/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("Jon Snow"));
	}

	@Test
	public void getAllContactsTest() throws Exception {

		List<Contact> list = Arrays.asList(new ObjectMapper().readValue(mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString(), Contact[].class));

		assertEquals(list.get(0).getName(), "Jon Snow");
	}

	@Test
	public void validContactCreation() throws Exception {
		String validRequestBody = "{\"name\":\"x\",\"phoneNumber\":\"x\"}";

		// ValidRequestBody
		MvcResult result = mockMvc.perform(post("/contact")
				.content(validRequestBody)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		// ValidResponseFieldCheck
		assertEquals("x",
				new ObjectMapper()
						.readValue(result.getResponse().getContentAsString(), Contact.class)
						.getName());
	}

	@Test
	public void invalidContactCreation() throws Exception {
		String invalidRequestBody = "{\"name\":\"\",\"phoneNumber\":\"\"}";

		// InvalidRequestBody
		mockMvc.perform(post("/contact")
				.content(invalidRequestBody)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void contactNotFoundTest() throws Exception {
		mockMvc.perform(get("/invalidId"))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

}

// Old
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import
// org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.test.web.servlet.MvcResult;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest
// @AutoConfigureMockMvc
// class ContactsRestfullApplicationTests {
// @Autowired
// MockMvc mockMvc;

// @Test
// void getPostPutDeleteTest() throws Exception {
// mockMvc.perform(get("/"))
// .andExpect(status().isOk());
// // Verify HTTP 200 response

// }

// @Test
// void postRequestTest() throws Exception {
// Contact contact = new Contact();
// contact.setName("a");
// contact.setPhoneNumber("1");

// MvcResult result = mockMvc.perform(post("/contact").content(new
// ObjectMapper().writeValueAsString(contact))
// .contentType(MediaType.APPLICATION_JSON))
// .andExpect(status().isCreated()).andReturn();
// Contact responseContact = new
// ObjectMapper().readValue(result.getResponse().getContentAsString(),
// Contact.class);
// assertEquals(responseContact.getName(), contact.getName());
// assertEquals(responseContact.getPhoneNumber(), contact.getPhoneNumber());
// }

// @Test
// void putTest() throws Exception {
// Contact contact = new Contact();
// contact.setName("x");
// // MvcResult result =
// MvcResult result = mockMvc.perform(put("/contact/foo").content(new
// ObjectMapper().writeValueAsString(contact))
// .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

// Contact responseContact = new
// ObjectMapper().readValue(result.getResponse().getContentAsString(),
// Contact.class);

// System.err.println(responseContact);
// assertEquals(contact.getName(), responseContact.getName());
// }

// @Test
// void deleteTest() throws Exception {
// MvcResult result =
// mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();

// Contact[] responseContactArray = new
// ObjectMapper().readValue(result.getResponse().getContentAsString(),
// Contact[].class);

// assertEquals(responseContactArray[0].getName(), "a");

// mockMvc.perform(delete("/delete/id1")).andExpect(status().isNoContent());

// MvcResult result2 =
// mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();

// Contact[] responseContactArray2 = new
// ObjectMapper().readValue(result2.getResponse().getContentAsString(),
// Contact[].class);
// assertEquals(responseContactArray2[0].getName(), "b");

// }

// }
