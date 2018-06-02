package com.dharma.demo.error;

import com.dharma.demo.controller.IndexController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DharmaException {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @ExceptionHandler({ RuntimeException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView processException(RuntimeException exception) {
        logger.info("dharma custom - runtime exception");
        ModelAndView mav = new ModelAndView();
        mav.addObject("dharmaException", exception.getMessage());
        mav.setViewName("error/500");
        return mav;
    }
}
