package com.dharma.spring.mongodb.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Feedback {

    @Id
    private String id;
    private String comment;
    private Date creationDate = new Date();
    private Map<String, String> selections = new HashMap<>();

    public Feedback() {
    }

    public Feedback(String id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Feedback(String id, String comment, Map<String, String> selections) {
        this.id = id;
        this.comment = comment;
        this.selections = selections;
    }

    public Feedback(String id, String comment, Date creationDate, Map<String, String> selections) {
        this.id = id;
        this.comment = comment;
        this.creationDate = creationDate;
        this.selections = selections;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Map<String, String> getSelections() {
        return selections;
    }

    public void setSelections(Map<String, String> selections) {
        this.selections = selections;
    }
}
