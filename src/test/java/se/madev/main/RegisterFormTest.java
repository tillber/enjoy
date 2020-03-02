package se.madev.main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

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
	
	private final String VALID_DATE = LocalDate.of(1999, 1, 1).toString();
	private final String VALID_FIRST_NAME = "Pelle";
	private final String VALID_LAST_NAME = "John";
	private final String VALID_EMAIL = "hah@aerg.se";
	
	private final String INVALID_DATE = LocalDate.of(2008, 1, 1).toString();
	private final String INVALID_FIRST_NAME = "Pelle!!!";
	private final String INVALID_LAST_NAME = "John!!";
	private final String INVALID_EMAIL = "helloWorld";
	
	private final String USERNAME = "pellan";
	private final String PASSWORD = "1234";
	private final String ROLE = "1";

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
	public void registerWithBadFirstName() throws Exception {
	   MvcResult result =  
			   mockMvc.perform(post("/register")
		            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		           	.param("firstName", INVALID_FIRST_NAME) //set to be invalid (containing illegal characters)
		           	.param("lastName", VALID_LAST_NAME)
		           	.param("email", VALID_EMAIL)
		           	.param("username", USERNAME)
		           	.param("password", PASSWORD)
		           	.param("dateOfBirth", VALID_DATE)
		           	.param("role", ROLE))
	    		.andExpect(status().isOk())
	    		.andExpect(model().attributeHasFieldErrors("user", "firstName"))
	    		.andReturn();
	   String content = result.getResponse().getContentAsString();
	   System.out.println(content);
	}
	
	@Test
	public void registerWithBadLastName() throws Exception {
	   MvcResult result =  
			   mockMvc.perform(post("/register")
		            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		           	.param("firstName", VALID_FIRST_NAME)
		           	.param("lastName", INVALID_LAST_NAME) //set to be invalid (containing illegal characters)
		           	.param("email", VALID_EMAIL)
		           	.param("username", USERNAME)
		           	.param("password", PASSWORD)
		           	.param("dateOfBirth", VALID_DATE)
		           	.param("role", ROLE))
	    		.andExpect(status().isOk())
	    		.andExpect(model().attributeHasFieldErrors("user", "lastName"))
	    		.andReturn();
	   String content = result.getResponse().getContentAsString();
	   System.out.println(content);
	}
	
	@Test
	public void registerWithBadEmail() throws Exception {
	   MvcResult result =  
			   mockMvc.perform(post("/register")
		            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		           	.param("firstName", VALID_FIRST_NAME)
		           	.param("lastName", VALID_LAST_NAME)
		           	.param("email", INVALID_EMAIL) //set to be invalid (bad email format)
		           	.param("username", USERNAME)
		           	.param("password", PASSWORD)
		           	.param("dateOfBirth", VALID_DATE)
		           	.param("role", ROLE))
	    		.andExpect(status().isOk())
	    		.andExpect(model().attributeHasFieldErrors("user", "email"))
	    		.andReturn();
	   String content = result.getResponse().getContentAsString();
	   System.out.println(content);
	}
	
	@Test
	public void registerWithBadDateOfBirth() throws Exception {
	   MvcResult result =  
			   mockMvc.perform(post("/register")
		            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		           	.param("firstName", VALID_FIRST_NAME)
		           	.param("lastName", VALID_LAST_NAME)
		           	.param("email", VALID_EMAIL)
		           	.param("username", USERNAME)
		           	.param("password", PASSWORD)
		           	.param("dateOfBirth", INVALID_DATE) //set to be invalid (Under the age of 15)
		           	.param("role", ROLE))
	    		.andExpect(status().isOk())
	    		.andExpect(model().attributeHasFieldErrors("user", "dateOfBirth"))
	    		.andReturn();
	   String content = result.getResponse().getContentAsString();
	   System.out.println(content);
	}
	
	@Test
	public void registerWithValidCredentials() throws Exception {
	   MvcResult result =  
			   mockMvc.perform(post("/register")
		            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		           	.param("firstName", VALID_FIRST_NAME)
		           	.param("lastName", VALID_LAST_NAME)
		           	.param("email", VALID_EMAIL)
		           	.param("username", USERNAME)
		           	.param("password", PASSWORD)
		           	.param("dateOfBirth", VALID_DATE)
		           	.param("role", ROLE))
	    		.andExpect(status().isOk())
	    		.andReturn();
	   String content = result.getResponse().getContentAsString();
	   System.out.println(content);
	}
	

}
