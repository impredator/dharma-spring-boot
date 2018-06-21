package com.dharma.demo.dao;

import com.dharma.demo.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.security.Key;
import java.util.List;

@Repository
public class FeedbackDALImpl implements FeedbackDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Feedback getFeedbackObject(String feedbackId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(feedbackId));
        return mongoTemplate.findOne(query, Feedback.class);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return mongoTemplate.findAll(Feedback.class);
    }

    @Override
    public Feedback getFeedbackById(String feedbackId) {
        return getFeedbackObject(feedbackId);
    }

    @Override
    public Feedback addNewFeedback(Feedback feedback) {
        mongoTemplate.save(feedback);
        return feedback;
    }

    @Override
    public Object getAllFeedbackSelections(String feedbackId) {
        Feedback feedback = getFeedbackObject(feedbackId);
        return feedback != null ? feedback.getSelections() : "Feedback not found";
    }

    @Override
    public String addFeedbackSelections(String feedbackId, String key, String value) {
        Feedback feedback = getFeedbackObject(feedbackId);
        if(feedback != null) {
            feedback.getSelections().put(key, value);
            mongoTemplate.save(feedback);
            return "Selection add done";
        } else {
            return "Feedback not found";
        }
    }

    @Override
    public String feedbackStarAnalysis() {
        final String map = "function() {emit(this.star, 1); }";
        final String reduce = "function(name, count) { return Array.sum(count);}";
        MapReduceResults<KeyValuePair> results = mongoTemplate.mapReduce("feedback", map, reduce, KeyValuePair.class);

        StringBuilder stats = new StringBuilder();
        for(KeyValuePair result: results) {
            stats.append(result.toString());
        }
        return stats.toString();
    }


    public static class KeyValuePair implements Comparable<KeyValuePair> {
        String id;
        Long value;

        public KeyValuePair(String id, Long value) {
            this.id = id;
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Star: " + id + " Count: " + value + "; ";
        }

        @Override
        public int compareTo(KeyValuePair o) {
            if (this.value < o.value) {
                return 1;
            } else if (this.value > o.value) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}
