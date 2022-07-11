package com.memories.new_life.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.memories.new_life.model.PropertiesEntity;
import com.memories.new_life.repo.PropertiesRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PropertiesConfiguration {

	@Autowired
	PropertiesRepo propertiesRepo;

	private Map<String, String> configurationMap = new HashMap<>(); ;

	@PostConstruct
	public void initializingConfigurationMap() {
		log.info("Initializing Configuration Map");
		List<PropertiesEntity> propertiesList = propertiesRepo.findAll();
		propertiesList.forEach(item -> {
			configurationMap.put(item.getKey().trim(), item.getValue().trim());
		});
		log.info("Configuration map details");
		configurationMap.forEach((k, v) -> {
			log.info("Key => " + k);
			log.info("Value => " + v);
		});
	}

	public Map<String, String> getConfigurationMap() {
		return configurationMap;
	}

}
