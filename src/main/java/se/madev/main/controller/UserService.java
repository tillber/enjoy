package se.madev.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import se.madev.main.integration.UserRepository;
import se.madev.main.model.MyUserDetails;
import se.madev.main.model.User;



@Service
public class UserService implements UserDetailsService {

   @Autowired
   UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
        if(user == null) {
        	throw new UsernameNotFoundException("Not found: " + username);
        }
        
        return new MyUserDetails(user);
    }
}

