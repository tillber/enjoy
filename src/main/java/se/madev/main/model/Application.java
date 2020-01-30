package se.madev.main.model;

public class Application {
	private Applicant applicant;
	private Experience[] experiences;
	private Availability[] availabilities;
	private boolean accepted;

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
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

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
