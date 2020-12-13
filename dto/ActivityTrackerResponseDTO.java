package com.java.dto;

import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author santo
 * activity tracker response dto
 */
public class ActivityTrackerResponseDTO {
	
	@Digits(integer = 8, fraction = 1, message = "InValid employee code")
	private Long empCode;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate activityDate;

	@NotEmpty(message = "Please provide activity description")
	@Size(min = 5, max = 3000)
	private String activityDescription;

	@NotEmpty(message = "status should not be empty")
	@Size(min = 5, max = 15)
	private String activityStatus;

	public Long getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Long empCode) {
		this.empCode = empCode;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public LocalDate getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(LocalDate activityDate) {
		this.activityDate = activityDate;
	}
}
