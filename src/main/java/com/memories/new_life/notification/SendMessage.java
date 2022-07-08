package com.memories.new_life.notification;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendMessage {

	public static final String ACCOUNT_SID = "ACccfbb845f50dee4cd32bc617e40a13d4";
	public static final String AUTH_TOKEN = "d70f71f8472741c978abc5cd61f4c42d";

	public static String sendMessage(String num, String message,boolean sendOtp) {
		if (sendOtp) {
			log.info("Sending message to number => " + num);
			String result = "success";
			try {
				Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

				Message sms = Message.creator(new PhoneNumber(num), new PhoneNumber("+19289705306"), message).create();

				log.info("OTP Message send to number " + num + " status =====> " + result);
			} catch (Exception e) {
				log.error("######### Unbale to send OTP ########### " + num);
				log.error(e.getMessage());
				result = e.getMessage();
			}
			return result;
		} else {
			return "success";
		}
	}

	
}
