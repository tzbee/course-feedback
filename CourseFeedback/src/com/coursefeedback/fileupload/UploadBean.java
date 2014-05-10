package com.coursefeedback.fileupload;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import com.coursefeedback.student.StudentManager;
import com.coursefeedback.student.studentsfactory.StudentsFactory;

@ManagedBean(name = "uploadBean")
@RequestScoped
public class UploadBean {
	// allowTypes="/(\.|\/)(xls|xlsx)$/"

	// Uploaded file
	private Part file;

	@ManagedProperty("#{studentManager}")
	private StudentManager studentManager;

	@ManagedProperty("#{studentsFactory}")
	private StudentsFactory studentsFactory;

	/**
	 * Retrives the students using the injected student factory implementation
	 * and file Saves the students using the injected student manager
	 * implementation
	 * 
	 * @return
	 * @throws IOException
	 */
	public String upload() throws IOException {
		return this.studentManager.saveStudents(this.studentsFactory
				.getStudents(this.file.getInputStream()));
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public void setStudentManager(StudentManager studentManager) {
		this.studentManager = studentManager;
	}

	public StudentManager getStudentManager() {
		return studentManager;
	}

	public StudentsFactory getStudentsFactory() {
		return studentsFactory;
	}

	public void setStudentsFactory(StudentsFactory studentsFactory) {
		this.studentsFactory = studentsFactory;
	}
}
