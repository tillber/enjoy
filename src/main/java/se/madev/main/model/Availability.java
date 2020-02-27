package se.madev.main.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Availability")
public class Availability {

	@Id
	@Column(name="id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="applicationid")
	private Application application;

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
	
	public Availability() {}

	public Date getFromDate() {
		return this.fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "Availability [from=" + fromDate + ", to=" + toDate + "]";
	}
}
