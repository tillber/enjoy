package se.madev.main.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; 
import se.madev.main.integration.UserRepository;



@Service
public class MyUserDetailsService implements UserDetailsService {

   @Autowired
   UserRepository userRepository;
   
   @Autowired
   private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        System.err.println(user.getRole().toString());
       // user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return new MyUserDetails(user);
        //user.map(MyUserDetails::new).get();
    }
}

