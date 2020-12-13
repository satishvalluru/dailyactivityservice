package com.java.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.ActivityTrackerResponseDTO;
import com.java.dto.ActivtyTrackerRequestDTO;
import com.java.exceptions.DailyActivityNotFoundException;
import com.java.service.ActivityTrackerService;

import io.swagger.annotations.Api;

@Validated
@RestController
@RequestMapping("/dailyactivity")
@Api(value="ActivityController", description = "Rest api for ActivityController", tags = {"ActivityController"})

public class ActivityController {

	@Autowired
	ActivityTrackerService activityTrackerService;
	
	/**
	 * 
	 * @param dailyActivtyRequestDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/saveDailyActivity")
	public ResponseEntity<String> addDailyActivity(@Valid @RequestBody ActivtyTrackerRequestDTO dailyActivtyRequestDTO) throws Exception {
		activityTrackerService.saveActivity(dailyActivtyRequestDTO);
		return new ResponseEntity<>("daily activity added successfully", HttpStatus.CREATED);

	}
	
	/**
	 * 
	 * @param empCode
	 * @return
	 * @throws DailyActivityNotFoundException
	 */
	@GetMapping("/viewDailyActivity/{empCode}")
	public ResponseEntity<List<ActivityTrackerResponseDTO>> viewActivityWithEmpCode(@RequestParam Long empCode) throws DailyActivityNotFoundException {
		List<ActivityTrackerResponseDTO> details = activityTrackerService.viewDailyActivity(empCode);
		return new ResponseEntity<>(details, HttpStatus.CREATED);

	}
	
	@GetMapping("/viewDailyActivity")
	public ResponseEntity<List<ActivityTrackerResponseDTO>> viewAllActivities(@RequestParam int pageNumber,@RequestParam int pageSize) throws DailyActivityNotFoundException {
		List<ActivityTrackerResponseDTO> details = activityTrackerService.viewAllDailyActivities(pageNumber, pageSize);
		return new ResponseEntity<>(details, HttpStatus.CREATED);

	}
	
	/**
	 * 
	 * @param updates
	 * @param empCode
	 * @return
	 * @throws DailyActivityNotFoundException
	 */
	@PatchMapping("/update/{empCode}")
	public ResponseEntity<String> modifyDailyActivity(@RequestBody Map<String, Object> updates, @PathVariable Long empCode) throws DailyActivityNotFoundException {
		System.out.println(updates.size());

		boolean isUpdated = activityTrackerService.modifyDailyActivity(updates, empCode);
		if (isUpdated) {
			return new ResponseEntity<>("Successfully modified daily activity", HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Failed to modify daily activity", HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * 
	 * @param empCode
	 * @return
	 * @throws DailyActivityNotFoundException
	 */
	@DeleteMapping("/deleteEmployee/{empCode}")
	public ResponseEntity<String> deleteDailyActivity(@PathVariable Long empCode) throws DailyActivityNotFoundException {
		activityTrackerService.deleteDailyActivity(empCode);
		return new ResponseEntity<>("daily activity details deleted successfully", HttpStatus.CREATED);

	}
	
	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws DailyActivityNotFoundException
	 */
	@GetMapping("/viewDailyActivityWithDate")
	public ResponseEntity<List<ActivityTrackerResponseDTO>> viewActivityWithDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) throws DailyActivityNotFoundException {

		List<ActivityTrackerResponseDTO> details = activityTrackerService.viewDailyActivityWithDate(fromDate,toDate);
		return new ResponseEntity<>(details, HttpStatus.CREATED);

	}
	
	/**
	 * 
	 * @param date
	 * @return
	 * @throws DailyActivityNotFoundException
	 */
	@GetMapping("/viewDailyActivityWithDate/{date}")
	public ResponseEntity<List<ActivityTrackerResponseDTO>> viewActivityWithFromDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws DailyActivityNotFoundException {

		List<ActivityTrackerResponseDTO> details = activityTrackerService.viewActivityWithFromDate(date);
		return new ResponseEntity<>(details, HttpStatus.CREATED);

	}
	
	/**
	 * 
	 * @param date
	 * @param empCode
	 * @return
	 * @throws DailyActivityNotFoundException
	 */
	@GetMapping("/viewDailyActivityWithDateAndCode")
	public ResponseEntity<List<ActivityTrackerResponseDTO>> viewActivityWithFromDateAndCode(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,@RequestParam Long empCode) throws DailyActivityNotFoundException {

		List<ActivityTrackerResponseDTO> details = activityTrackerService.viewActivityWithFromDateAndCode(date, empCode);
		return new ResponseEntity<>(details, HttpStatus.CREATED);

	}
	
	@GetMapping("/viewDailyActivityWithCodeAndDate")
	public ResponseEntity<List<ActivityTrackerResponseDTO>> viewActivityWithDateAndCode(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,@RequestParam Long empCode) throws DailyActivityNotFoundException {

		List<ActivityTrackerResponseDTO> details = activityTrackerService.viewDailyActivityWithDateAndCode(fromDate, toDate, empCode);
		return new ResponseEntity<>(details, HttpStatus.CREATED);

	}
	
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadActvityReport(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate activityDate) {
		 String filename = "activity.xls";
		    InputStreamResource file = new InputStreamResource(activityTrackerService.downloadReportforDate(activityDate));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=activity.xls")
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(file);
	}
}
