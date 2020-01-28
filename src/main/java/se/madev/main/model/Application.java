package se.madev.main.model;

public class Application {
	private Applicant applicant;
	private Experience[] experiences;
	private Availability[] availabilities;
	private boolean processed;
	
	public Application(Applicant applicant, Experience[] experiences, Availability[] availabilities, boolean processed) {
		this.applicant = applicant;
		this.experiences = experiences;
		this.availabilities = availabilities;
		this.processed = processed;
	}

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

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
