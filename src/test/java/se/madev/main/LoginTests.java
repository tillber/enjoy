package se.madev.main;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void loginWithCorrectCredentialsApplicant() throws Exception {
		RequestBuilder req = formLogin("/login").user("admin").password("root");
		mvc.perform(req).andExpect(redirectedUrl("applicant/index")).andExpect(status().isFound());
	}
	
//	@Test
//	public void loginWithCorrectCredentialsRecruiter() throws Exception {
//		RequestBuilder req = formLogin().user("USERNAME").password("PASSWORD");
//		mvc.perform(req).andExpect(redirectedUrl("recruiter/index")).andExpect(status().isFound());
//	}
	
}
