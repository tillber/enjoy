package se.madev.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

<<<<<<< HEAD
import se.madev.main.controller.UserService;
import se.madev.main.model.Role;
import se.madev.main.model.Role.Type;
=======
import se.madev.main.model.MyUserDetailsService;
>>>>>>> 872c5535a77c8bb5fbcbf0ada1f1c960ad7ee544

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    UserDetailsService userDetailsService;

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        	.antMatchers("/register").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/").authenticated()
            .antMatchers("/recruiter/**").hasAuthority(Role.Type.RECRUITER.toString())
            .antMatchers("/applicant/**").hasAuthority(Role.Type.APPLICANT.toString())
            .anyRequest().authenticated()
            .and()
        .formLogin().loginPage("/login").permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))            
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)        // set invalidation state when logout
            .deleteCookies("JSESSIONID")
            .and().exceptionHandling().accessDeniedPage("/exceptions/403");
    }
    
    //WARNIG!
    //BCrypt algorithm generates a String of length 50
    //so we need to make sure that the password will be stored in a column that can accommodate it.
    //Also, only encode a password once due to each call will generate a different salt
    @Bean
    public PasswordEncoder getPasswordEncoder() {
    	return new Pbkdf2PasswordEncoder("secret", 10000, 50);
    }
}
