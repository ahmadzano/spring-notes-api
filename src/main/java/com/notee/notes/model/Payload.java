package com.notee.notes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class Payload {
    @JsonProperty(value = "userId", required = true)
    private int userId;
    @JsonProperty(value = "type", required = true)
    private String type;
    @JsonProperty(value = "content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
