package se.madev.main.view;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import se.madev.main.model.ApplicationStatus;
import se.madev.main.model.MyUserDetails;
import se.madev.main.model.User;

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
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("user", (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
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
