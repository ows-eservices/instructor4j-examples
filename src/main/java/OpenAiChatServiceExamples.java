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
                User user = instructor.createChatCompletion(messages, "gpt-4o-mini", User.class);
                System.out.println(user);
            } catch (InstructorException e) {
                e.printStackTrace();
            }
        }
    }

    static class Advanced_User_Query {

        public static void main(String[] args) {
            String apiKey = ApiKeys.OPENAI_API_KEY;
            AiChatService openAiService = new OpenAiChatService(apiKey);
            Instructor instructor = new Instructor(openAiService, 3);

            List<ChatMessage> messages = List.of(
                new ChatMessage("user", "Nenad Alajbegovic will be 31 years old in 13 months")
            );

            try {
                User user = instructor.createChatCompletion(messages, "gpt-4o-mini", User.class);
                System.out.println(user);
            } catch (InstructorException e) {
                e.printStackTrace();
            }
        }
    }

    static class Product_Query {

        public static void main(String[] args) {
            String apiKey = ApiKeys.OPENAI_API_KEY;
            AiChatService openAiService = new OpenAiChatService(apiKey);
            Instructor instructor = new Instructor(openAiService, 3);

            List<ChatMessage> messages = List.of(
                new ChatMessage("user", "Create a product with id P123, name 'Laptop', and price 999.99")
            );

            try {
                Product product = instructor.createChatCompletion(messages, "gpt-4o-mini", Product.class);
                System.out.println(product);
            } catch (InstructorException e) {
                e.printStackTrace();
            }
        }
    }

    static class Conference_Data_Query {

        private static final String meetingRecord =
            "In our recent online meeting, participants from various backgrounds joined to discuss the upcoming tech conference. " +
                "The names and contact details of the participants were as follows:\n\n" +

                "- Name: John Doe, Email: johndoe@email.com, Twitter: @TechGuru44\n" +
                "- Name: Jane Smith, Email: janesmith@email.com, Twitter: @DigitalDiva88\n" +
                "- Name: Alex Johnson, Email: alexj@email.com, Twitter: @CodeMaster2023\n" +
                "- Name: Emily Clark, Email: emilyc@email.com, Twitter: @InnovateQueen\n" +
                "- Name: Ron Stewart, Email: ronstewart@email.com, Twitter: @RoboticsRon5\n" +
                "- Name: Sarah Lee, Email: sarahlee@email.com, Twitter: @AI_Aficionado\n" +
                "- Name: Mike Brown, Email: mikeb@email.com, Twitter: @FutureTechLeader\n" +
                "- Name: Lisa Green, Email: lisag@email.com, Twitter: @CyberSavvy101\n" +
                "- Name: David Wilson, Email: davidw@email.com, Twitter: @GadgetGeek77\n" +
                "- Name: Daniel Kim, Email: danielk@email.com, Twitter: @DataDrivenDude\n\n" +

                "During the meeting, we agreed on several key points. The conference will be held on March 15th, 2024, at the Grand Tech Arena " +
                "located at 4521 Innovation Drive. Dr. Emily Johnson, a renowned AI researcher, will be our keynote speaker.\n\n" +

                "The budget for the event is set at $50,000, covering venue costs, speaker fees, and promotional activities. Each participant " +
                "is expected to contribute an article to the conference blog by February 20th.\n\n" +

                "A follow-up meeting is scheduled for January 25th at 3 PM GMT to finalize the agenda and confirm the list of speakers.";

        public static void main(String[] args) {
            String apiKey = ApiKeys.OPENAI_API_KEY;
            AiChatService openAiService = new OpenAiChatService(apiKey);
            Instructor instructor = new Instructor(openAiService, 3);

            List<ChatMessage> messages = List.of(
                new ChatMessage("user", meetingRecord)
            );

            try {
                ConferenceData conferenceData = instructor.createChatCompletion(messages, "gpt-4o-mini",
                    ConferenceData.class);
                System.out.println(conferenceData);
            } catch (InstructorException e) {
                e.printStackTrace();
            }
        }
    }
}