package com.dharma.boot.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class dharmaExcepiton {

	private static final Logger logger =  LoggerFactory.getLogger(dharmaExcepiton.class);

	@ExceptionHandler({ RuntimeException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(RuntimeException exception) {
		logger.info("dharma custom-RuntimeException");
		ModelAndView m = new ModelAndView();
		m.addObject("dharmaException", exception.getMessage());
		m.setViewName("error/500");
		return m;
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(Exception exception) {
		logger.info("dharma custom-Exception");
		ModelAndView m = new ModelAndView();
		m.addObject("dharmaException", exception.getMessage());
		m.setViewName("error/500");
		return m;
	}

}
