package se.madev.main.model;

import javax.persistence.*;

/**
 * This entity describes the status of each application in the system.
 * @author madev
 *
 */
@Entity
@Table(name="status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    @Enumerated(EnumType.STRING)
    private Type type;

    public Status(){}

    public Status(Type type) { 
    	this.type = type;
    }
    
    public Type getType(){ 
    	return this.type; 
    }

    public int getId() { 
    	return this.id;
    }

    public void setType(Type type) { 
    	this.type = type; 
    }

    public void setId(int id) { 
    	this.id = id; 
    }
    
    @Override
	public String toString() {
		return "Status [type=" + type + "]";
	}

	public enum Type {
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
