package se.madev.main.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Availability {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date from;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date to;
	
	public Availability(Date from, Date to) {
		this.from = from;
		this.to = to;
	}
	
	public Availability() {}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}	

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "Availability [from=" + from + ", to=" + to + "]";
	}
}
