package com.memories.new_life.service.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.memories.new_life.azure.AzureAdapter;
import com.memories.new_life.model.ValidateOtpEntity;
import com.memories.new_life.notification.SendEmail;
import com.memories.new_life.notification.SendMessage;
import com.memories.new_life.notification.SendWhatsapp;
import com.memories.new_life.repo.ValidateOtpRepo;
import com.memories.new_life.service.BackendService;
import com.memories.new_life.utils.Utility;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BackendServiceImpl implements BackendService {

	@Autowired
	AzureAdapter adapter;

	@Autowired
	ValidateOtpRepo repo;

	@Autowired
	SendEmail sendEmail;

	@Value("${send.message}")
	private boolean sendMessage;

	@Value("${test.otp}")
	private String testOtp;

	@Value("${email.reminder.message}")
	private String birthdayReminderBody;

	@Value("${email.reminder.subject}")
	private String birthdayReminderSubject;

	@Value("${message.birthday.updates}")
	private String messageBirthdayUpdate;

	@Value("${reciever.email}")
	private String recieverEmails;

	@Value("${reciever.number}")
	private String recieverNumbers;

	@Override
	public byte[] getFile(String fileName) {
		byte[] data = adapter.getFile(fileName);
		return data;
	}

	@Override
	public boolean validateLogin(String uname, String pswd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String sendOtp(String num) {
		String otp = "";
		if (sendMessage) {
			otp = generateOtp();
		} else {
			otp = testOtp;
		}
		List<ValidateOtpEntity> validOtps = repo.findByPhoneNumber(num);
		validOtps.forEach(x -> {
			x.setIsValid(false);
			repo.save(x);
		});
		ValidateOtpEntity entity = new ValidateOtpEntity();
		entity.setOtp(otp);
		entity.setPhoneNumber(num);
		entity.setIsValid(true);
		entity.setTimestamp(new Timestamp(new Date().getTime()));
		repo.save(entity);
		String message = "Please use the otp " + otp + " to login to the #nishani application";
		String result = SendMessage.sendMessage(num, message, sendMessage);
		log.info("Send OTP result => " + result);
		return result;
	}

	private String generateOtp() {
		// TODO Auto-generated method stub
		return new DecimalFormat("000000").format(new Random().nextInt(999999));
	}

	@Override
	public Boolean validateOtp(String num) {
		String otp = repo.findValidOtp(num);
		log.info(otp);
		if (otp != null) {
			log.info("otp verified");
			return true;
		}
		return false;
	}

	@Override
	public String sendBirthdayUpdates() {
		log.info("Sending birthday reminder updates");
		String result = "success";
		String messageString = Utility.replaceStringFromEmailBody(messageBirthdayUpdate, Utility.getBirthdayTime());
		String body = Utility.replaceStringFromEmailBody(birthdayReminderBody, Utility.getBirthdayTime());
		try {
			sendEmail.sendEmail(recieverEmails, birthdayReminderSubject, body);
			List<String> recieverNumbersList = Arrays.asList(recieverNumbers.split(","));
			recieverNumbersList.forEach(num -> {
				SendMessage.sendMessage(num, messageString, sendMessage);
				SendWhatsapp.sendWhatsapp(num, messageString, sendMessage);
			});
		} catch (Exception e) {
			log.error("Error occured while sending birthday reminders "+e.getMessage());
			result = "failure";
		}
		return result;
	}

}
