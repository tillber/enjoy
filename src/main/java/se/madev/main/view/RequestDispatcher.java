package se.madev.main.view;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestDispatcher {
	
	@GetMapping("/login")
    public String login() {
      return "login";
    }
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	/*
	@GetMapping
	String index() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
		
		for (GrantedAuthority authority : authorities) {
			if(authority.getAuthority().equals("RECRUITER")) {
				return "recruiter/index";
			}
		}
		
		return "applicant/index";
	}*/
	
	@GetMapping("/")
	public String index() {
		return "applicant/index";
	}
	
	@GetMapping("/applicant")
	String applicant() {
		return "applicant/index";
	}
	
	@GetMapping("/recruiter")
	String recruiter() {
		return "recruiter/index";
	}
	
}
