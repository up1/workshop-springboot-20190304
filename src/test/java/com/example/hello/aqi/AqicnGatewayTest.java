package com.example.hello.aqi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
        
        given(restTemplate.getForObject("http://localhost/"+target+"/?token=xxxx", AqiResponse.class))
                .willReturn(expectedResponse);

        Optional<AqiResponse> actualResponse = gateway.fetchData(target);

        assertEquals(actualResponse, Optional.of(expectedResponse));
    }
	
	@Test
    public void shouldReturnEmptyWherServiceIsUnavailable() throws Exception {
		gateway = new AqicnGateway(restTemplate, "http://localhost", "xxxx");
		
		String target = "thailand/pathum-thani/bangkok-university-rangsit-campus";
        given(restTemplate.getForObject("http://localhost/"+target+"/?token=xxxx", AqiResponse.class))
            .willThrow(new RestClientException("something went wrong"));

        Optional<AqiResponse> actualResponse = gateway.fetchData(target);

        assertEquals(actualResponse, Optional.empty());
    }


}
