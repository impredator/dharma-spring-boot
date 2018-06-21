package com.dharma.demo.dao;

import com.dharma.demo.model.Feedback;

import java.util.List;

public interface FeedbackDAL {

    List<Feedback> getAllFeedbacks();

    Feedback getFeedbackById(String feedbackId);

    Feedback addNewFeedback(Feedback feedback);

    Object getAllFeedbackSelections(String feedbackId);

    String addFeedbackSelections(String feedbackId, String key, String value);

    String feedbackStarAnalysis();
}
