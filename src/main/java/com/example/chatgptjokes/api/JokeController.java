package com.example.chatgptjokes.api;

import com.example.chatgptjokes.dtos.MyResponse;
import com.example.chatgptjokes.service.OpenAiService;
import org.springframework.web.bind.annotation.*;

/**
 * This class handles fetching a joke via the ChatGPT API
 */
@RestController
@RequestMapping("/api/v1/joke")
@CrossOrigin(origins = "*")
public class JokeController {

  private final OpenAiService service;

  /**
   * This contains the message to the ChatGPT API, telling the AI how it should act in regard to the requests it gets.
   */
  final static String SYSTEM_MESSAGE = """
                          You are a helpful assistant that provides help for people going on vacation.
                            You should be friendly and helpful, and provide useful information to the user.
                            You should provide information that is relevant to the user's questions and help them with their vacation plans.
                            If the user asks questions not related to vacations or travelling, you should politely guide them back to the main topic.
                          """;

  /**
   * The controller called from the browser client.
   * @param service
   */
  public JokeController(OpenAiService service) {
    this.service = service;
  }

  /**
   * Handles the request from the browser client.
   * @param about contains the input that ChatGPT uses to make a joke about.
   * @return the response from ChatGPT.
   */
  @GetMapping
  public MyResponse getJoke(@RequestParam String about) {

    return service.makeRequest(about,SYSTEM_MESSAGE);
  }
}
