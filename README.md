# GPT Connector API Documentation

## Overview

The GPT Connector API is a Spring Boot-based application that serves as a conduit for interacting
with GPT models. The API facilitates the ability to send messages/questions and receive responses by
communicating with the underlying logic wrapped around the GPT model interactions.

## API Endpoint

### Ask Questions

- Endpoint: /api/v1/gpt-connector/ask
- Method: POST
- Description: Sends a list of messages (questions) and receives respective responses from the GPT
  model.
- Request Body: An array of MessageGpt objects.
- Response: A Mono of GptResponse.

#### Example Request:

`[
{
"role": "user",
"content": "What is the capital of France?"
}
]`

## Model Definitions

### MessageGpt

#### Description

A serializable class to encapsulate messages that are to be communicated to or from the GPT model.

#### Properties
 - role (String): Specifies the role of the message.
 - content (String): Contains the actual textual content of the message.

#### Methods & Usage
 - Utilize MessageGptBuilder for object creation.
 - Getters (getRole(), getContent()) and setters (setRole(String), setContent(String)) for properties.
 - equals(Object), hashCode(), and toString() overridden for object comparison, hash code generation,
and string representation respectively.

#### Usage Example:
`MessageGpt message = MessageGpt.builder().role("user").content("What is the capital of France?").build();`

### GptRequest

#### Description

A serializable class that bundles a set of messages and configurations for interactions with the GPT
model.

#### Properties
 - model (String): Identifier or name of the GPT model to be used.
 - messages (List<MessageGpt>): A list of MessageGpt objects to be processed by the GPT model.
 - temperature (Double): Sampling temperature for controlling randomness in the model’s responses.

#### Usage Example
`GptRequest gptRequest = GptRequest.builder().model("gpt-3.5").messages(Collections.singletonList(new MessageGpt("user", "What is the capital of France?"))).temperature(0.7).build();`

## Usage
### Sending Questions to GPT Model
Utilize the /ask endpoint to submit questions and receive responses from the GPT model. The
questions must be encapsulated as MessageGpt objects and can be provided in a list.

### Utilizing Responses
The responses from the GPT model, encapsulated within GptResponse, should be parsed and utilized as
per the application requirement.

## Configuration Properties Breakdown
### Authentication & Authorization
 - authorization: Indicates the header field used for authorization, typically "Authorization".
 - bearer: Token type, which is typically "Bearer" for API requests.
 - **_apikey_**: The unique API key provided by OpenAI to authenticate the API requests.
 - **_organization-value_**: The unique organization key provided by OpenAI to authenticate the API requests.
### Model Specifications
 - model: Specifies the GPT model to be utilized for API requests, e.g., "gpt-3.5-turbo", "gpt-4".
 - max-tokens: Maximum number of tokens (words/characters) in the model response.
 - temperature: Determines the randomness in model’s response. Higher values make responses more random, whereas lower values make it more deterministic.
 - top-p: Top P sampling, a float value between 0 and 1, which controls the diversity of the response.
### API Endpoint & Headers
 - media-type: Indicates the type and character set of data being sent to the API, typically "application/json; charset=UTF-8".
 - url: The endpoint URL where the API requests are sent.

##### Note: 
The properties in bold are required for the API to function properly.
Not all properties are required for the API to function properly.
Not all properties are used in the program at this time.