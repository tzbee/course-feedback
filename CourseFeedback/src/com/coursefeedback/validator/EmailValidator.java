/**
 * 
 */
package com.coursefeedback.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author isaac Ogunlolu Validate inputed student email's format
 * 
 */
@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
			+ "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*"
			+ "(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;

	/**
	 * 
	 */
	public EmailValidator() {

		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {
			FacesMessage message = new FacesMessage("Invalid Email Formatting");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);

		}
	}
}
