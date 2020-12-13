package com.java.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author santo
 * entity for activity tracker
 */
@Entity
@Table(name = "activity_tracker")
public class ActivityTracker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long empCode;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate activityDate;

	private String activityDescription;

	private String activityStatus;

	private Boolean flag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public LocalDate getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(LocalDate activityDate) {
		this.activityDate = activityDate;
	}

	@Override
	public String toString() {
		return "ActivityTracker [id=" + id + ", empCode=" + empCode + ", activityDate=" + activityDate
				+ ", activityDescription=" + activityDescription + ", activityStatus=" + activityStatus + ", flag="
				+ flag + "]";
	}
	
	

}
