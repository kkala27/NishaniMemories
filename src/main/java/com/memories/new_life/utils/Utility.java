package com.memories.new_life.utils;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utility {

	static final int SECONDS_PER_MINUTE = 60;
	static final int SECONDS_PER_DAY = 86400;
	static final int SECONDS_PER_HOUR = 3600;

	public static String getAccessToken() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	public static Map<String, String> getBirthdayTime() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dateString = "21/07/2022";
		// string to date
		LocalDate birthdayDate = LocalDate.parse(dateString, dateTimeFormatter);
		LocalDateTime birthdayDateTime = birthdayDate.atStartOfDay();
		Duration duration = Duration.between(LocalDateTime.now(), birthdayDateTime);
		long seconds = duration.getSeconds();
		BigInteger days = BigInteger.valueOf(seconds / SECONDS_PER_DAY);
		seconds = seconds % SECONDS_PER_DAY;
		BigInteger hours = BigInteger.valueOf(seconds / SECONDS_PER_HOUR);
		seconds = seconds % SECONDS_PER_HOUR;
		BigInteger minutes = BigInteger.valueOf(seconds / SECONDS_PER_MINUTE);
		seconds = seconds % SECONDS_PER_MINUTE;
		Map<String, String> map = new HashMap<>();
		map.put("%noOfDays%", days.toString());
		map.put("%age%", "29th");
		map.put("%days%", days.toString());
		map.put("%hours%", hours.toString());
		map.put("%minutes%", minutes.toString());
		map.put("%seconds%", Long.toString(seconds));
		return map;
	}

	public static String replaceStringFromEmailBody(String birthdayReminderBody, Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet())
			birthdayReminderBody = birthdayReminderBody.replace(entry.getKey(), entry.getValue());
		return birthdayReminderBody;
	}

}
