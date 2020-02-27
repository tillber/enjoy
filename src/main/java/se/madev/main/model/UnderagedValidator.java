package se.madev.main.model;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnderagedValidator implements ConstraintValidator<NonUnderAged, Date>{
	
	private final Date CURRENT_DATE = new Date();
	private final int MIN_AGE = 15;

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		return (CURRENT_DATE.compareTo(value) >= MIN_AGE);
	}

}
