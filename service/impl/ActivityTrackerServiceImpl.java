package com.java.service.impl;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.client.EmployeeServiceClient;
import com.java.dto.ActivityTrackerResponseDTO;
import com.java.dto.ActivtyTrackerRequestDTO;
import com.java.dto.ActvityReportResponse;
import com.java.dto.EmployeeCodesRequestDto;
import com.java.dto.EmployeeResponseDTO;
import com.java.exceptions.DailyActivityNotFoundException;
import com.java.exceptions.EmployeeNotFoundException;
import com.java.model.ActivityTracker;
import com.java.repository.ActivityTrackerRepository;
import com.java.service.ActivityTrackerService;
import com.java.utils.ExcelConvertionUtil;

@Service
public class ActivityTrackerServiceImpl implements ActivityTrackerService {

	private static final Logger logger = LoggerFactory.getLogger(ActivityTrackerServiceImpl.class);

	@Autowired
	ActivityTrackerRepository activityTrackerRepository;

	@Autowired
	EmployeeServiceClient employeeServiceClient;

	/**
	 * save activities
	 */
	@Override
	public ActivtyTrackerRequestDTO saveActivity(ActivtyTrackerRequestDTO dailyActivtyRequestDTO) {
		logger.debug("Started save activity method");

		List<ActivityTracker> activityTrackerList = new ArrayList<>();

		dailyActivtyRequestDTO.getActivityTrackerDto().stream().forEach(activityDto -> {
			EmployeeResponseDTO empDetailsResponseDTO = new EmployeeResponseDTO();
			try {
				empDetailsResponseDTO = employeeServiceClient.viewEmployeeWithCode(activityDto.getEmpCode());

			}

			catch (Exception e) {
				throw new EmployeeNotFoundException("employee code does not exist");
			}

			ActivityTracker activity = new ActivityTracker();
			LocalDate date = LocalDate.now();
			activity.setActivityDate(date);
			activity.setActivityDescription(activityDto.getActivityDescription());
			activity.setActivityStatus(dailyActivtyRequestDTO.getActivityStatus());
			activity.setEmpCode(activityDto.getEmpCode());
			activity.setFlag(false);
			BeanUtils.copyProperties(activityDto, activityTrackerList);
			activityTrackerList.add(activity);

		});

		activityTrackerRepository.saveAll(activityTrackerList);
		logger.debug("Ended save activity method");

		return dailyActivtyRequestDTO;
	}

	/**
	 * retrieving activity
	 */
	@Override
	public List<ActivityTrackerResponseDTO> viewDailyActivity(Long empCode) throws DailyActivityNotFoundException {
		logger.debug("Started view daily activity method");
		List<ActivityTracker> activityDetails = activityTrackerRepository.findByEmpCode(empCode);
		if (activityDetails.isEmpty()) {
			throw new DailyActivityNotFoundException("daily activity code not found");
		}
		List<ActivityTrackerResponseDTO> activityTrackerResponseDTO = new ArrayList<>();
		ActivityTrackerResponseDTO activityDetailsDTO = null;
		for (ActivityTracker dailyActivityDetails : activityDetails) {

			activityDetailsDTO = new ActivityTrackerResponseDTO();
			BeanUtils.copyProperties(dailyActivityDetails, activityDetailsDTO);
			activityTrackerResponseDTO.add(activityDetailsDTO);
		}
		logger.debug("Ended view daily activity method");

		return activityTrackerResponseDTO;
	}

	/**
	 * modifying activity
	 */
	@Override
	public boolean modifyDailyActivity(Map<String, Object> updates, Long empCode)
			throws DailyActivityNotFoundException {
		logger.debug("Started modify daily activity method");

		ActivityTracker activityTracker = new ActivityTracker();
		Optional<ActivityTracker> optionalActivityTracker = activityTrackerRepository.findById(empCode);

		if (!optionalActivityTracker.isPresent()) {
			throw new DailyActivityNotFoundException("dialy activity code not found");
		}

		if (!optionalActivityTracker.isPresent())
			return false;
		activityTracker = optionalActivityTracker.get();

		for (Map.Entry<String, Object> entry : updates.entrySet()) {
			try {
				org.apache.commons.beanutils.BeanUtils.setProperty(activityTracker, entry.getKey(), entry.getValue());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		logger.debug("Ended view daily activity method");

		activityTrackerRepository.save(activityTracker);
		return true;
	}

	/**
	 * delete activity
	 */
	@Override
	public boolean deleteDailyActivity(Long empCode) throws DailyActivityNotFoundException {
		logger.debug("Started delete daily activity method");

		List<ActivityTracker> activityTracker = activityTrackerRepository.findByEmpCode(empCode);

		if (activityTracker.size() == 0) {
			throw new DailyActivityNotFoundException("daily activity code not found");
		}
		for (ActivityTracker activitytrackers : activityTracker) {
			activitytrackers.setFlag(true);
			activityTrackerRepository.saveAll(activityTracker);

		}
		logger.debug("Ended delete daily activity method");

		return true;

	}

	/**
	 * retrieving activity with date
	 */
	@Override
	public List<ActivityTrackerResponseDTO> viewDailyActivityWithDate(LocalDate fromDate, LocalDate toDate)
			throws DailyActivityNotFoundException {
		logger.debug("Started view daily activity with date method");

		List<ActivityTracker> activityTrackers = activityTrackerRepository.findByActivityDateBetween(fromDate, toDate);
		if (activityTrackers.isEmpty()) {
			throw new DailyActivityNotFoundException("daily activity code not found");
		}
		List<ActivityTrackerResponseDTO> activityTrackerResponseDTOList = new ArrayList<>();
		activityTrackers.stream().forEach(activityResponseDTO -> {
			ActivityTrackerResponseDTO activityTrackerResponseDTO = new ActivityTrackerResponseDTO();
			BeanUtils.copyProperties(activityResponseDTO, activityTrackerResponseDTO);
			activityTrackerResponseDTOList.add(activityTrackerResponseDTO);

		});
		logger.debug("Ended view daily activity with date method");

		return activityTrackerResponseDTOList;
	}

	@Override
	public List<ActivityTrackerResponseDTO> viewDailyActivityWithDateAndCode(LocalDate fromDate, LocalDate toDate,
			Long empCode) throws DailyActivityNotFoundException {
		logger.debug("Started view daily activity with date method");

		List<ActivityTracker> activityTrackers = activityTrackerRepository.findByActivityDateAndEmpCodeBetween(fromDate,
				toDate, empCode);
		if (activityTrackers.isEmpty()) {
			throw new DailyActivityNotFoundException("daily activity code not found");
		}
		List<ActivityTrackerResponseDTO> activityTrackerResponseDTOList = new ArrayList<>();
		activityTrackers.stream().forEach(activityResponseDTO -> {
			ActivityTrackerResponseDTO activityTrackerResponseDTO = new ActivityTrackerResponseDTO();
			BeanUtils.copyProperties(activityResponseDTO, activityTrackerResponseDTO);
			activityTrackerResponseDTOList.add(activityTrackerResponseDTO);

		});
		logger.debug("Ended view daily activity with date method");

		return activityTrackerResponseDTOList;
	}

	/**
	 * retrieving activity with fromdate
	 */
	@Override
	public List<ActivityTrackerResponseDTO> viewActivityWithFromDate(LocalDate date)
			throws DailyActivityNotFoundException {
		logger.debug("Started view daily activity with fromdate method");

		List<ActivityTracker> activityTrackers = activityTrackerRepository.findByActivityDate(date);
		if (activityTrackers.isEmpty()) {
			throw new DailyActivityNotFoundException("daily activity code not found");
		}
		List<ActivityTrackerResponseDTO> activityTrackerResponseDTOList = new ArrayList<>();
		activityTrackers.stream().forEach(activityResponseDTO -> {
			ActivityTrackerResponseDTO activityTrackerResponseDTO = new ActivityTrackerResponseDTO();
			BeanUtils.copyProperties(activityResponseDTO, activityTrackerResponseDTO);
			activityTrackerResponseDTOList.add(activityTrackerResponseDTO);

		});
		logger.debug("Ended view daily activity with fromdate method");

		return activityTrackerResponseDTOList;
	}

	/**
	 * retrieving activity with date
	 */
	@Override
	public List<ActivityTrackerResponseDTO> viewActivityWithFromDateAndCode(LocalDate date, Long empCode)
			throws DailyActivityNotFoundException {
		logger.debug("Started view daily activity with fromdate and employee code method");

		List<ActivityTracker> activityTrackers = activityTrackerRepository.findByActivityDateAndEmpCode(date, empCode);
		if (activityTrackers.isEmpty()) {
			throw new DailyActivityNotFoundException("daily activity code not found");
		}
		List<ActivityTrackerResponseDTO> activityTrackerResponseDTOList = new ArrayList<>();
		activityTrackers.stream().forEach(activityResponseDTO -> {
			ActivityTrackerResponseDTO activityTrackerResponseDTO = new ActivityTrackerResponseDTO();
			BeanUtils.copyProperties(activityResponseDTO, activityTrackerResponseDTO);
			activityTrackerResponseDTOList.add(activityTrackerResponseDTO);

		});
		logger.debug("Ended view daily activity with fromdate and employee code method");

		return activityTrackerResponseDTOList;
	}

	/**
	 * retrieving all daily activities
	 */
	@Override
	public List<ActivityTrackerResponseDTO> viewAllDailyActivities(int pageNumber, int pageSize) {
		Page<ActivityTracker> activityTrack;
		List<ActivityTrackerResponseDTO> fetchActivityResponseDtoList = new ArrayList<>();
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		activityTrack = activityTrackerRepository.findAll(pageable);

		logger.info("exception for employees activity");
		if (activityTrack.isEmpty()) {
			logger.info("list is empty");
			throw new EmployeeNotFoundException("No activities found");
		}

		activityTrack.stream().forEach(actTrack -> {
			ActivityTrackerResponseDTO fetchActivityResponseDto = new ActivityTrackerResponseDTO();
			BeanUtils.copyProperties(actTrack, fetchActivityResponseDto);

			fetchActivityResponseDtoList.add(fetchActivityResponseDto);
		});

		return fetchActivityResponseDtoList;
	}

	@Override
	public ByteArrayInputStream downloadReportforDate(LocalDate activityDate) {

		List<ActivityTracker> activityList = activityTrackerRepository.findByactivityDate(activityDate);

		EmployeeCodesRequestDto employeeCodesRequestDto = new EmployeeCodesRequestDto();
		employeeCodesRequestDto.setEmpStatus("Active");

		List<Long> employeeCodes = new ArrayList<>();
		activityList.stream().forEach(actvity -> {
			employeeCodes.add(actvity.getEmpCode());
		});
		employeeCodesRequestDto.setEmpCode(employeeCodes);
		List<EmployeeResponseDTO> fetchEmployeeResponseDtoList = employeeServiceClient
				.fetchEmployeeDetails(employeeCodesRequestDto);
		List<ActvityReportResponse> actvityReportResponeList = new ArrayList<>();

		activityList.stream().forEach(activityresponse -> {

			fetchEmployeeResponseDtoList.stream().forEach(employeeresponse -> {

				if (activityresponse.getEmpCode().equals(employeeresponse.getEmpCode())) {

					ActvityReportResponse actvityReportRespone = new ActvityReportResponse();
					BeanUtils.copyProperties(activityresponse, actvityReportRespone);
					actvityReportRespone.setEmpName(employeeresponse.getEmpName());
					actvityReportResponeList.add(actvityReportRespone);
				}
			});
		});
		ByteArrayInputStream acts = ExcelConvertionUtil.actvitystoExcel(actvityReportResponeList);

		return acts;
	}
}
