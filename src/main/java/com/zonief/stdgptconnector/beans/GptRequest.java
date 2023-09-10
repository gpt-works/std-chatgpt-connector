package com.zonief.stdgptconnector.beans;

import com.zonief.gptconnectorcommons.beans.gpt.MessageGpt;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class GptRequest implements Serializable {

  private String model;
  private List<MessageGpt> messages;
  private Double temperature;
}
