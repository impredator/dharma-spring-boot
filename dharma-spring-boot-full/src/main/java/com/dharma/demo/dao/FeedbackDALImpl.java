package com.dharma.demo.dao;

import com.dharma.demo.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDALImpl implements FeedbackDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Feedback addNewFeedback(Feedback feedback) {
        mongoTemplate.save(feedback);
        return feedback;
    }

    @Override
    public Object getAllFeedbackSelections(String feedbackId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(feedbackId));
        Feedback feedback = mongoTemplate.findOne(query, Feedback.class);
        return feedback != null ? feedback.getSelections() : "Feedback not found";
    }

}
