package se.madev.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class SessionTest{

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private FilterChainProxy filterChain;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
	      .addFilter(filterChain).build();
	}
	
	@Test
	public void testSession() throws Exception {
	    // Login and save the cookie
		RequestBuilder req = formLogin("/login").user("admin").password("root");
		Cookie c = new Cookie("admin", "cookie");
//	    MockHttpServletResponse result = mockMvc.perform(req).andReturn().getResponse().addCookie(c);
	    
	
	    // No cookie; 401 Unauthorized
	    //mockMvc.perform((req) ((ResultActions) get("/")).andExpect(status().isUnauthorized()));
//	    mockMvc.perform(get("")).andExpect(status().isUnauthorized());
//	
//	    // With cookie; 200 OK
//	    mockMvc.perform(get("").cookie(c)).andExpect(status().isOk());
	
	    // Logout, and ensure we're told to wipe the cookie
//	    result = mockMvc.perform(SecurityMockMvcRequestBuilders.logout("/logout")).andReturn().getResponse();
//	    c = result.getCookie("JSESSIONID");
	    assertThat(c.getValue().length()).isNull();
	}
}