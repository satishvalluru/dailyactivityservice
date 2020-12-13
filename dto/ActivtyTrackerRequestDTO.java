package com.java.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 
 * @author santo
 * activity tracker request dto
 */
public class ActivtyTrackerRequestDTO {

	@NotEmpty(message = "list should not be empty")
	private List<@Valid ActivityTrackerDto> activityTrackerDto = new ArrayList<ActivityTrackerDto>();

	@NotEmpty(message = "status should not be empty")
	@Size(min = 5, max = 15)
	private String activityStatus;

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public List<ActivityTrackerDto> getActivityTrackerDto() {
		return activityTrackerDto;
	}

	public void setActivityTrackerDto(List<ActivityTrackerDto> activityTrackerDto) {
		this.activityTrackerDto = activityTrackerDto;
	}

}
