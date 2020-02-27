package se.madev.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import se.madev.main.model.Role;

@EnableTransactionManagement
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    UserDetailsService userDetailsService;

    /**
     * Adds authentication on a custom implementation of userDetailsService
     * @param auth
     * @throws Exception
     */
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authProvider());
    }

    /**
     * Controls authorization of users in our application.
     * @param http
     * @throws Exception
     */
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
        .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/", true)
            .and()
            .logout().permitAll() //test to permitall
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))            
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)        // set invalidation state when logout
            .deleteCookies("JSESSIONID")
            .and().exceptionHandling().accessDeniedPage("/exceptions/403");
    }

    /**
     * Encodes passwords.
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * 
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
