package com.zonief.stdgptconnector.service;

import com.zonief.gptconnectorcommons.beans.gpt.GptResponse;
import com.zonief.gptconnectorcommons.beans.gpt.MessageGpt;
import com.zonief.stdgptconnector.beans.GptRequest;
import com.zonief.stdgptconnector.config.WebClientConfig;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class GptService {

  private final WebClient gptWebClient;
  private final WebClientConfig webClientConfig;

  public Mono<GptResponse> askQuestions(List<MessageGpt> requests) {
    return callGpt(
        new GptRequest(webClientConfig.getModel(), requests, webClientConfig.getTemperature()));
  }

  private Mono<GptResponse> callGpt(GptRequest request) {
    log.debug("Calling GPT with request: {}", request);
    return gptWebClient.post().bodyValue(request).retrieve().bodyToMono(GptResponse.class);
  }
}
