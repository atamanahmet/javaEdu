package com.contacts.contacts_restfull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Request;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.contacts.contacts_restfull.controller.ContactsController;
import com.contacts.contacts_restfull.repository.ContactsRepository;
import com.contacts.contacts_restfull.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactsRestfullApplicationTests {
	// @Autowired
	// ContactService service;
	// @Autowired
	// ContactsRepository repository;
	// @Autowired
	// ContactsController controller;

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

}
