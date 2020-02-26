package se.madev.main.model;

import javax.persistence.*;

@Entity
@Table(name="application")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@OneToOne
	@JoinColumn(name = "applicant")
	private User applicant;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "application", orphanRemoval = true)
	private Availability availability;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "application", orphanRemoval = true)
	private Experience experience; 
	
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;

	public Application(){
		status = new Status(Status.Type.UNHANDLED);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public Experience getExperience() {
		return this.experience;
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
		return "Application [id=" + id + ", applicant=" + applicant + ", availability=" + availability
				+ ", experience=" + experience + ", status=" + status + "]";
	}
}
