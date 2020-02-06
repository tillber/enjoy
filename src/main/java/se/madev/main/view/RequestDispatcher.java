package se.madev.main.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se.madev.main.model.MyUserDetails;

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
		MyUserDetails user = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.err.println(user.getRole().toString());
		model.addAttribute("user", user);
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
	
	@RequestMapping("/exceptions/403")
	public String accessDenied() {
	    return "exceptions/403";
	}
}
