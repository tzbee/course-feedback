package com.coursefeedback.fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.coursefeedback.student.Student;
import com.coursefeedback.student.StudentManager;

@ManagedBean
@RequestScoped
public class UploadBean {
	// allowTypes="/(\.|\/)(xls|xlsx)$/"
	private Part file;

	public String upload(StudentManager studentManager) throws IOException {
		return studentManager.saveStudentList(displayFile());
	}

	public Collection<Student> displayFile() throws IOException {
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
