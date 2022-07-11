package com.memories.new_life.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="properties")
@Data
public class PropertiesEntity {

	@Id
	@Column(name = "key")
	private String key;
	
	@Column(name = "value")
	private String value;
	
}
