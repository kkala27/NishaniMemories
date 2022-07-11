package com.memories.new_life.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.memories.new_life.model.PropertiesEntity;

@Service
public interface BackendService {

	public byte[] getFile(String fileName);

	public boolean validateLogin(String uname, String pswd);

	public String sendOtp(String num);

	public Boolean validateOtp(String num);

	public String sendBirthdayUpdates();

	public String updatePropertiesValue(PropertiesEntity entity);

}
