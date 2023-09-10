package com.zonief.stdgptconnector.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class GptProperties {

  @Value("${gpt.authorization}")
  private String authorization;
  @Value("${gpt.bearer}")
  private String bearer;
  @Value("${gpt.apikey}")
  private String apikey;
  @Value("${gpt.model}")
  private String model;
  @Value("${gpt.max-tokens}")
  private Integer maxTokens;
  @Value("${gpt.temperature}")
  private Double temperature;
  @Value("${gpt.top-p}")
  private Double topP;
  @Value("${gpt.media-type}")
  private String mediaType;
  @Value("${gpt.url}")
  private String url;
  @Value("${gpt.organization}")
  private String organization;
  @Value("${gpt.organization-value}")
  private String organizationValue;

}
