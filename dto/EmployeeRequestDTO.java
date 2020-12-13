package com.java.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 
 * @author santo
 * employee request dto
 */
public class EmployeeRequestDTO {

	@NotEmpty(message = "status should not be empty")
	@Size(min = 5, max = 15)
	private String status;

	@NotEmpty(message = "list should not be empty")
	private List<@Valid EmployeeDetailsRequestDTO> employeeRequestDto = new ArrayList<EmployeeDetailsRequestDTO>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<EmployeeDetailsRequestDTO> getEmployeeRequestDto() {
		return employeeRequestDto;
	}

	public void setEmployeeRequestDto(List<EmployeeDetailsRequestDTO> employeeRequestDto) {
		this.employeeRequestDto = employeeRequestDto;
	}

}
