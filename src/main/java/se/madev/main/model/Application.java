package se.madev.main.model;

public class Application {
	private User applicant;
	private Experience[] experiences;
	private Availability[] availabilities;
	private boolean accepted;
	
	//commitcheck

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

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
