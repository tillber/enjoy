package se.madev.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;

//<<<<<<< Updated upstream
import se.madev.main.integration.ApplicationRepository;
import se.madev.main.integration.RoleRepository;
import se.madev.main.integration.UserRepository;
import se.madev.main.model.*;
//=======

import se.madev.main.integration.*;
import se.madev.main.integration.DbMigration.MigrationHandler;
import se.madev.main.model.MyUserDetails;
import se.madev.main.model.Role;
import se.madev.main.model.User;
import se.madev.main.model.UserAlreadyExistsException;
import se.madev.main.integration.RoleRepository;
import se.madev.main.integration.UserRepository;

//>>>>>>> Stashed changes

import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Handles authentication and registration of users
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class UserService implements UserDetailsService {



    @Autowired
	UserRepository userRepository;
	
	@Autowired
    RoleRepository roleRepository;

	@Autowired
    ApplicationRepository applicationRepository;

	@Autowired
    CompetenceRepository competenceRepository;

	@Autowired
    StatusRepository statusRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

    /**
     * Locates a specific user based on Username and returns a fully populated user record.
     * @param username
     * @return MyUserDetails Object
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
        if(user == null) {
        	throw new UsernameNotFoundException("Not found: " + username);
        }

/*<<<<<<< Updated upstream
        Application application = applicationRepository.findByApplicant(user);
        Experience experience = application.getExperience();
        System.out.println(experience);
=======*/
        try {
            MigrationHandler migrate = new MigrationHandler(roleRepository, applicationRepository, statusRepository, userRepository, competenceRepository, passwordEncoder);
            migrate.Migrate();
        } catch (URISyntaxException e) {
            System.err.println("URI ECEPTION");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL EXCEPTION");
            e.printStackTrace();
        }
        /*User testuser = userRepository.findById(6);
        Application app = new Application(testuser);
        Status status = statusRepository.findById(1);
        app.setStatus(status);
        applicationRepository.save(app);
        Competence competence = competenceRepository.findById(1);
        Experience experience = new Experience(app, competence, 3);
        Date fromdate = new Date(10/10/2020);
        Date todate = new Date(10/10/2021);
        Availability availability = new Availability(app, fromdate, todate);
        app.setExperience(experience);
        app.setAvailability(availability);
        applicationRepository.save(app);*/

        //Application application = applicationRepository.findByApplicant(user);
        //Experience experience = application.getExperience();
        //System.out.println(experience);
//>>>>>>> Stashed changes
        return new MyUserDetails(user);
    }

    /**
     * Stores a new unique applicant user into the database.
     * @param user
     * @throws UserAlreadyExistsException
     */
    public void registerApplicant(User user) throws UserAlreadyExistsException{

    	user.setRole(roleRepository.findByType(Role.Type.APPLICANT));
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistsException("A user with the given username already exists!");
        } else if(userRepository.existsByEmail(user.getEmail())){
        	throw new UserAlreadyExistsException("A user with the given email-address already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}

