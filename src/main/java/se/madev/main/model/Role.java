package se.madev.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private long id;
	
	@Column(name = "type", unique = true)
	@Enumerated(EnumType.STRING)
	private Type type;
	
	public Role() {}
	
	public Role(Type type) {
		this.type = type;
	}

	public Role(int id, Type type) {
		this.id = id;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type.toString();
	}
	
	public enum Type {
		ROLE_APPLICANT {
			public String toString() {
				return "ROLE_APPLICANT";
			}
		},
		ROLE_RECRUITER {
			public String toString() {
				return "ROLE_RECRUITER";
			}
		}
	}
}
