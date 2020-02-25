package se.madev.main.model;

import javax.persistence.*;

@Entity
@Table(name="Experience")
public class Experience {

	@Id
	@Column(name="id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="applicationid")
	private Application application;

	@Column(name="competenceid")
	private String areaOfExpertise;

	@Column(name="yearsofexp")
	private int numberOfYears;

	public Experience(){}
	
	public int getId(){ return id; }

	public void setId(int id) { this.id = id; }

	public Application getApplication(){ return application; }

	public void setApplicationId(Application application){ this.application = application; }

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

	@Override
	public String toString() {
		return "Experience [areaOfExpertise=" + areaOfExpertise + ", numberOfYears=" + numberOfYears + "]";
	}
}
