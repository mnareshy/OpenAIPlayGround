package com.ap.samples;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class TripPlanner {


    static String SYSTEM_TASK_MESSAGE = "You are an API server that responds in a JSON format. Don't say anything else. Respond only with the JSON.\n" +
            "\n" +
            "The user will provide you with a city name and available budget. While considering that budget, you must suggest a list of places to visit.\n" +
            "\n" +
            "Allocate 30% of the budget to restaurants and bars. Allocate another 30% to shows, amusement parks, and other sightseeing. Dedicate the remainder of the budget to shopping. Remember, the user must spend 90-100% of the budget.\n" +
            "\n" +
            "Respond in a JSON format, including an array named 'places'. Each item of the array is another JSON object that includes 'place_name' as a text, 'place_short_info' as a text, and 'place_visit_cost' as a number.\n" +
            "\n" +
            "Don't add anything else after you respond with the JSON.";

    static String apiKey = "sairam";
    static long apiTimeout = 4;
    static OpenAiService openAiService;
    static
    {
        openAiService = new OpenAiService(
                apiKey, Duration.ofSeconds(apiTimeout));
    }

    public static void main(String[] args) {

      /*  Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        String city = sc.nextLine();
        String budget = sc.nextLine();*/

        String key = "xxx";
        String city = "Mysore";
        int budget = 1000;

        String model = "gpt-3.5-turbo";


        String apiKey = key;
        long apiTimeout = 4;
        double tempatature = 0.8;

       /* OpenAiService openAiService = new OpenAiService(
                apiKey, Duration.ofSeconds(apiTimeout));*/

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model(model)
                .temperature(tempatature)
                .messages(List.of(new ChatMessage("system", SYSTEM_TASK_MESSAGE),
                        new ChatMessage("user", String.format("I want to visit %s and have a budget of %d rupees", city, budget))))
                .build();


        StringBuilder builder = new StringBuilder();
        openAiService.createChatCompletion(chatCompletionRequest)
                .getChoices().forEach(choice -> {
                    builder.append(choice.getMessage().getContent());
                });

        String jsonResponse = builder.toString();

        System.out.println(jsonResponse);


    }
}
