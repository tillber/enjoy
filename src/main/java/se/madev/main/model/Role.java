package se.madev.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class {@code Role} depicts a role and is applied to users, which signifies different authorities in the application.
 * The class is a JPA entity representing data that is persisted to the "roles" table in the PostgreSQL database. 
 * @author madev
 */
@Entity
@Table(name = "roles")
public class Role {
	/**
	 * Represents the role's id in the database (used for referencing).
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private long id;
	
	/**
	 * Represents the role's type/title.
	 */
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	public Role() {}
	
	/**
	 * Creates a role with the specified type.
	 */
	public Role(Type type) {
		this.type = type;
	}
	
	/**
	 * Creates a role with the specified id and type.
	 */
	public Role(int id, Type type) {
		this.id = id;
		this.type = type;
	}
	
	/**
	 * Returns the role's id.
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets the role's id.
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Returns the role's type.
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Sets the role's type.
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", type=" + type + "]";
	}
	
	/**
	 * Enum {@code Type} specifies the types that the class {@code Role} can obtain.
	 * @author madev
	 *
	 */
	public enum Type {
		APPLICANT {
			public String toString() {
				return "APPLICANT";
			}
		},
		RECRUITER {
			public String toString() {
				return "RECRUITER";
			}
		}
	}
}
