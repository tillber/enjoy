package se.madev.main.model;

import javax.persistence.*;

/**
 * This class depicts experiences in applications, combining an area of expertise (competence) and the number of years in the field.
 * @author madev
 *
 */
@Entity
@Table(name="Experience")
public class Experience {

	@Id
	@Column(name="id")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="applicationid")
	private Application application;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="competenceid")
	private Competence areaOfExpertise;

	@Column(name="yearsofexp")
	private int numberOfYears;

	public Experience(){}
	
	public int getId(){ 
		return id; 
	}

	public void setId(int id) { 
		this.id = id; 
	}

	public Application getApplication(){ 
		return application; 
	}

	public void setApplicationId(Application application){ 
		this.application = application; 
	}

	public Competence getAreaOfExpertise() {
		return areaOfExpertise;
	}

	public void setAreaOfExpertise(Competence areaOfExpertise) {
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
		return "Experience [application=" + application.getId() + ", areaOfExpertise=" + areaOfExpertise.getName() + ", numberOfYears=" + numberOfYears + "]";
	}
}