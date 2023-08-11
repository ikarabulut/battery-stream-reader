package com.ikarabulut.smartbatterystreamer.api;

import io.dropwizard.core.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.HashMap;
import java.util.Map;

public class SmartBatteryStreamerConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();
    private String deviceTable;

    @NotNull
    private String topic;
    @NotNull
    private Map<String, String> kafka = new HashMap<>();

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(String Topic) {
        topic = Topic;
    }

    @JsonProperty("kafka")
    public Map<String, String> getKafka(){
        return this.kafka;
    }

    @JsonProperty("kafka")
    public void setKafka(Map<String, String> kafka) {
        this.kafka = kafka;
    }

    @JsonProperty("deviceTable")
    public String getDeviceTable() {
        return deviceTable;
    }

    @JsonProperty("deviceTable")
    public void setDeviceTable(String deviceTable) {
        this.deviceTable = deviceTable;
    }
}
