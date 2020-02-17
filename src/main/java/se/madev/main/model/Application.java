package se.madev.main.model;

public class Application {
	private User applicant;
	private Experience[] experiences;
	private Availability[] availabilities;
	private Status status;

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public Experience[] getExperiences() {
		return experiences;
	}

	public void setExperiences(Experience[] experiences) {
		this.experiences = experiences;
	}

	public Availability[] getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(Availability[] availabilities) {
		this.availabilities = availabilities;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
