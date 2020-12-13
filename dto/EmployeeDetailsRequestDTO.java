package com.java.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @author santo
 * employee details request dto
 */
public class EmployeeDetailsRequestDTO {

	
	private Long empCode;

	@NotEmpty(message = "Please provide employee name")
	@Size(min = 3, max = 100)
	private String empName;

	@NotEmpty(message = "gender should not be empty")
	@Size(min = 1, max = 10)
	private String empGender;

	@NotEmpty(message = "designation should not be empty")
	@Size(min = 5, max = 100)
	private String empDesignation;

	@NotEmpty(message = "Please provide Email id")
	@Email
	private String empEmailId;

	@NotNull(message = "please provide valid experience")
	@Min(value = 0, message = "please provide Valid experience")
	private Integer empExperience;

	@NotEmpty(message = "Please provide a Mobile Number")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Provide valid Mobile Number")
	private String empPhoneNo;

	@NotEmpty(message = "location should not be empty")
	@Size(min = 5, max = 100)
	private String empLocation;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getEmpEmailId() {
		return empEmailId;
	}

	public void setEmpEmailId(String empEmailId) {
		this.empEmailId = empEmailId;
	}

	public Integer getEmpExperience() {
		return empExperience;
	}

	public void setEmpExperience(Integer empExperience) {
		this.empExperience = empExperience;
	}

	public String getEmpLocation() {
		return empLocation;
	}

	public void setEmpLocation(String empLocation) {
		this.empLocation = empLocation;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public String getEmpPhoneNo() {
		return empPhoneNo;
	}

	public void setEmpPhoneNo(String empPhoneNo) {
		this.empPhoneNo = empPhoneNo;
	}

	public Long getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Long empCode) {
		this.empCode = empCode;
	}

}
