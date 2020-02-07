package se.madev.main.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se.madev.main.model.MyUserDetails;
import se.madev.main.model.MyUserDetailsService;
import se.madev.main.model.User;

@Controller
public class RequestDispatcher {
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@GetMapping("/login")
    public String login() {
      return "login";
    }
	
	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String postRegister(@ModelAttribute("user") User user, BindingResult result) {
		userDetailsService.registerApplicant(user);
		return "login";
	}
	
	@GetMapping("/")
	public String index(Model model, HttpServletRequest httpServletRequest) {
		MyUserDetails user = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		
		if(httpServletRequest.isUserInRole("APPLICANT")) {
			return "applicant/index";
        } else {
        	return "recruiter/index";
        }
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
