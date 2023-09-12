package com.zonief.stdgptconnector.controller;

import com.zonief.gptconnectorcommons.beans.gpt.GptResponse;
import com.zonief.gptconnectorcommons.beans.gpt.MessageGpt;
import com.zonief.stdgptconnector.service.GptService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/gpt-connector")
public class ConnectorController {

  private final GptService gptService;

  @PostMapping("/ask")
  public ResponseEntity<Mono<GptResponse>> askQuestions(@RequestBody List<MessageGpt> fullRequest) {
    return ResponseEntity.ok(gptService.askQuestions(fullRequest));
  }
}
