package com.coursefeedback.validator;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 * Validates a file based on its size(>10MB)
 * 
 * @author touzbi
 */
@FacesValidator(value = "fileValidator")
public class FileValidator implements Validator {

	private static final long MAX_FILE_SIZE = 10485760L; // 10MB.

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Part file = (Part) value;

		if (file != null && file.getSize() > MAX_FILE_SIZE) {
			try {
				file.delete();
			} catch (IOException e) {
			}

			// Free resources!
			throw new ValidatorException(new FacesMessage(String.format(
					"File exceeds maximum permitted size of %d bytes.",
					MAX_FILE_SIZE)));
		}
	}

	@SuppressWarnings("unused")
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