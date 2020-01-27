package se.madev.main.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestDispatcher {
		
	@GetMapping()
	String login() {
		return "login";
	}
	
}
