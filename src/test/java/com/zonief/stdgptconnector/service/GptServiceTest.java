package com.zonief.stdgptconnector.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zonief.gptconnectorcommons.beans.gpt.Choice;
import com.zonief.gptconnectorcommons.beans.gpt.GptResponse;
import com.zonief.gptconnectorcommons.beans.gpt.MessageGpt;
import com.zonief.gptconnectorcommons.beans.gpt.enums.GptRole;
import com.zonief.stdgptconnector.config.WebClientConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class GptServiceTest {

  @Autowired GptService gptService;
  static MockWebServer mockBackEnd;

  @Autowired ObjectMapper objectMapper;
  @Autowired WebClientConfig webClientConfig;

  @Test
  void testAskQuestions() throws JsonProcessingException {

    mockBackEnd.enqueue(
        new MockResponse()
            .setBody(
                objectMapper.writeValueAsString(
                    GptResponse.builder()
                        .choices(
                            Collections.singletonList(
                                Choice.builder()
                                    .message(
                                        MessageGpt.builder()
                                            .content("test")
                                            .role(GptRole.ASSISTANT.getValue())
                                            .build())
                                    .build()))
                        .build())));
    Mono<GptResponse> response = gptService.askQuestions(new ArrayList<>());
    StepVerifier.create(response).expectNextCount(0).verifyComplete();
  }

  @BeforeEach
  void initialize() {
    String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
    webClientConfig.gptWebClient().mutate().baseUrl(baseUrl).build();
  }

  @BeforeAll
  static void setUp() throws IOException {
    mockBackEnd = new MockWebServer();
    mockBackEnd.start();
  }

  @AfterAll
  static void tearDown() throws IOException {
    mockBackEnd.shutdown();
  }
}
