package com.memories.new_life.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FrontendController {
	
	@RequestMapping("/login")
	public ModelAndView login(HttpSession session) {
		log.info("Request for login page recieved");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login-form");
		return modelAndView;
	}

	@RequestMapping(value = "/nextSlide/{slideId}")
	@ResponseBody
	public ModelAndView getImage(@PathVariable Integer slideId, HttpServletRequest request) {
		log.info("Request for new slide recieved with slide id => "+slideId);
		ModelAndView modelAndView = new ModelAndView();
	//	switch(slideId) {
//		case 1: modelAndView.setViewName("slide1");
//		break;
//		case 2:modelAndView.setViewName("slide2");
//			break;
//		case 4:modelAndView.setViewName("slide3");
//			break;
//		case 5:modelAndView.setViewName("slide4");
//		break;
//		case 6:modelAndView.setViewName("slide5");
//		break;
//		case 7:modelAndView.setViewName("slide6");
//		break;
//		case 8:modelAndView.setViewName("slide7");
//		break;
//		case 9:modelAndView.setViewName("slide8");
//		break;
//		case 10:modelAndView.setViewName("slide9");
//		break;
//		case 11:modelAndView.setViewName("slide3");
//		break;
//		}
		modelAndView.setViewName("slide"+slideId);
		return modelAndView;
	}
	
	@RequestMapping("/welcome")
	public ModelAndView welcome(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		log.info("Request for welcome page recieved");
		modelAndView.setViewName("welcome");
		return modelAndView;
	}
}
