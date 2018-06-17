package com.dharma.spring.mongodb.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.dharma.spring.mongodb.model.Feedback;

@Repository
public class FeedbackDALImpl implements FeedbackDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Feedback> getAllFeedbacks() {
		return mongoTemplate.findAll(Feedback.class);
	}

	@Override
	public Feedback getFeedbackById(String feedbackId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(feedbackId));
		return mongoTemplate.findOne(query, Feedback.class);
	}

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
		return feedback != null ? feedback.getSelections() : "Feedback not found.";
	}

	@Override
	public String getFeedbackSelection(String feedbackId, String key) {
		Query query = new Query();
		query.fields().include("selections");
		query.addCriteria(Criteria.where("id").is(feedbackId).andOperator(Criteria.where("selections." + key).exists(true)));
		Feedback feedback = mongoTemplate.findOne(query, Feedback.class);
		return feedback != null ? feedback.getSelections().get(key) : "Selection not found.";
	}

	@Override
	public String addFeedbackSelections(String feedbackId, String key, String value) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(feedbackId));
		Feedback feedback = mongoTemplate.findOne(query, Feedback.class);
		if (feedback != null) {
			feedback.getSelections().put(key, value);
			mongoTemplate.save(feedback);
			return "Key added.";
		} else {
			return "Feedback not found.";
		}
	}
}
