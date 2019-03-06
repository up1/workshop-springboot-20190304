package com.example.hello.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.hello.api.AqiResponse;
import com.example.hello.api.AqicnGateway;
import com.example.hello.api.Data;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AqicnGatewayTest {
	
	@Mock
    private RestTemplate restTemplate;
	
	private AqicnGateway gateway;
	
	@Test
    public void shouldCallService() throws Exception {
		gateway = new AqicnGateway(restTemplate, "http://localhost", "xxxx");
		
		String target = "thailand/pathum-thani/bangkok-university-rangsit-campus";
        AqiResponse expectedResponse = new AqiResponse(new Data(1));
        
        String targetUrl = "http://localhost/"+ target +"/?token=xxxx";
		given(restTemplate.getForObject(targetUrl, AqiResponse.class))
                .willReturn(expectedResponse);

        Optional<AqiResponse> actualResponse = gateway.fetchData(target);

        assertEquals(actualResponse, Optional.of(expectedResponse));
    }
	
	@Test
    public void shouldReturnEmptyWherServiceIsUnavailable() throws Exception {
		gateway = new AqicnGateway(restTemplate, "http://localhost", "xxxx");
		
		String target = "thailand/pathum-thani/bangkok-university-rangsit-campus";
        String targetUrl = "http://localhost/"+ target +"/?token=xxxx";
		given(restTemplate.getForObject(targetUrl, AqiResponse.class))
            .willThrow(new RestClientException("something went wrong"));

        Optional<AqiResponse> actualResponse = gateway.fetchData(target);

        assertEquals(actualResponse, Optional.empty());
    }


}
