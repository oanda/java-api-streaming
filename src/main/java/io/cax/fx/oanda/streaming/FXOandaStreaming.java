package io.cax.fx.oanda.streaming;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cax.fx.oanda.streaming.domain.Tick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Scanner;


@SpringBootApplication
public class FXOANDAStreaming implements CommandLineRunner{

    Logger logger = LoggerFactory.getLogger(FXOANDAStreaming.class);

    @Value("${oanda.access_token}")
    private String accessToken;

    @Value("${oanda.account_id}")
    private String accountId;


    @Value("${oanda.domain}")
    private String domain;

    @Value("${oanda.instruments}")
    private String instruments;

    private RestOperations restTemplate;

    public static void main (String[]args) throws IOException {
        SpringApplication.run(FXOANDAStreaming.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        restTemplate = new RestTemplate();


        UriComponents uriComponents = UriComponentsBuilder.fromUriString(domain).path("/v1/prices").queryParam("accountId",accountId).queryParam("instruments",instruments).build();

        restTemplate.execute(uriComponents.toUriString(),
                HttpMethod.GET,
                clientHttpRequest -> clientHttpRequest.getHeaders().add("Authorization","Bearer " + accessToken),
                clientHttpResponse -> {
                    try(Scanner scanner = new Scanner(clientHttpResponse.getBody(),"utf-8")){

                        while(scanner.hasNext()) formatPrintContent(scanner.nextLine());
                    }
                    return new ResponseEntity<>(HttpStatus.OK);
                });
    }

    private void formatPrintContent(String content){

        ObjectMapper mapper = new ObjectMapper();

        try {

            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            Tick tick = mapper.readValue(content,Tick.class);
            logger.info(mapper.writer(new DefaultPrettyPrinter()).writeValueAsString(tick));

        } catch (IOException e) {
            logger.debug("heartbeat not processed: " + e.getMessage());
        }


    }
}