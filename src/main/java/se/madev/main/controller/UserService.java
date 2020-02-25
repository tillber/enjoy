package se.madev.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;

import se.madev.main.integration.ApplicationRepository;
import se.madev.main.integration.RoleRepository;
import se.madev.main.integration.UserRepository;
import se.madev.main.model.*;

import org.springframework.transaction.annotation.Transactional;

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

        Application application = applicationRepository.findByApplicant(user);
        Experience experience = application.getExperience();
        System.out.println(experience);
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

