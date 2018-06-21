package com.dharma.spring.mongodb.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.dharma.spring.mongodb.model.Feedback;

@Repository
public class FeedbackDALImpl implements FeedbackDAL {

    private final String COLLECTION_NAME = "feedback";

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

	@Override
    public String feedbackStarAnalysis() {
//        final String map = "function() {emit(this.star, 1);}";
//        final String reduce = "function(name, count) {return Array.sum(count);}";
//
//        MapReduceResults<KeyValuePair> results = mongoTemplate.mapReduce(COLLECTION_NAME, map, reduce, KeyValuePair.class);

        MapReduceOptions options = MapReduceOptions.options();
        options.outputCollection("countStar");
        options.outputTypeReduce();
        MapReduceResults<KeyValuePair> results = mongoTemplate.mapReduce(COLLECTION_NAME,  "classpath:map.js",
                "classpath:reduce.js", options, KeyValuePair.class);

        StringBuilder stats = new StringBuilder();
        for (KeyValuePair result : results) {
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
