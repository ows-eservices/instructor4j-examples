package main.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import main.java.config.ApiKeys;
import main.java.model.ConferenceParticipant;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import solutions.own.instructor4j.Instructor;
import solutions.own.instructor4j.model.BaseMessage;
import solutions.own.instructor4j.service.AiChatService;
import solutions.own.instructor4j.service.impl.OpenAiChatService;
import solutions.own.instructor4j.util.Utils;

public class OpenAiStreamChatExamples {

    static class Stream_Participant_Data_Query {

        private static final ObjectMapper objectMapper = new ObjectMapper();

        private final static String meetingRecord = "In our recent online meeting, participants from various backgrounds joined to discuss the upcoming tech conference. " +
            "The names and contact details of the participants were as follows:\n" +
            "\n" +
            "- Name: John Doe, Email: johndoe@email.com, Twitter: @TechGuru44\n" +
            "- Name: Jane Smith, Email: janesmith@email.com, Twitter: @DigitalDiva88\n" +
            "- Name: Alex Johnson, Email: alexj@email.com, Twitter: @CodeMaster2023\n" +
            "- Name: Emily Clark, Email: emilyc@email.com, Twitter: @InnovateQueen\n" +
            "- Name: Ron Stewart, Email: ronstewart@email.com, Twitter: @RoboticsRon5\n" +
            "- Name: Sarah Lee, Email: sarahlee@email.com, Twitter: @AI_Aficionado\n" +
            "- Name: Mike Brown, Email: mikeb@email.com, Twitter: @FutureTechLeader\n" +
            "- Name: Lisa Green, Email: lisag@email.com, Twitter: @CyberSavvy101\n" +
            "- Name: David Wilson, Email: davidw@email.com, Twitter: @GadgetGeek77\n" +
            "- Name: Daniel Kim, Email: danielk@email.com, Twitter: @DataDrivenDude";

        public static void main(String[] args) {
            String apiKey = ApiKeys.OPENAI_API_KEY;
            AiChatService openAiService = new OpenAiChatService(apiKey);
            Instructor instructor = new Instructor(openAiService, 3);

            StringBuilder fullResponseReceived = new StringBuilder();

            List<BaseMessage> messages = Collections.unmodifiableList(Arrays.asList(
                new BaseMessage(BaseMessage.Role.USER.getValue(), meetingRecord)
            ));

            Flux<String> extractionStream = instructor.createStreamChatCompletion(
                messages,
                "gpt-4o-mini",
                ConferenceParticipant.class
            );

            extractionStream
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(extraction -> {
                    if (extraction != null) {
                        fullResponseReceived.append(extraction);

                        // Let us assure that json received always have balanced quotes, curly braces, and square brackets
                        String consistentJson = Utils.ensureJsonClosures(fullResponseReceived.toString());

                        // Let's print the received objects as they arrive during the streaming process.
                        // This will give you a clear idea of the data volume being transmitted, and help you understand
                        // how we might efficiently send and display it in front end.
                        JsonNode rootNode;
                        try {
                            rootNode = objectMapper.readTree(consistentJson);
                            JsonNode dataNode = rootNode.get("data");
                            List<ConferenceParticipant> participants = objectMapper.convertValue(dataNode, new TypeReference<List<ConferenceParticipant>>() {});
                            if (participants != null) {
                                for (ConferenceParticipant p : participants) {
                                    System.out.println(
                                        "    PARTICIPANT DATA RECEIVED: " + p.getName() + ", " + p.getEmail() + ", "
                                            + p.getTwitter());
                                }
                                System.out.println("\n");
                            }
                        } catch (JsonProcessingException e) {
                            ; // perfectly fine
                        }
                    }
                })
                .doOnError(error -> {
                    System.out.println("Flux emitted an unexpected error: " + error.getMessage());
                })
                .doOnComplete(() -> {
                    try {
                        List<ConferenceParticipant> completedParticipants = Utils.getEntities(
                            fullResponseReceived.toString().replaceAll("\\n", ""),
                            ConferenceParticipant.class,
                            "data");

                        // Print out complete list of received participants
                        System.out.println("\nComplete list of received participants:\n");
                        for (ConferenceParticipant p : completedParticipants) {
                            System.out.println(
                                "    PARTICIPANT: " + p.getName() + ", " + p.getEmail() + ", "
                                    + p.getTwitter());
                        }

                    } catch (JsonProcessingException e) {
                        System.out.println("Not able to interpretate data: " + e.getMessage());
                    }
                })
                .blockLast();
        }
    }
}