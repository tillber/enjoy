package se.madev.main.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

/**
 * This class takes care of all requests from the client view.
 * @author madev
 *
 */
@Controller
public class RequestDispatcher {
	
	@Autowired
	UserService userDetailsService;

	@Autowired
	ApplicationService applicationService;
	
	@RequestMapping(value = "/")
	public String index(Authentication authentication, Model model) {
		try {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			if(authorities.contains(new SimpleGrantedAuthority(Role.Type.APPLICANT.toString()))) {
				return View.REDIRECT + View.APPLICANT;
			} else {
				return View.REDIRECT + View.RECRUITER;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Operation failed!");
			return View.REDIRECT + View.LOGIN;
		}
	}
	
	/**
	 * GET request mapping for the login page.
	 */
	@RequestMapping(value = "/" + View.LOGIN, method = RequestMethod.GET)
    public String getLogin(Model model) {
		try {
			return View.LOGIN;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Operation failed!");
			return View.LOGIN;
		}
    }
	
	/**
	 * GET request mapping for the register page.
	 */
	@RequestMapping(value = "/" + View.REGISTER, method = RequestMethod.GET)
	public String getRegister(Model model) {
		try {
			model.addAttribute("user", new User());
			return View.REGISTER;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Operation failed!");
			return View.REGISTER;
		}
	}
	
	/**
	 * POST request mapping for the register page.
	 * @param user The user to be registered.
	 */
	@RequestMapping(value = "/" + View.REGISTER, method = RequestMethod.POST)
	public String postRegister(@ModelAttribute @Valid User user, BindingResult result, Model model) {
		try {
			if(result.hasErrors()) {
				List<String> errors = new ArrayList<>();
				for (FieldError error : result.getFieldErrors()) {
					errors.add(error.getDefaultMessage());
				}
				model.addAttribute("errors", errors);
				return View.REGISTER;
			}else {
				try {
					userDetailsService.registerApplicant(user);
				} catch (UserAlreadyExistsException e) {
					model.addAttribute("errors", Arrays.asList(e.getMessage()));
					return View.REGISTER;
				}
				return View.REDIRECT + View.LOGIN;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Operation failed!");
			return View.REGISTER;
		}
	}
	
	/**
	 * GET request mapping for the applicant view.
	 */
	@RequestMapping(value = "/" + View.APPLICANT, method = RequestMethod.GET)
	public String getApplicant(Model model) {
		try {
			MyUserDetails user = getAuthenticatedUser();
			model.addAttribute("competences", applicationService.getCompetences());
			model.addAttribute("user", user);
			model.addAttribute("application", new Application());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Operation failed!");
		}
		
		return View.APPLICANT + "/index";
	}
	
	/**
	 * POST request mapping for the applicant view. 
	 * @param application The application to be sent.
	 */
	@RequestMapping(value = "/" + View.APPLICANT, method = RequestMethod.POST)
	public String postApplicant(@ModelAttribute @Valid Application application, BindingResult result, Model model) {
		try {
			MyUserDetails user = getAuthenticatedUser();
			model.addAttribute("user", user);
			model.addAttribute("competences", applicationService.getCompetences());
			application.setApplicant(new User(user));
			applicationService.saveApplication(application);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Operation failed!");
		}
		
		return View.APPLICANT + "/index";
	}
	
	/**
	 * GET request mapping for the recruiter view.
	 */
	@RequestMapping(value = "/" + View.RECRUITER, method = RequestMethod.GET)
	public String getRecruiter(Model model) {
		try {
			MyUserDetails user = getAuthenticatedUser();
			model.addAttribute("user", user);
			
			List<Application> applications = applicationService.getApplications();
			model.addAttribute("applications", applications);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Operation failed!");
		}
		
		return View.RECRUITER + "/index";
	}
	
	/**
	 * GET request mapping for the access denied page.
	 */
	@RequestMapping(value = "/" + View.EXCEPTION_403, method = RequestMethod.GET)
	public String getAccessDenied() {
	    return View.EXCEPTION_403;
	}
	
	/**
	 * Returns the authenticated user.
	 * @return The authenticated user (currently logged in).
	 */
	private MyUserDetails getAuthenticatedUser() {
		return (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
