package se.madev.main.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User implements Serializable{

    @Id
    @Column(name="userid")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
}