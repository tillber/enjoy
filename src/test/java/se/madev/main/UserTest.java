package se.madev.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import se.madev.main.integration.UserRepository;
import se.madev.main.model.Role;
import se.madev.main.model.Role.Type;
import se.madev.main.model.User;

/**
 * TO-DO: Password is still not being encrypted
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureMockMvc
public class UserTest {
	
	private final String[] FIRST_NAMES = {"Daria", "Pelle", "Anton", "Martin", "Felix", "Linus", "Joakim", "Arnold", "George",
			"Gustav", "Helena", "Annika", "Lisa", "Anna", "Måns", "Sören"};
	
	private final String[] LAST_NAMES = {"Galal", "Jönsson", "Heurlin", "Tillberg", "Ståhl", "Berg", "Fridlund", "Schartz", "Coonie",
			"Pettersson", "Wiklund", "Morales", "Wiklund", "Bok", "Dagerklint", "Persson"};
	
	private final String[] USERNAMES = {"Darre", "Pellan", "Antman", "Marre", "Fellpan", "Berget", "Fridis", "Starkis", "Georgie",
			"Gust", "Hellan", "Ankan", "Lill-lisa", "Solrosen", "EkelResan13", "Soptippen"};
	
	private final String[] EMAILS = {"Darre@test.se", "Pellan@test.se", "Antman@test.se", "Marre@test.se", "Fellpan@test.se", 
			"Berget@test.se", "Fridis@test.se", "Starkis@test.se", "Georgie@test.se",
			"Gust@test.se", "Hellan@test.se", "Ankan@test.se", "Lill-lisa@test.se", "Solrosen@test.se", "EkelResan13@test.se", "Soptippen@test.se"};
	
	private final int NUMBER_OF_USERS = USERNAMES.length;
	
	private Role applicant;
	private Role recruiter;
	private User[] users;
	
	private final Type APPLICANT_ROLE = Type.APPLICANT;
	private final Type RECRUITER_ROLE = Type.RECRUITER;
	@Autowired
	private TestEntityManager entityManager;
	 
	@Autowired
	private UserRepository usrRepo;	
	private User[] initNewUsersAndPopulate() {
		initRecRole();
		users = new User[NUMBER_OF_USERS];
		for(int i = 0; i < NUMBER_OF_USERS; i++) {
			users[i] = createUser(FIRST_NAMES[i], LAST_NAMES[i], USERNAMES[i], EMAILS[i]);
		}

		return users;
    }	
	private void initRecRole() {
		recruiter = new Role();
		recruiter.setType(RECRUITER_ROLE);
		applicant = new Role();
		applicant.setType(APPLICANT_ROLE);
		
		entityManager.persistAndFlush(recruiter);
		entityManager.persistAndFlush(applicant);
	}	
	private User createUser(String firstName, String lastName, String username, String email) {
		User newUser = new User();
	   	newUser.setFirstName(firstName);
	   	newUser.setLastName(lastName);
	   	newUser.setEmail(email);
	   	newUser.setDateOfBirth(new Date(0));
    	newUser.setUsername(username);
	   	newUser.setPassword("1234");
	   	if(firstName.charAt(0) == 'A') {
	   		newUser.setRole(recruiter);
	   	}else {
	   		newUser.setRole(applicant);
	   	}
	   	entityManager.persistAndFlush(newUser);
	   	
	   	return newUser;
	}
	
	@Test
	public void createdNewUserExists() {
		User[] users = initNewUsersAndPopulate();
		
		for(int i = 0; i < users.length; i++) {
			User newUser = users[i];
			User found = usrRepo.findByUsername(users[i].getUsername());
			assertThat(found.getFirstName()).isEqualTo(newUser.getFirstName());
			assertThat(found.getLastName()).isEqualTo(newUser.getLastName());
			assertThat(found.getEmail()).isEqualTo(newUser.getEmail());
			assertThat(found.getDateOfBirth()).isEqualTo(newUser.getDateOfBirth());
			assertThat(found.getUsername()).isEqualTo(newUser.getUsername());
			assertThat(found.getPassword()).isEqualTo(newUser.getPassword());
			assertThat(found.getRole()).isEqualTo(newUser.getRole());
			assertThat(found.getId()).isEqualTo(newUser.getId());

		}
	}

}
