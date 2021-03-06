/**
 * 
 */
package com.coursefeedback.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author isaac Ogunlolu validates username's length
 * 
 */
@FacesValidator(value = "usernameValidator")
public class UsernameValidator implements Validator {

	private static final int MAX_LENGTH = 15;
	private static final int MIN_LENGTH = 4;

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value.toString().length() < MIN_LENGTH) {
			FacesMessage message = new FacesMessage(
					"Username too short, btw 4 to 15 characters is required");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		} else if (value.toString().length() > MAX_LENGTH) {
			FacesMessage message = new FacesMessage(
					"Username too long, btw 4 to 15 characters is required");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
