package se.madev.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import se.madev.main.integration.RoleRepository;
import se.madev.main.integration.UserRepository;
import se.madev.main.model.MyUserDetails;
import se.madev.main.model.Role;
import se.madev.main.model.User;
import se.madev.main.model.UserAlreadyExistsException;
import se.madev.main.model.Role.*;



@Service
public class UserService implements UserDetailsService {

   @Autowired
   UserRepository userRepository;

   @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
        if(user == null) {
        	throw new UsernameNotFoundException("Not found: " + username);
        }
        
        return new MyUserDetails(user);
    }

    public void registerApplicant(User user) throws UserAlreadyExistsException{
        user.setRole(roleRepository.findByType(Role.Type.APPLICANT));
        
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistsException("A user with the given username already exists!");
        } else if(userRepository.existsByEmail(user.getEmail())){
        	throw new UserAlreadyExistsException("A user with the given email-address already exists!");
        }
        
        userRepository.save(user);
    }

}

