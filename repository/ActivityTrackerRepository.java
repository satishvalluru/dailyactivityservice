package com.java.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.model.ActivityTracker;

/**
 * 
 * @author santo activity repository
 */
@Repository
public interface ActivityTrackerRepository extends JpaRepository<ActivityTracker, Long> {

	List<ActivityTracker> findByEmpCode(Long empCode);

	@Query("select d from ActivityTracker d where d.activityDate >= :fromDate and d.activityDate <= :toDate")
	List<ActivityTracker> findByActivityDateBetween(@Param("fromDate") LocalDate fromDate,
			@Param("toDate") LocalDate toDate);

	List<ActivityTracker> findByActivityDate(LocalDate date);

	List<ActivityTracker> findByActivityDateAndEmpCode(LocalDate date, Long empCode);

	@Query("select d from ActivityTracker d where d.activityDate >= :fromDate and d.activityDate <= :toDate and d.empCode = :empCode")
	List<ActivityTracker> findByActivityDateAndEmpCodeBetween(@Param("fromDate") LocalDate fromDate,
			@Param("toDate") LocalDate toDate, Long empCode);

	List<ActivityTracker> findByactivityDate(LocalDate activityDate);

}