package com.dharma.spring.mongodb.dal;

import java.util.List;

import com.dharma.spring.mongodb.model.Feedback;

public interface FeedbackDAL {

	List<Feedback> getAllFeedbacks();

	Feedback getFeedbackById(String feedbackId);

	Feedback addNewFeedback(Feedback feedback);

	Object getAllFeedbackSelections(String feedbackId);

	String getFeedbackSelection(String feedbackId, String key);

	String addFeedbackSelections(String feedbackId, String key, String value);

	String feedbackStarAnalysis();
}