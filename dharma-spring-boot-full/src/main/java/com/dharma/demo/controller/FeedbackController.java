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

    @GetMapping("")
    public List<Feedback> getAllFeedbacks() {
        LOG.info("get all feedbacks");
        return feedbackRepository.findAll();
    }

    @GetMapping("/{feedbackId}")
    public Feedback getFeedbackById(@PathVariable String feedbackId) {
        LOG.info("get feedback by id = " + feedbackId);
        return feedbackRepository.findById(feedbackId).orElse(null);
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
}
