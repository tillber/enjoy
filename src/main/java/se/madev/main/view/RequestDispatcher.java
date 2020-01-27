package se.madev.main.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestDispatcher {
	
	@GetMapping("/login")
    String getLogin() {
      return "login";
    }
	
	@GetMapping
	String index() {
		return "index";
	}
	
	@GetMapping("/applicant")
	String applicant() {
		return("<h1>Hello Applicant</h1>");
	}
	
	@GetMapping("/recruiter")
	String recruiter() {
		return("<h1>Hello Recruiter</h1>");
	}
	
}
