package com.coursefeedback.student.studentsfactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.coursefeedback.student.Student;

/**
 * Defines ways of creating or retriving a set of students using an Excel file
 * 
 */

@ManagedBean(name = "studentsFactory")
public class ExcelFileStudentsFactory implements StudentsFactory {

	@Override
	public Collection<Student> getStudents(InputStream inputStream) {
		Collection<Student> studentList = new ArrayList<Student>();

		HSSFWorkbook workbook;
		try {
			workbook = getHSSFWorkbook(inputStream);
		} catch (IOException e) {
			// Return an empty collection if the file cannot be read
			return Collections.emptyList();
		}

		// This workbook already has all content of uploaded file
		// ArrayList<Student> studentList=new ArrayList<Student>();
		String studentNo = null;
		String studentEmail = null;
		HSSFSheet sheet = workbook.getSheetAt(0);
		// Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.rowIterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			Student student = new Student();

			Cell cell = cellIterator.next();

			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				studentNo = String.valueOf(cell.getBooleanCellValue());
				System.out.println("cell" + cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				studentNo = String.valueOf(cell.getNumericCellValue());
				System.out.println("cell" + cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				studentNo = cell.getStringCellValue();
				System.out.println("cell" + cell.getStringCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				studentNo = "";
				System.out.println("cell" + cell.getStringCellValue());
				break;
			}

			student.setStudentNumber(studentNo);

			cell = cellIterator.next();
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				studentEmail = String.valueOf(cell.getBooleanCellValue());
				System.out.println("cell" + cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				studentEmail = String.valueOf(cell.getNumericCellValue());
				System.out.println("cell" + cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				studentEmail = cell.getStringCellValue();
				System.out.println("cell" + cell.getStringCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				studentEmail = "";
				System.out.println("cell" + cell.getStringCellValue());
				break;

			}
			student.setStudentEmail(studentEmail);
			studentList.add(student);
		}

		return studentList;
	}

	private static HSSFWorkbook getHSSFWorkbook(InputStream inputStream)
			throws IOException {
		return new HSSFWorkbook(new POIFSFileSystem(inputStream));
	}
}
