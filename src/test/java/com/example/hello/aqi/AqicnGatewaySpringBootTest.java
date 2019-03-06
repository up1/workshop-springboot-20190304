package com.example.hello.aqi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

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
    
    public static String read(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }


}
