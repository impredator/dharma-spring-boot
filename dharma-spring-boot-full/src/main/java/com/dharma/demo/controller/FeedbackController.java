package com.dharma.demo.controller;

import com.dharma.demo.dao.FeedbackDAL;
import com.dharma.demo.dao.FeedbackRepository;
import com.dharma.demo.model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final FeedbackRepository feedbackRepository;

    private final FeedbackDAL feedbackDAL;

    public FeedbackController(FeedbackRepository feedbackRepository, FeedbackDAL feedbackDAL) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackDAL = feedbackDAL;
    }

    private Feedback getFeedbackWithDefault(String feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    @GetMapping("/all")
    public List<Feedback> getAllFeedbacks() {
        LOG.info("get all feedbacks");
        return feedbackRepository.findAll();
    }

    @GetMapping("/")
    public List<Feedback> getFeedbacks() {
        LOG.info("get all feedbacks");
        return feedbackDAL.getAllFeedbacks();
    }

    @GetMapping("/id/{feedbackId}")
    public Feedback getFeedback(@PathVariable String feedbackId) {
        LOG.info("get feedback by id = " + feedbackId);
        return getFeedbackWithDefault(feedbackId);
    }

    @GetMapping("/{feedbackId}")
    public Feedback getFeedbackById(@PathVariable String feedbackId) {
        LOG.info("get feedback by id = " + feedbackId);
        return feedbackDAL.getFeedbackById(feedbackId);
    }

    @PostMapping("/new")
    public Feedback newFeedback(@RequestBody Feedback feedback){
        LOG.info("Add feedback");
        return feedbackRepository.save(feedback);
    }

    @PostMapping("/add")
    public Feedback addFeedback(@RequestBody Feedback feedback){
        LOG.info("Add feedback");
        return feedbackDAL.addNewFeedback(feedback);
    }

    @GetMapping("/{feedbackId}/selections")
    public Object getAllFeedbackSelections(@PathVariable String feedbackId) {
        return feedbackDAL.getAllFeedbackSelections(feedbackId);
    }

    @GetMapping("/id/{feedbackId}/selections")
    public Object getFeedbackSelections(@PathVariable String feedbackId) {
        Feedback feedback = getFeedbackWithDefault(feedbackId);
        return feedback != null ? feedback.getSelections() : "Feedback not found";
    }

    @GetMapping("/{feedbackId}/selections/{key}")
    public Object getFeedbackSelectionByKey(@PathVariable String feedbackId, @PathVariable String key) {
        String result = "";
        Feedback feedback = feedbackRepository.findById(feedbackId).orElse(null);
        if(feedback != null) {
            String selection = feedback.getSelections().get(key);
            if(selection != null) {
                result = selection;
            } else {
                result = "Selection not found";
            }
        } else {
            result = "Feedback not found";
        }
        return result;
    }

    @PostMapping("/{feedbackId}/selections/{key}/{value}")
    public String addFeedbackSection(@PathVariable String feedbackId, @PathVariable String key, @PathVariable String value) {
//        Feedback feedback = getFeedbackWithDefault(feedbackId);
//        if(feedback != null) {
//            feedback.getSelections().put(key, value);
//            feedbackRepository.save(feedback);
//            return "Selection add done";
//        } else {
//            return "Feedback not found";
//        }
        return feedbackDAL.addFeedbackSelections(feedbackId, key, value);
    }

    @GetMapping("/stats/count")
    public String countFeedbackStars() {
        return feedbackDAL.feedbackStarAnalysis();
    }

}
