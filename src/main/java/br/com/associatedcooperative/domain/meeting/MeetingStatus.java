package br.com.associatedcooperative.domain.meeting;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MeetingStatus {
    OPEN("OPEN"),
    CLOSED("CLOSED");

    private final String situation;
    MeetingStatus(String value) {
        this.situation = value;
    }
    @JsonValue
    public String getSituation() {
        return situation;
    }
}
