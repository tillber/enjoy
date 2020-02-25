package se.madev.main.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Application {
	private User applicant;
	private Experience experience;
	private Availability availability;
	private Status status;
	
	public Application(User applicant, Experience experience, Availability availability) {
		this.applicant = applicant;
		this.experience = experience;
		this.availability = availability;
		this.status = Status.UNHANDLED;
	}
	
	public Application() {
		this.status = Status.UNHANDLED;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}
	
	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Application [applicant=" + applicant + ", experience=" + experience
				+ ", availability=" + availability + ", status=" + status + "]";
	}

	public enum Status {
		UNHANDLED {
			public String toString() {
				return "UNHANDLED";
			}
		},
		ACCEPTED {
			public String toString() {
				return "ACCEPTED";
			}
		},
		REJECTED {
			public String toString() {
				return "REJECTED";
			}
		}
	}
}
