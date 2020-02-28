package se.madev.main;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {
	
	private final String USR = "noob";
	private final String PSW = "plebb";
	
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
	public void fillFormDetailsCorrect() throws Exception {
		RequestBuilder login = formLogin("/login").user(USR).password(PSW);
		mvc.perform(login).andExpect(redirectedUrl("/home")).andExpect(status().isFound());
		
	}

}
