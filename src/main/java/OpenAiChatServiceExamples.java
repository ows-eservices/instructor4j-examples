package main.java;

import solutions.own.instructor4j.exception.InstructorException;
import solutions.own.instructor4j.service.AiChatService;
import solutions.own.instructor4j.service.impl.OpenAiChatService;
import solutions.own.instructor4j.Instructor;

import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.List;

public class OpenAiChatServiceExamples {

    static class Simple_User_Query {

        public static void main(String[] args) {
            String apiKey = ApiKeys.OPENAI_API_KEY;
            AiChatService openAiService = new OpenAiChatService(apiKey);
            Instructor instructor = new Instructor(openAiService, 3);

            List<ChatMessage> messages = List.of(
                new ChatMessage("user", "Nenad Alajbegovic is 30 years old")
            );

            try {
                User user = instructor.createChatCompletion(messages, "gpt-3.5-turbo", User.class);
                System.out.println(user);
            } catch (InstructorException e) {
                e.printStackTrace();
            }
        }
    }

    static class Complex_User_Query {

        public static void main(String[] args) {
            String apiKey = ApiKeys.OPENAI_API_KEY;
            AiChatService openAiService = new OpenAiChatService(apiKey);
            Instructor instructor = new Instructor(openAiService, 3);

            List<ChatMessage> messages = List.of(
                new ChatMessage("user", "Nenad Alajbegovic will be 31 years old at this time next year")
            );

            try {
                User user = instructor.createChatCompletion(messages, "gpt-3.5-turbo", User.class);
                System.out.println(user);
            } catch (InstructorException e) {
                e.printStackTrace();
            }
        }
    }

    static class Simple_Product_Query {

        public static void main(String[] args) {
            String apiKey = ApiKeys.OPENAI_API_KEY;
            AiChatService openAiService = new OpenAiChatService(apiKey);
            Instructor instructor = new Instructor(openAiService, 3);

            List<ChatMessage> messages = List.of(
                new ChatMessage("user", "Create a product with id P123, name 'Laptop', and price 999.99")
            );

            try {
                Product product = instructor.createChatCompletion(messages, "gpt-3.5-turbo", Product.class);
                System.out.println(product);
            } catch (InstructorException e) {
                e.printStackTrace();
            }
        }
    }
}