package com.java.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.java.dto.ActvityReportResponse;

public class ExcelConvertionUtil {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static String[] HEADERs = { "Id", "ActivityDate", "ActivityDescription", "ActivityStatus",
			"EmpCode", "EmpName" };
	public static String SHEET = "Activity";

	public static ByteArrayInputStream actvitystoExcel(List<ActvityReportResponse> activityList) {

		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			// Creating excel work book in java
			Workbook workBook = new HSSFWorkbook();
			Sheet sheet = workBook.createSheet(SHEET);
			Row headerRow = sheet.createRow(0);

			// mapping the header values to excel
			for (int column = 0; column < HEADERs.length; column++) {
				sheet.autoSizeColumn(column);
				Cell cell = headerRow.createCell(column);
				cell.setCellValue(HEADERs[column]);
			}

			// mapping the db data to excel
			int rowIndex = 1;
			for (ActvityReportResponse actrack : activityList) {

				Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(actrack.getId());
				String date = actrack.getActivityDate().toString();
				row.createCell(1).setCellValue(date);
				row.createCell(2).setCellValue(actrack.getActivityDescription());
				row.createCell(3).setCellValue(actrack.getActivityStatus());
				row.createCell(4).setCellValue(actrack.getEmpCode());
				row.createCell(5).setCellValue(actrack.getEmpName());

			}
			workBook.write(output);
			return new ByteArrayInputStream(output.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}

	}
}
