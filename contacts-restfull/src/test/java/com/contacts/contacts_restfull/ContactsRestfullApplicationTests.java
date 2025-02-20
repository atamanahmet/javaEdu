package com.contacts.contacts_restfull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactsRestfullApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Test
	void getPostPutDeleteTest() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());
		// Verify HTTP 200 response

	}

	@Test
	void postRequestTest() throws Exception {
		Contact contact = new Contact();
		contact.setName("a");
		contact.setPhoneNumber("1");

		MvcResult result = mockMvc.perform(post("/contact").content(new ObjectMapper().writeValueAsString(contact))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();
		Contact responseContact = new ObjectMapper().readValue(result.getResponse().getContentAsString(),
				Contact.class);
		assertEquals(responseContact.getName(), contact.getName());
		assertEquals(responseContact.getPhoneNumber(), contact.getPhoneNumber());
	}

	@Test
	void putTest() throws Exception {
		Contact contact = new Contact();
		contact.setName("x");
		// MvcResult result =
		MvcResult result = mockMvc.perform(put("/contact/foo").content(new ObjectMapper().writeValueAsString(contact))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		Contact responseContact = new ObjectMapper().readValue(result.getResponse().getContentAsString(),
				Contact.class);

		System.err.println(responseContact);
		assertEquals(contact.getName(), responseContact.getName());
	}

	@Test
	void deleteTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();

		Contact[] responseContactArray = new ObjectMapper().readValue(result.getResponse().getContentAsString(),
				Contact[].class);

		assertEquals(responseContactArray[0].getName(), "a");

		mockMvc.perform(delete("/delete/id1")).andExpect(status().isNoContent());

		MvcResult result2 = mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();

		Contact[] responseContactArray2 = new ObjectMapper().readValue(result2.getResponse().getContentAsString(),
				Contact[].class);
		assertEquals(responseContactArray2[0].getName(), "b");

	}

}
