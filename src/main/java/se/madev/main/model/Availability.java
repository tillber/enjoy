package se.madev.main.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Availability")
public class Availability {

	@Id
	@Column(name="id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="applicationid")
	private Applications application;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fromdate")
	private Date fromDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="todate")
	private Date toDate;
	
	public Availability(Date fromDate, Date toDate) {
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Availability(){}

	public Date getFrom() {
		return fromDate;
	}

	public void setFrom(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getTo() {
		return toDate;
	}

	public void setTo(Date toDate) {
		this.toDate = toDate;
	}
}
