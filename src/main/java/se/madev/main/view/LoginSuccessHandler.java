package se.madev.main.view;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import se.madev.main.model.Role;

/**
 * This Spring Component handles the redirect after a successful authentication.
 * @author tillber
 *
 */
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	/**
	 * Takes care of the redirect after a successful login/authentication. 
	 */
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String target = determineTarget(authentication);
		
		if(response.isCommitted()) {
			System.out.println("Can't redirect!");
			return;
		}
		
		redirectStrategy.sendRedirect(request, response, target);
	}
	
	/**
	 * Determines the next view for the user, based on which role the user obtains.
	 * @return The correct target view to show after login.
	 */
	protected String determineTarget(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		if(authorities.contains(new SimpleGrantedAuthority(Role.Type.APPLICANT.toString()))) {
			return View.APPLICANT_VIEW;
        } else {
        	return View.RECRUITER_VIEW;
        }
	}
}
