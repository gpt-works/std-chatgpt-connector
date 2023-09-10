package com.zonief.stdgptconnector.service;

import com.zonief.gptconnectorcommons.beans.ConnectorRequest;
import com.zonief.gptconnectorcommons.beans.gpt.GptResponse;
import com.zonief.gptconnectorcommons.beans.gpt.MessageGpt;
import com.zonief.stdgptconnector.beans.GptRequest;
import com.zonief.stdgptconnector.config.GptProperties;
import java.util.ArrayList;
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
  private final GptProperties gptProperties;

  public Mono<GptResponse> askQuestions(List<ConnectorRequest> requests) {
    List<MessageGpt> messages = new ArrayList<>();
    for (ConnectorRequest request : requests) {
      messages.add(MessageGpt.builder().role("user").content(request.getQuestion()).build());
    }
    return callGpt(new GptRequest(gptProperties.getModel(), messages, gptProperties.getTemperature()));
  }

  private Mono<GptResponse> callGpt(GptRequest request) {
    log.debug("Calling GPT with request: {}", request);
    return gptWebClient.post().bodyValue(request).retrieve().bodyToMono(GptResponse.class);
  }
}
