package com.ikarabulut.smartbatterystreamer.db;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BatteryEvent {
    private final String text;

    @JsonCreator
    public BatteryEvent(@JsonProperty("text") String text) {
        this.text = text;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }
}
