package com.java.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.java.dto.ActivityTrackerResponseDTO;
import com.java.dto.ActivtyTrackerRequestDTO;
import com.java.exceptions.DailyActivityNotFoundException;

/**
 * 
 * @author santo
 * save,retrieving,delete, modifying activities operations
 */
public interface ActivityTrackerService {

	public List<ActivityTrackerResponseDTO> viewDailyActivity(Long empCode) throws DailyActivityNotFoundException;

	public boolean modifyDailyActivity(Map<String, Object> updates, Long empCode) throws DailyActivityNotFoundException;

	public boolean deleteDailyActivity(Long empCode) throws DailyActivityNotFoundException;

	public ActivtyTrackerRequestDTO saveActivity(ActivtyTrackerRequestDTO dailyActivtyRequestDTO) throws Exception;

	public List<ActivityTrackerResponseDTO> viewDailyActivityWithDate(LocalDate fromDate, LocalDate toDate)
			throws DailyActivityNotFoundException;

	public List<ActivityTrackerResponseDTO> viewActivityWithFromDate(LocalDate date)
			throws DailyActivityNotFoundException;

	public List<ActivityTrackerResponseDTO> viewActivityWithFromDateAndCode(LocalDate date, Long empCode) throws DailyActivityNotFoundException;

	List<ActivityTrackerResponseDTO> viewDailyActivityWithDateAndCode(LocalDate fromDate, LocalDate toDate, Long empCode)
			throws DailyActivityNotFoundException;

	public List<ActivityTrackerResponseDTO> viewAllDailyActivities(int pageNumber, int pageSize);
	
	ByteArrayInputStream downloadReportforDate(LocalDate activityDate);

}
