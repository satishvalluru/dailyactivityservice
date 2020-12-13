package com.java.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 
 * @author santo
 * activity tracker dto
 */
public class ActivityTrackerDto {
	
	@Digits(integer = 8, fraction = 1, message = "InValid employee code")
	private Long empCode;

	@NotEmpty(message = "Please provide activity description")
	@Size(min = 5, max = 3000)
	private String activityDescription;

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

}
