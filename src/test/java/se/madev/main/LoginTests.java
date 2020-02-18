package se.madev.main;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Integration and unit tests with auto-configured MockMvc.
 * 
 * 
 * @author DariaGalal
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTests {
	
	private final String LOGIN_ERROR_URL = "/login?error";

	private final String USR_REC = "admin";
	private final String PSW_REC = "root";
	
	private final String USR_APL = "noob";
	private final String PSW_APL = "plebb";
	
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
	public void loginAsRecruiter() throws Exception {
		RequestBuilder req = formLogin("/login").user(USR_REC).password(PSW_REC);
		mvc.perform(req)
		.andExpect(redirectedUrl("/"))
		.andExpect(content().string(containsString("recruiter/index")))
		.andExpect(status().isFound())
		.andExpect(authenticated());
	}
	
	@Test
	public void loginAsApplicant() throws Exception {
		RequestBuilder req = formLogin("/login").user(USR_APL).password(PSW_APL);
		mvc.perform(req)
		.andDo(print())
		.andExpect(redirectedUrl("/"))
		.andExpect(content().string(containsString("applicant/index")))
		.andExpect(status().isFound())
		.andExpect(authenticated());
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
