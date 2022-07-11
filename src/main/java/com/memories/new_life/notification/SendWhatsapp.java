package com.memories.new_life.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.memories.new_life.configuration.PropertiesConfiguration;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SendWhatsapp {

	@Autowired
	PropertiesConfiguration propertiesConfig;

	public String sendWhatsapp(String num, String messageString, boolean sendMessage) {
		String result = "success";
		if (sendMessage) {
			try {
				Twilio.init(propertiesConfig.getConfigurationMap().get("ACCOUNT_SID"),
						propertiesConfig.getConfigurationMap().get("AUTH_TOKEN"));
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
