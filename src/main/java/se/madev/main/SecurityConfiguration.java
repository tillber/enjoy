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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
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
     * Configures authentication on a custom implementation of userDetailsService and adds another layer
     * of authentication for password encoding(?)
     * @param auth in-memory authentication builder
     * @throws Exception
     */
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService);
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
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))            
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)        // set invalidation state when logout
            .deleteCookies("JSESSIONID")
            .and().exceptionHandling().accessDeniedPage("/exceptions/403");
    }

    /**
     * Encodes passwords using BCrypt.
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder("test", 10000, 50);
    }
    
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
