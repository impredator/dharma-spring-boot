package com.dharma.spring.mongodb.controller;

import java.util.List;

import com.dharma.spring.mongodb.dal.FeedbackDAL;
import com.dharma.spring.mongodb.dal.FeedbackRepository;
import com.dharma.spring.mongodb.model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final FeedbackRepository feedbackRepository;

    private final FeedbackDAL feedbackDAL;

    private Feedback getFeedbackWithDefault(String feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    public FeedbackController(FeedbackRepository feedbackRepository, FeedbackDAL feedbackDAL) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackDAL = feedbackDAL;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Feedback> getAllFeedbacks() {
        LOG.info("Getting all feedbacks.");
        return feedbackDAL.getAllFeedbacks();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Feedback> getAll() {
        LOG.info("Getting all feedbacks.");
        return feedbackRepository.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Feedback createNewFeedback(@RequestBody Feedback feedback) {
        LOG.info("Saving feedback.");
        return feedbackRepository.save(feedback);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Feedback addNewFeedback(@RequestBody Feedback feedback) {
        LOG.info("Saving feedback.");
        return feedbackDAL.addNewFeedback(feedback);
    }

    @RequestMapping(value = "/{feedbackId}", method = RequestMethod.GET)
    public Feedback getFeedback(@PathVariable String feedbackId) {
        LOG.info("Getting feedback with ID: {}.", feedbackId);
        return getFeedbackWithDefault(feedbackId);
    }

    @RequestMapping(value = "/get/{feedbackId}", method = RequestMethod.GET)
    public Feedback getFeedbackById(@PathVariable String feedbackId) {
        LOG.info("Getting feedback with ID: {}.", feedbackId);
        return feedbackDAL.getFeedbackById(feedbackId);
    }

    @RequestMapping(value = "/{feedbackId}/selections", method = RequestMethod.GET)
    public Object getAllFeedbackSelectionsById(@PathVariable String feedbackId) {
        Feedback feedback = getFeedbackWithDefault(feedbackId);
        if (feedback != null) {
            return feedback.getSelections();
        } else {
            return "Feedback not found.";
        }
    }

    @RequestMapping(value = "/get/{feedbackId}/selections", method = RequestMethod.GET)
    public Object getAllFeedbackSelections(@PathVariable String feedbackId) {
        return feedbackDAL.getAllFeedbackSelections(feedbackId);
    }

    @RequestMapping(value = "/{feedbackId}/selections/{key}", method = RequestMethod.GET)
    public String getFeedbackSelectionByKey(@PathVariable String feedbackId, @PathVariable String key) {
        Feedback feedback = getFeedbackWithDefault(feedbackId);
        if (feedback != null) {
            String value = feedback.getSelections().get(key);
            if(value != null) {
                return value;
            } else {
                return "Selection not found.";
            }
        } else {
            return "Feedback not found.";
        }
    }

    @RequestMapping(value = "/get/{feedbackId}/selections/{key}", method = RequestMethod.GET)
    public String getFeedbackSelection(@PathVariable String feedbackId, @PathVariable String key) {
        return feedbackDAL.getFeedbackSelection(feedbackId, key);
    }

    @RequestMapping(value = "/{feedbackId}/selections/{key}/{value}", method = RequestMethod.POST)
    public String addFeedbackSelection(@PathVariable String feedbackId, @PathVariable String key, @PathVariable String value) {
        Feedback feedback = getFeedbackWithDefault(feedbackId);
        if (feedback != null) {
            feedback.getSelections().put(key, value);
            feedbackRepository.save(feedback);
            return "Key added";
        } else {
            return "Feedback not found.";
        }
    }

    @RequestMapping(value = "/update/{feedbackId}/selections/{key}/{value}", method = RequestMethod.POST)
    public String addFeedbackSelectionWithKeyValue(@PathVariable String feedbackId, @PathVariable String key, @PathVariable String value) {
        return feedbackDAL.addFeedbackSelections(feedbackId, key, value);
    }

    @RequestMapping(value = "/stats/count", method = RequestMethod.GET)
    public String countFeedbackStars() {
        return feedbackDAL.feedbackStarAnalysis();
    }
}