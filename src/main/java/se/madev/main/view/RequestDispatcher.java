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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se.madev.main.controller.ApplicationService;
import se.madev.main.controller.UserService;
import se.madev.main.integration.RoleRepository;
import se.madev.main.integration.UserRepository;
import se.madev.main.model.MyUserDetails;
import se.madev.main.model.Role;
import se.madev.main.model.Role.Type;
import se.madev.main.model.User;
import se.madev.main.model.UserAlreadyExistsException;

@Controller
public class RequestDispatcher {
	
	@Autowired
	UserService userDetailsService;

	@Autowired
	ApplicationService applicationService;
	
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
	
	@GetMapping("/")
	public String index(Model model, HttpServletRequest httpServletRequest) {
		MyUserDetails user = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		if(authorities.contains(new SimpleGrantedAuthority(Role.Type.APPLICANT.toString()))) {
			model.addAttribute("competences", applicationService.getCompetences());
			return "applicant/index";
        } else {
        	return "recruiter/index";
        }
	}
	
	@GetMapping("/applicant")
	String applicant(Model model) {
		MyUserDetails user = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		return "applicant/index";
	}
	
	@GetMapping("/recruiter")
	String recruiter(Model model) {
		MyUserDetails user = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		return "recruiter/index";
	}
	
	@RequestMapping("/exceptions/403")
	public String accessDenied() {
	    return "exceptions/403";
	}
}
