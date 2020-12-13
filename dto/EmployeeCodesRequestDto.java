package com.java.dto;

import java.util.List;

public class EmployeeCodesRequestDto {

	private String empStatus;
	private List<Long> empCode;
	
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public List<Long> getEmpCode() {
		return empCode;
	}
	public void setEmpCode(List<Long> empCode) {
		this.empCode = empCode;
	}
}
