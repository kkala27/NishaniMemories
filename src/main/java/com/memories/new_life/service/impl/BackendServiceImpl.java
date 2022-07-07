package com.memories.new_life.service.impl;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.memories.new_life.azure.AzureAdapter;
import com.memories.new_life.model.ValidateOtpEntity;
import com.memories.new_life.notification.SendMessage;
import com.memories.new_life.repo.ValidateOtpRepo;
import com.memories.new_life.service.BackendService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BackendServiceImpl implements BackendService {

	@Autowired
	AzureAdapter adapter;

	@Autowired
	ValidateOtpRepo repo;

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
		String otp = generateOtp();
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
		String result = SendMessage.sendMessage(num, message);
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

}
