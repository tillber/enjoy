package se.madev.main.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.madev.main.controller.ApplicationService;
import se.madev.main.controller.UserService;
import se.madev.main.model.Application;
import se.madev.main.model.MyUserDetails;
import se.madev.main.model.Role;
import se.madev.main.model.User;
import se.madev.main.model.UserAlreadyExistsException;

@Controller
public class RequestDispatcher {
	
	@Autowired
	UserService userDetailsService;

	@Autowired
	ApplicationService applicationService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
		return "login";
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegister(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(@ModelAttribute @Valid User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			model.addAttribute("errors", errors);
			return "register";
		}else {
			try {
				userDetailsService.registerApplicant(user);
			} catch (UserAlreadyExistsException e) {
				model.addAttribute("errors", Arrays.asList(e.getMessage()));
				return "register";
			}
			return "login";
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest httpServletRequest) {
		MyUserDetails user = getAuthenticatedUser();
		model.addAttribute("user", user);
		
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		if(authorities.contains(new SimpleGrantedAuthority(Role.Type.APPLICANT.toString()))) {
			model.addAttribute("competences", applicationService.getCompetences());
			model.addAttribute("application", new Application());
			return "applicant/index";
        } else {
        	return "recruiter/index";
        }
	}
	
	@RequestMapping(value = "/applicant", method = RequestMethod.GET)
	public String applicant(Model model) {
		MyUserDetails user = getAuthenticatedUser();
		model.addAttribute("user", user);
		model.addAttribute("application", new Application());
		return "applicant/index";
	}
	
	@RequestMapping(value = "/applicant", method = RequestMethod.POST)
	public String postApplication(@ModelAttribute @Valid Application application, BindingResult result, Model model) {
		MyUserDetails user = getAuthenticatedUser();
		model.addAttribute("user", user);
		
		application.setApplicant(new User(user));
		System.err.println(application);
		
		return "applicant/index";
	}
	
	@RequestMapping(value = "/recruiter", method = RequestMethod.GET)
	public String recruiter(Model model) {
		MyUserDetails user = getAuthenticatedUser();
		model.addAttribute("user", user);
		return "recruiter/index";
	}
	
	@RequestMapping(value = "/exceptions/403", method = RequestMethod.GET)
	public String accessDenied() {
	    return "exceptions/403";
	}
	
	private MyUserDetails getAuthenticatedUser() {
		return (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
