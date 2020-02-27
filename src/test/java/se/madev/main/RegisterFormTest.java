package se.madev.main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RegisterFormTest {
	
	private final String LOCALHOST = "http://localhost";
	private final String dateOfBirth = new Date(1).toString();
	private final String firstName = "Pelle";
	private final String lastName = "John";
	private final String email = "hah@aerg.se";
	private final String username = "pellan";
	private final String password = "1234";
	private final String role = "1";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before()
	public void setup()
	{
	    //Init MockMvc Object
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void registerWithBadInput() throws Exception {
	   MvcResult result =  
			   mockMvc.perform(post("/register")
	            	.contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            	.param("firstName", firstName)
	            	.param("lastName", lastName)
	            	.param("email", email)
	            	.param("username", username)
	            	.param("password", password)
	            	.param("dateOfBirth", dateOfBirth)
	            	.param("role", role))
	    		.andExpect(status().isOk())
	    		.andReturn();
	   String content = result.getResponse().getContentAsString();
	   System.out.println(content);
	}
	

}
