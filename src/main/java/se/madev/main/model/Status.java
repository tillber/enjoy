package se.madev.main.model;

import javax.persistence.*;

@Entity
@Table(name="status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    //@Enumerated(EnumType.STRING)
    private String name;

    public Status(){}

   /* public Status(Type type) {
    	this.type = type;
    }*/
    
    public String getName(){
    	return this.name;
    }

    public int getId() { 
    	return this.id;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public void setId(int id) { 
    	this.id = id; 
    }
    
    @Override
	public String toString() {
		return "Status [type=" + name + "]";
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
