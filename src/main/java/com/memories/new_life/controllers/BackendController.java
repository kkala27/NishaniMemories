package com.memories.new_life.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.memories.new_life.model.PropertiesEntity;
import com.memories.new_life.notification.SendEmail;
import com.memories.new_life.service.BackendService;
import com.memories.new_life.utils.Utility;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BackendController {

	@Value("${images.path}")
	private String imagePath;
	
	@Value("${app.active.profile}")
	String activeProfile;

	@Autowired
	BackendService service;
	
	@Autowired 
	SendEmail sendMail;

	@RequestMapping(value = "/getImage/{imageId}")
	@ResponseBody
	public byte[] getImage(@PathVariable String imageId, HttpServletRequest request) {
		log.info("Inside getImage for image id => " + imageId);
		return service.getFile(imageId);
	}

	@PostMapping("/validateOtp")
	public ModelAndView validateOtp(@RequestParam("otp") String otp, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		String num = request.getSession().getAttribute("number").toString();
		log.info("Inside validate otp for phone number => " + num + " and otp =>" + otp);
		if (service.validateOtp("+91" + num)) {
			modelAndView.setViewName("welcome");
			request.getSession().setAttribute("access-token", Utility.getAccessToken());
		} else {
			modelAndView.setViewName("login-form");
			model.addAttribute("error", "Invalid OTP");
		}
		return modelAndView;
	}

	@PostMapping("/generateOtp")
	public ModelAndView generateOtp(@RequestParam("num") String num, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		log.info("Inside generateOtp for phone number => " + num);
		String result = service.sendOtp("+91" + num);
		modelAndView.setViewName("login-form");
		model.addAttribute("result", result);
		request.getSession().setAttribute("number", num);
		return modelAndView;
	}

	@RequestMapping(value = "/sendOtp/{num}")
	@ResponseBody
	public String sendOtp(@PathVariable String num, HttpServletRequest request) {
		log.info("Inside sendOtp for number  => " + num);
		request.getSession().setAttribute("number", num);
		return service.sendOtp("+91" + num);
	}
	
	
	@RequestMapping(value = "/sendBirthdayUpdates")
	@ResponseBody
	public String getTime(HttpServletRequest request) throws IOException {
		return service.sendBirthdayUpdates();
	}

	
	@PostMapping("/changeProperties")
	public String updateProperties(@RequestBody PropertiesEntity entity) {
		String result = service.updatePropertiesValue(entity);
		return "success";
	}

	@RequestMapping(value = "/sendTestOtp")
	@ResponseBody
	public String sendTestOtp(HttpServletRequest request) throws IOException {
		return service.sendOtp("+919999707559");
	}
	
}
