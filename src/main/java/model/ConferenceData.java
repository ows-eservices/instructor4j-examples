package main.java.model;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import solutions.own.instructor4j.annotation.Description;

public class ConferenceData {

    @Description("The list of conference participants identified with name, email and twitter")
    @NotNull
    private List<ConferenceParticipant> conferenceParticipants;

    @Description("The date of the conference")
    @NotNull
    private String date;

    @Description("The location of the conference")
    @NotNull
    private String location;

    @Description("The budget of the conference")
    @NotNull
    private Double budget;

    @Description("The deadline for writing the article")
    @NotNull
    private String deadline;

    public ConferenceData() {}

    public ConferenceData(List<ConferenceParticipant> conferenceParticipants, String date,
        String location, Double budget, String deadline) {
        this.conferenceParticipants = conferenceParticipants;
        this.date = date;
        this.location = location;
        this.budget = budget;
        this.deadline = deadline;
    }

    public List<ConferenceParticipant> getConferenceParticipants() {
        return conferenceParticipants;
    }

    public void setConferenceParticipants(List<ConferenceParticipant> conferenceParticipants) {
        this.conferenceParticipants = conferenceParticipants;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "ConferenceData{" +
            "conferenceParticipants=" + conferenceParticipants +
            ", date='" + date + '\'' +
            ", location='" + location + '\'' +
            ", budget=" + budget +
            ", deadline='" + deadline + '\'' +
            '}';
    }
}
