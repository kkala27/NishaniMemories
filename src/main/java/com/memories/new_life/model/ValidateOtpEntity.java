package com.memories.new_life.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "validate_otp")
@Data
public class ValidateOtpEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "OTP")
	private String otp;

	@Column(name = "is_valid")
	private Boolean isValid;

	@Column(name = "TIMESTAMP")
	private Timestamp timestamp;
}
