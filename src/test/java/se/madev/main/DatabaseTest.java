package se.madev.main;

import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import se.madev.main.integration.UserRepository;
import se.madev.main.model.MyUserDetails;
import se.madev.main.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DatabaseTest{
	
	private User newUser;
	
    @Autowired
    private TestEntityManager testUserEntityManager;
 
    @Autowired
    private UserRepository testUserRepository;
    
    public void initNewUser(int role) {
    	newUser = new User();
    	
    	newUser.setFirstName("Daria");
    	newUser.setLastName("Galal");
    	newUser.setUsername("Darrez");
    	newUser.setPassword("1234");
    	newUser.setEmail("test@testmail.com");
    	newUser.setRole(role);
    	newUser.setDateOfBirth(new Date(0));
    	
    	testUserEntityManager.persist(newUser);
    	testUserEntityManager.flush();
    }
    
    @Test
    public void testNewUserExists() {
    	
    	initNewUser(0);
    	
    	User foundUser = testUserRepository.findByUsername(newUser.getUsername());
    	
    	assertThat(foundUser.getFirstName()).isEqualTo(newUser.getFirstName());
    	assertThat(foundUser.getLastName()).isEqualTo(newUser.getLastName());
    	assertThat(foundUser.getUsername()).isEqualTo(newUser.getUsername());
    	assertThat(foundUser.getPassword()).isEqualTo(newUser.getPassword());
    	assertThat(foundUser.getEmail()).isEqualTo(newUser.getEmail());
    	assertThat(foundUser.getRole()).isEqualTo(newUser.getRole());
    	assertThat(foundUser.getId()).isEqualTo(newUser.getId());
    	assertThat(foundUser.getDateOfBirth()).isEqualTo(newUser.getDateOfBirth());
    	
    }
    
    @Test
    public void testNewUserGrantedAuthority() {
    	
    }
	
    
	
}
