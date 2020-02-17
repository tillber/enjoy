package se.madev.main;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTests {
	
	//private final Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
	private final String LOGIN_ERROR_URL = "/login?error";
	private final String INVALID_CREDENTIALS = "Invalid Credentials!";
	private final String USR = "master";
	private final String PSW = "pleb";//encoder.encode((CharSequence)"1234");
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context)
	    					.apply(springSecurity())
	    					.alwaysDo(print())
	    					.build();
	}
	
	@Test
	public void loginWithCorrectCredentialsApplicant() throws Exception {
		RequestBuilder req = formLogin("/login").user(USR).password(PSW);
		mvc.perform(req)
		.andExpect(redirectedUrl("applicant/index"))
		.andExpect(status().isFound());
	}
	
	@Test
	public void loginAvailableForAll() throws Exception {
	  mvc.perform(get("/login"))
	     .andExpect(status().isOk());
	} 
	
	@Test
	public void invalidLoginDenied() throws Exception {
		mvc.perform(formLogin().password("invalid"))
			.andExpect(status().isFound())
	        .andExpect(redirectedUrl(LOGIN_ERROR_URL))
	        .andExpect(unauthenticated());

//	    mvc.perform(get(LOGIN_ERROR_URL))
//	         .andExpect(content().string(containsString(INVALID_CREDENTIALS)));
	}
	
}
