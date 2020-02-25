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
    private String name;

    public Status(){}

    public Status(String name) { this.name = name;
    }
    public String getName(){ return this.name; }

    public int getId() { return this.id; }

    public void setName(String name) { this.name = name; }

    public void setId(int id) { this.id = id; }
}
