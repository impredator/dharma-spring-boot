package com.dharma.demo.dao;

import com.dharma.demo.model.Feedback;

public interface FeedbackDAL {

    Feedback addNewFeedback(Feedback feedback);

    Object getAllFeedbackSelections(String feedbackId);
}
