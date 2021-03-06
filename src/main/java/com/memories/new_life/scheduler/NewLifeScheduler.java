package com.memories.new_life.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.memories.new_life.configuration.PropertiesConfiguration;
import com.memories.new_life.service.BackendService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NewLifeScheduler {

	
	@Autowired
	BackendService backendService;
	
	@Autowired
	PropertiesConfiguration propertiesConfig;
	
	@Scheduled(cron = "0 0 * * * *"  )
	public void scheduleTaskUsingCronExpression() {
	 log.info("********** Starting birthday reminder cron job ************");
	 backendService.sendBirthdayUpdates();
	}
	
	@Scheduled(cron = "0 0/5 * * * *"  )
	public void refreshConfigProps() {
		propertiesConfig.initializingConfigurationMap();
	}
	
}
