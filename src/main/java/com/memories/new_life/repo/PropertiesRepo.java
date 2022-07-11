package com.memories.new_life.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memories.new_life.model.PropertiesEntity;

public interface PropertiesRepo extends JpaRepository<PropertiesEntity, String> {

	public PropertiesEntity findByKey(String key);

}
