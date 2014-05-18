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
 * @author isaac Ogunlolu validate student no length and also validates if only
 *         numbers are inputed
 * 
 */
@FacesValidator(value = "numberValidator")
public class StudentNoValidator implements Validator {
	private Pattern pattern;
	private Matcher matcher;

	private static final int LENGTH = 7; // pending approval (for testing)
	private static final String NO_PATTERN = "\\d*";

	public StudentNoValidator() {

		pattern = Pattern.compile(NO_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		matcher = pattern.matcher(value.toString());

		if (!matcher.matches() && value.toString().length() != LENGTH) {

			FacesMessage message = new FacesMessage(
					"Numbers only!!! and 7 digit number required");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);

		} else if (!matcher.matches()) {
			FacesMessage message = new FacesMessage("Numbers Only!!!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		} else if (value.toString().length() != LENGTH) {
			FacesMessage message = new FacesMessage("7 digit number required");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
