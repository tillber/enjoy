package se.madev.main.model;

public class Experience {
	private String areaOfExpertise;
	private int numberOfYears;
	
	public Experience(String areaOfExpertise, int numberOfYears) {
		this.areaOfExpertise = areaOfExpertise;
		this.numberOfYears = numberOfYears;
	}

	public String getAreaOfExpertise() {
		return areaOfExpertise;
	}

	public void setAreaOfExpertise(String areaOfExpertise) {
		this.areaOfExpertise = areaOfExpertise;
	}

	public int getNumberOfYears() {
		return numberOfYears;
	}

	public void setNumberOfYears(int numberOfYears) {
		this.numberOfYears = numberOfYears;
	}
}
