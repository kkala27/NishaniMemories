package com.memories.new_life.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.memories.new_life.model.ValidateOtpEntity;

@Repository
public interface ValidateOtpRepo extends JpaRepository<ValidateOtpEntity, Integer> {

	@Query(value = "SELECT OTP FROM validate_otp where PHONE_NUMBER = ?1 and is_valid = true", nativeQuery = true)
	public String findValidOtp(String number);

	public List<ValidateOtpEntity> findByPhoneNumber(String phoneNumber);

}
