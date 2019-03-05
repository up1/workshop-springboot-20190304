package com.example.hello.random;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.hello.random.NumberControllerResponse;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NumberControllerTest {
	
	@MockBean
	private MyRandom stubRandom;
	
	@Autowired
	private TestRestTemplate rest;

	@Test
	public void success() {
		NumberControllerResponse expected 
		    = new NumberControllerResponse("5555");
		
		// Stub
		given(stubRandom.nextInt(10)).willReturn(5555);
		
		// Call API
		NumberControllerResponse actual =
		 rest.getForObject("/number", NumberControllerResponse.class);
		
		assertEquals("5555", actual.getValue());
		assertEquals(expected, actual);
	}

}
