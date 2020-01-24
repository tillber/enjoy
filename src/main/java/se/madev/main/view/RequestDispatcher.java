package se.madev.main.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestDispatcher {
	
	@GetMapping("/")
    String home() {
      return "login";
    }
	
	@GetMapping("/login")
	String login() {
		return "login";
	}
	
}
