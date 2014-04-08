package com.coursefeedback.fileupload;

import java.io.File;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "fileValidator")
public class FileValidator implements Validator {

	private static final long MAX_FILE_SIZE = 10485760L; // 10MB.

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		File file = (File) value;
		if (file != null && file.length() > MAX_FILE_SIZE) {
			file.delete(); // Free resources!
			throw new ValidatorException(new FacesMessage(String.format(
					"File exceeds maximum permitted size of %d bytes.",
					MAX_FILE_SIZE)));
		}
	}

}