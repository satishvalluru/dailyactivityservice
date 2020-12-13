package com.java.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.EmployeeCodesRequestDto;
import com.java.dto.EmployeeRequestDTO;
import com.java.dto.EmployeeResponseDTO;
import com.java.exceptions.EmployeeNotFoundException;

//@FeignClient(name = "http://EMPLOYEESERVICE/employee")
@FeignClient(value = "employeeservice", url = "http://localhost:8082/employeeservice/employee")
public interface EmployeeServiceClient {

	@GetMapping("/port")
	public String getInfo();

	@GetMapping("/getEmployees")
	public List<EmployeeRequestDTO> getEmployees();
	
	@GetMapping("/viewEmployeeWithCode/{empCode}")
	public EmployeeResponseDTO viewEmployeeWithCode(@RequestParam Long empCode)
			throws EmployeeNotFoundException;
	
	@PostMapping("/fetch/empdetails")
	public  List<EmployeeResponseDTO> fetchEmployeeDetails(@RequestBody EmployeeCodesRequestDto employeeCodesRequestDto);
			
	/*
	 * @PostMapping("/fundtransfer") public String fundTransfer(@RequestBody
	 * TransactionMasterRequest transreq);
	 */
}