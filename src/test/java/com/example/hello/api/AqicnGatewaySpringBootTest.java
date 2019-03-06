package com.example.hello.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import com.example.hello.api.AqiResponse;
import com.example.hello.api.AqicnGateway;
import com.example.hello.api.Data;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AqicnGatewaySpringBootTest { 
	
	@Autowired
	private AqicnGateway gateway;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8888);
	
    @Test
    public void shouldCallService() throws Exception {
    	String target = "thailand/pathum-thani/bangkok-university-rangsit-campus";
        
        wireMockRule.stubFor(get(urlPathEqualTo("/"+target+"/"))
        		.withQueryParam("token", equalTo("xxxx"))
                .willReturn(aResponse()
                        .withBody(read("classpath:apiResponse.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        Optional<AqiResponse> response = gateway.fetchData(target);

        Data data = new Data(80);
		Optional<AqiResponse> expectedResponse = Optional.of(new AqiResponse(data));
        assertEquals(expectedResponse, response);
    }
    
    public static String read(String filePath) 
    		throws IOException {
        File file = ResourceUtils.getFile(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }


}
