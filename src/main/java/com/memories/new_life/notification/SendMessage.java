package com.memories.new_life.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.export.properties.PropertiesConfigAdapter;
import org.springframework.stereotype.Component;

import com.memories.new_life.configuration.PropertiesConfiguration;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendMessage {

	@Autowired
	PropertiesConfiguration propertiesConfig;

	public String sendMessage(String num, String message, boolean sendMessage) {
		String result = "success";
		if (sendMessage) {
			log.info("Sending message to number => " + num);
			try {
				log.info("Twillio Properties");
				System.out.println(propertiesConfig.getConfigurationMap());
				Twilio.init(propertiesConfig.getConfigurationMap().get("ACCOUNT_SID"),
						propertiesConfig.getConfigurationMap().get("AUTH_TOKEN"));
				Message sms = Message.creator(new PhoneNumber(num), new PhoneNumber(propertiesConfig.getConfigurationMap().get("SENDERS_NUMBER")), message).create();
				log.info("OTP Message send to number " + num + " response =====> " + sms.toString());
			} catch (Exception e) {
				log.error("######### Unbale to send OTP ########### " + num);
				log.error(e.getMessage());
				result = e.getMessage();
			}

		} else {
			result = "success";
		}
		return result;
	}

}
