package com.zonief.stdgptconnector.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class WebClientConfig {

  @Value("${webclient.gpt.authorization}")
  private String authorization;

  @Value("${webclient.gpt.bearer}")
  private String bearer;

  @Value("${webclient.gpt.apikey}")
  private String apikey;

  @Value("${webclient.gpt.model}")
  private String model;

  @Value("${webclient.gpt.max-tokens}")
  private Integer maxTokens;

  @Value("${webclient.gpt.temperature}")
  private Double temperature;

  @Value("${webclient.gpt.top-p}")
  private Double topP;

  @Value("${webclient.gpt.media-type}")
  private String mediaType;

  @Value("${webclient.gpt.url}")
  private String url;

  @Value("${webclient.gpt.organization}")
  private String organization;

  @Value("${webclient.gpt.organization-value}")
  private String organizationValue;

  @Bean(name = "gptWebClient")
  public WebClient gptWebClient() {
    final int size = 16 * 1024 * 1024;
    final ExchangeStrategies strategies =
        ExchangeStrategies.builder()
            .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
            .build();
    return WebClient.builder()
        .defaultCookie("cookieKey", "cookieValue")
        .defaultHeaders(httpHeaders -> httpHeaders.addAll(createHeaders()))
        .baseUrl(url)
        .exchangeStrategies(strategies)
        .build();
  }

  private HttpHeaders createHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(authorization, bearer + " " + apikey);
    headers.add(organization, organizationValue);
    headers.add(HttpHeaders.CONTENT_TYPE, mediaType);
    return headers;
  }
}
