package com.memories.new_life.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import com.memories.new_life.model.PropertiesEntity;
import com.memories.new_life.model.ValidateOtpEntity;
import com.memories.new_life.notification.SendEmail;
import com.memories.new_life.notification.SendMessage;
import com.memories.new_life.notification.SendWhatsapp;
import com.memories.new_life.repo.PropertiesRepo;
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
	PropertiesRepo propertiesRepo;

	@Autowired
	SendEmail sendEmail;

	@Autowired
	SendMessage sendMessage;

	@Autowired
	SendWhatsapp sendWhatsapp;

	@Value("${otp.message}")
	private String otpMessage;

	@Value("${send.message}")
	private boolean isSendMessage;
	
	@Value("${file.path}")
	private String filePath;

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
		//byte[] data = adapter.getFile(fileName);
		File file = new File(filePath+"/"+fileName);
		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null;
	}

	@Override
	public boolean validateLogin(String uname, String pswd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String sendOtp(String num) {
		String otp = "";
		if (isSendMessage) {
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
		log.info("Otp for the user :"+otp);
		entity.setOtp(otp);
		entity.setPhoneNumber(num);
		entity.setIsValid(true);
		entity.setTimestamp(new Timestamp(new Date().getTime()));
		repo.save(entity);
		Map<String, String> replacementMap = new HashMap<>();
		replacementMap.put("%otp%", otp);
		String message = Utility.replaceStringFromEmailBody(otpMessage, replacementMap);
		String result = sendMessage.sendMessage(num, message, isSendMessage);
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
				sendMessage.sendMessage(num, messageString, isSendMessage);
				sendWhatsapp.sendWhatsapp(num, messageString, isSendMessage);
			});
		} catch (Exception e) {
			log.error("Error occured while sending birthday reminders " + e.getMessage());
			result = "failure";
		}
		return result;
	}

	@Override
	public String updatePropertiesValue(PropertiesEntity entity) {
		String result = "success";
		try {
			PropertiesEntity dbEntity = propertiesRepo.findByKey(entity.getKey());
			if (dbEntity != null) {
				log.info("Updating value for existing key");
				dbEntity.setValue(entity.getValue());
				propertiesRepo.save(dbEntity);
			} else {
				log.info("Adding new property");
				propertiesRepo.save(entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "failure";
		}
		return result;
	}

}
