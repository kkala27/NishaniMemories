package com.memories.new_life.notification;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendWhatsapp {

	public static final String ACCOUNT_SID = "ACccfbb845f50dee4cd32bc617e40a13d4";
	public static final String AUTH_TOKEN = "8ee65e3cd47be7cfc7bd6b4feba1f3bb";

	public static String sendWhatsapp(String num, String messageString, boolean sendMessage) {
		String result = "success";
		if (sendMessage) {
			try {
				Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
				Message message = Message.creator(new com.twilio.type.PhoneNumber("whatsapp:" + num),
						new com.twilio.type.PhoneNumber("whatsapp:+14155238886"), messageString).create();
				log.info("Send whatsapp message response =>" + message.toString());
			} catch (Exception e) {
				result = e.getMessage();
			}
		} else {
			result = "success";
		}
		return result;
	}
}
