package com.assignment.rewards.program.controller;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.assignment.rewards.program.Application;
import com.assignment.rewards.program.domain.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RewardsProgramAssignmentControllerTest {

	private MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void calculateRewardPoints() throws Exception {
		String uri = "/customerRewardPoints";

		String inputJson = getRequestAsJson();
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();

		ObjectMapper objectMapper = new ObjectMapper();
		List<Customer> customers = objectMapper.readValue(content, new TypeReference<List<Customer>>() {
		});
		assertEquals("Soumya", customers.get(0).getName());
	}

	private String getRequestAsJson() {
		StringBuilder builder = new StringBuilder();
		try (FileInputStream fileInputStream = new FileInputStream(new File("src/test/config/request.json"));
				BufferedReader buf = new BufferedReader(new InputStreamReader(fileInputStream));) {
			String line = buf.readLine();
			while (line != null) {
				builder.append(line);
				line = buf.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

}
