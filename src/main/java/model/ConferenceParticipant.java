package main.java.model;

import jakarta.validation.constraints.NotNull;
import solutions.own.instructor4j.annotation.Description;

public class ConferenceParticipant {

    @Description("The name of the participant")
    @NotNull
    private String name;

    @Description("The email of the participant")
    @NotNull
    private String email;

    @Description("The twitter handle of the participant")
    @NotNull
    private String twitter;

    public ConferenceParticipant() {}

    public ConferenceParticipant(String name, String email, String twitter) {
        this.name = name;
        this.email = email;
        this.twitter = twitter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return "ConferenceParticipant{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", twitter='" + twitter + '\'' +
            '}';
    }
}
