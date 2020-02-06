package se.madev.main;

import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import se.madev.main.integration.UserRepository;
import se.madev.main.model.Role;
import se.madev.main.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration
public class DatabaseTest{
	
	private User newUser;
	private Role role;
	private final String APPLICANT = "ROLE_APPLICANT";
	private final String RECRUITER = "ROLE_RECRUITER";
	
    @Autowired
    private TestEntityManager testUserEntityManager;
 
    @Autowired
    private UserRepository testUserRepository;
    
    
    private void init(int id) {
		initRole(1);
    	initNewUser();
    }
    
    private void initNewUser() {
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
    
    private void initRole(int id) throws IndexOutOfBoundsException{
    	role = new Role();
    	switch(id) {
    	case(1):
    		role.setName(APPLICANT);
    	break;
    	case(2):
    		role.setName(RECRUITER);
    	break;
    	default:
    		throw new IndexOutOfBoundsException();
    	}
    	testUserEntityManager.persistAndFlush(role);
    }
    
    
    //test if newly created user's credentials matches database query result
    @Test
    public void testNewUserExists() {
    	//initiate a new user with applicant role
    	init(1);
    	//query for the newly created user and store it in foundUser
        User foundUser = testUserRepository.findByUsername(newUser.getUsername());
        
        //first name
       	assertThat(foundUser.getFirstName()).isEqualTo(newUser.getFirstName());
       	
       	//last name
       	assertThat(foundUser.getLastName()).isEqualTo(newUser.getLastName());
       	
       	//username
       	assertThat(foundUser.getUsername()).isEqualTo(newUser.getUsername());
       	
       	//password hash match
       	assertThat(foundUser.getPassword()).isEqualTo(newUser.getPassword());
       	
       	//e-mail
       	assertThat(foundUser.getEmail()).isEqualTo(newUser.getEmail());
       	
       	//role
       	assertThat(foundUser.getRole()).isEqualTo(newUser.getRole());
       	
       	//id
       	assertThat(foundUser.getId()).isEqualTo(newUser.getId());
       	
       	//date of birth
       	assertThat(foundUser.getDateOfBirth()).isEqualTo(newUser.getDateOfBirth());
    	
       	System.out.println("User id/role:"+foundUser.getId()+"/"+foundUser.getRole());
       	System.out.println("User password:"+foundUser.getPassword());
    }
    
//    @Test
//    @WithMockUser
//    public void testNewUserGrantedAuthority() {
//    	String msg = messageService.getMessage();
//    }
	
    
	
}
