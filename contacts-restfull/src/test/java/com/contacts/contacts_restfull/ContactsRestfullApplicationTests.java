package com.contacts.contacts_restfull;

import com.contacts.contacts_restfull.repository.ContactsRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

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

		// ResponseEntity<Contact> result = testRestTemplate.getForEntity("/1",
		// Contact.class);

		for (int i = 1; i < contacts.length + 2; i++) {
			if (i < contacts.length + 1) {
				MvcResult result = mockMvc.perform(get("/" + i)).andReturn();

				assertEquals(HttpStatus.valueOf(200).value(), result.getResponse().getStatus());
			} else {
				MvcResult result4 = mockMvc.perform(get("/4")).andReturn();

				assertEquals(HttpStatus.NOT_FOUND.value(),
						result4.getResponse().getStatus());
			}

		}
		// MvcResult result = mockMvc.perform(get("/1")).andReturn();

		// assertEquals(HttpStatus.valueOf(200).value(),
		// result.getResponse().getStatus());

		// MvcResult result2 = mockMvc.perform(get("/2")).andReturn();

		// assertEquals(HttpStatus.OK.value(), result2.getResponse().getStatus());

		// MvcResult result3 = mockMvc.perform(get("/3")).andReturn();

		// assertEquals(HttpStatus.OK.value(), result3.getResponse().getStatus());

		// MvcResult result4 = mockMvc.perform(get("/4")).andReturn();

		// assertEquals(HttpStatus.NOT_FOUND.value(),
		// result4.getResponse().getStatus());
	}

	@Test
	public void getAllContactsTest() throws Exception {
		ResultActions mockGetRequest = mockMvc.perform(get("/"));
		MvcResult result = mockGetRequest.andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

		mockMvc.perform(get("/")).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	public void validContactCreation() throws Exception {
		String requestBody = "{\"name\":\"x\",\"phoneNumber\":\"x\"}";

		ResultActions mockPostRequest = mockMvc
				.perform(post("/contact").content(requestBody).contentType(MediaType.APPLICATION_JSON));

		assertEquals(HttpStatus.CREATED.value(), mockPostRequest.andReturn().getResponse().getStatus());

		mockPostRequest.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void invalidContactCreation() throws Exception {
		String requestBody = "{\"name\":\"\",\"phoneNumber\":\"\"}";

		ResultActions mockPostRequest = mockMvc
				.perform(post("/contact").content(requestBody).contentType(MediaType.APPLICATION_JSON));

		assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), mockPostRequest.andReturn().getResponse().getStatus());
	}

	@Test
	public void contactNotFoundTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/0")).andReturn();

		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
	}

}

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
