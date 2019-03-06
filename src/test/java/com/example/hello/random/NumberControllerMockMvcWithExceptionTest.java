package com.example.hello.random;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hello.random.NumberControllerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.BDDMockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(NumberController.class)
public class NumberControllerMockMvcWithExceptionTest {
	
	@MockBean
	private MyRandom stubRandom;
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void should_Throw_RandomException() throws Exception {
		ErrorMessage expected = new ErrorMessage("404", null);
		
		// Stub
		given(stubRandom.nextInt(10))
		   .willThrow(new MyRandomException());
		
		// Call API HTTP response code = 200
		String response = 
				this.mvc.perform(get("/number")) 
				.andExpect(status().isOk())
				.andReturn()
				.getResponse().getContentAsString();
		
		// Convert JSON message to Object
		ObjectMapper mapper = new ObjectMapper();
		ErrorMessage actual =mapper.readValue(response, 
									   ErrorMessage.class);
		
		// Assert
		assertEquals("404", actual.getCode());
		assertEquals(expected, actual);
	}

}
