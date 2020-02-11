package se.madev.main;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

/**
 * Integration testing with login/logout
 * @author Group 3
 *
 */

@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration
public class LoginTests {
	
	private final String LOGIN_ERROR = "/login?error"; //error page
	private final String LOGIN_SUCCESS = ""; //default index page
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void loginWithTrueCredentials() throws Exception {
		RequestBuilder req = formLogin("/login").user("admin").password("root"); 		//enter correct user credentials
		mvc.perform(req).andDo(print()).andExpect(redirectedUrl("/")) 					//expect to be redirected to index page after login
		.andExpect(status().isFound()) 													//expect page to be found (Status 302)
		.andExpect(content().string(containsString(LOGIN_SUCCESS))) 					//expect response body to contain empty string
		.andExpect(authenticated().withUsername("admin"));								//expect the user to be authenticated
	}
	
	@Test
	public void loginWithFalseCredentials() throws Exception {
		RequestBuilder req = formLogin("/login").user("badmin").password("broot"); 		//enter false user credentials
		mvc.perform(req).andExpect(redirectedUrl(LOGIN_ERROR)) 							//expect to be redirected to error page
		.andExpect(status().isFound());													//expect page to be found (Status 302)
	}
	
	@Test 
	public void LoginWithTrueCredentialsThenLogout() throws Exception {
		RequestBuilder req = formLogin("/login").user("admin").password("root"); 		//enter correct user credentials
		mvc.perform(req).andExpect(redirectedUrl("/")) 									//expect to be redirected to index page after login
		.andExpect(status().isFound()) 													//expect page to be found (Code 302)
		.andExpect(content().string(containsString(LOGIN_SUCCESS))) 					//expect response body to contain empty string
		.andExpect(authenticated().withUsername("admin")); 
		
		mvc.perform(SecurityMockMvcRequestBuilders.logout("/logout"))
		.andExpect(redirectedUrl("/login"))
		.andExpect(content().string(containsString("")));
	}
	
}
