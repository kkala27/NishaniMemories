package com.memories.new_life.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.memories.new_life.notification.SendMessage;
import com.memories.new_life.service.BackendService;
import com.memories.new_life.utils.Utility;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BackendController {

	@Value("${images.path}")
	private String imagePath;

	@Autowired
	BackendService service;

	@RequestMapping(value = "/getImage/{imageId}")
	@ResponseBody
	public byte[] getImage(@PathVariable String imageId, HttpServletRequest request) {
		log.info("Inside getImage for image id => " + imageId);
		return service.getFile(imageId);
	}

	@PostMapping("/validateOtp")
	public ModelAndView validateOtp(@RequestParam("otp") String otp, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		String num = request.getSession().getAttribute("number").toString();
		log.info("Inside validate otp for phone number => " + num + " and otp =>" + otp);
			if (service.validateOtp("+91"+num)) {
				modelAndView.setViewName("welcome");
				request.getSession().setAttribute("access-token", Utility.getAccessToken());
			} else {
				modelAndView.setViewName("login-form");
				model.addAttribute("error", "Invalid OTP");
			}
		return modelAndView;
	}
	
	@PostMapping("/generateOtp")
	public ModelAndView generateOtp(@RequestParam("num") String num, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		log.info("Inside generateOtp for phone number => " + num );
			String result = service.sendOtp("+91"+num);
			modelAndView.setViewName("login-form");
			model.addAttribute("result",result);
			request.getSession().setAttribute("number", num);
		return modelAndView;
	}

}
