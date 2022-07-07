package com.memories.new_life.service;
import org.springframework.stereotype.Service;


@Service
public interface BackendService {

	public byte[] getFile(String fileName);
	
	
	public boolean validateLogin(String uname, String pswd);


	public String sendOtp(String num);


	public Boolean validateOtp(String num);
}
