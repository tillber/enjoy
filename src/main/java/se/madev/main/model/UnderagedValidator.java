package se.madev.main.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnderagedValidator implements ConstraintValidator<NonUnderAged, Date>{
	
	private final int MIN_AGE = 15;
	private final Date CURRENT_DATE = new Date();
	
	private LocalDate convertToLocalDate(Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		LocalDate localDate = localDateTime.toLocalDate();
		
		return localDate;
	}

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		LocalDate today = convertToLocalDate(CURRENT_DATE);
		LocalDate userBirthDate = convertToLocalDate(value);
		return (today.getYear() - userBirthDate.getYear() >= MIN_AGE);
	}

}
