package com.coursefeedback.fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.coursefeedback.studentmanager.Student;
import com.coursefeedback.studentmanager.StudentManager;

@ManagedBean
@RequestScoped
public class UploadBean {
	// allowTypes="/(\.|\/)(xls|xlsx)$/"
	private Part file;
	private String fileContent;

	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;
		// if (file.getSize() > 1024) {
		// msgs.add(new FacesMessage("file too big"));
		// // }
		// if (!"application/kset".equals(file.getContentType())) {
		// msgs.add(new FacesMessage("not a excel file"));
		// }
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
	}

	public String upload(StudentManager studentManager) throws IOException {
		// System.out.println("fileContent");

		try {
			fileContent = new Scanner(file.getInputStream())
					.useDelimiter("\\A").next();
		} catch (IOException e) {
			// Error handling
		}

		return studentManager.saveStudentList(displayFile());
	}

	public ArrayList<Student> displayFile() throws IOException {
		InputStream inputStream = file.getInputStream();
		// FileOutputStream outputStream = new
		// FileOutputStream(getFilename(file));
		// String name = new File(((Object)
		// file).getOriginalFilename()).getName();
		// String fileName = FilenameUtils.getName(file.getName());

		String fileName = getFilename(file);
		// String filePath = System.getProperty("java.io.tmpdir") + "" +
		// fileName;
		// String filePath="C://Users//shaolan//Desktop//"+fileName;
		// InputStream inputStream=new FileInputStream (filePath);

		// String filePath=file.
		System.out.println("filename" + fileName);
		// System.out.println("filePath"+ filePath);

		// FileInputStream file = new FileInputStream(new File("C://demo.xls"));

		POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);

		HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
		StudentManager studentBean = new StudentManager();
		return studentBean.displayStudentFile(workbook);
		// studentBean.ss();
		// HSSFSheet sheet = workbook.getSheetAt(0);
		// // Iterate through each rows from first sheet
		// Iterator<Row> rowIterator = sheet.rowIterator();
		// while (rowIterator.hasNext()) {
		// Row row = rowIterator.next();
		// // For each row, iterate through each columns
		// Iterator<Cell> cellIterator = row.cellIterator();
		// while (cellIterator.hasNext()) {
		// Cell cell = cellIterator.next();
		// switch (cell.getCellType()) {
		// case Cell.CELL_TYPE_BOOLEAN:
		// cell.getBooleanCellValue();
		// System.out.println("cell"+cell.getBooleanCellValue());
		// break;
		// case Cell.CELL_TYPE_NUMERIC:
		// cell.getNumericCellValue();
		// System.out.println("cell"+cell.getNumericCellValue());
		// break;
		// case Cell.CELL_TYPE_STRING:
		// cell.getStringCellValue();
		// System.out.println("cell"+cell.getStringCellValue());
		// break;
		// }
		// }
		//
		//
		//
		// }

	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}
}
