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
public class MyUserDetailsService implements UserDetailsService {

    private final int applicantRoleID = 1;

   @Autowired
   UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        return new MyUserDetails(user);
    }

    public void registerApplicant(User user){
        user.setRole(applicantRoleID);
        if(userRepository.findByUsername(user.getUsername()) != null){
            //throw exception
        }
        if(userRepository.findByEmail(user.getEmail()) != null){
            //throw exception
        }
        userRepository.save(user);
    }

}

