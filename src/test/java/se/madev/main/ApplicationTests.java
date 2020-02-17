package se.madev.main;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {
	
	private final String LOCALHOST = "http://localhost";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void returnLoginPage() throws Exception {
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("login")));
	}
	
	@Test
	public void returnRegisterPage() throws Exception {
		this.mockMvc.perform(get("/register")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("register")));
	}
	
	@Test
	public void returnLoginPageOnDefault() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isFound())
				.andExpect(content().string(containsString("")));
	}
	
	@Test
	public void returnLoginPageWhenUnauthorizedApplicant() throws Exception {
		this.mockMvc.perform(get("/applicant")).andDo(print()).andExpect(status().isFound())
				.andExpect(redirectedUrl(LOCALHOST+"/login"));
	}
	
	@Test
	public void returnLoginPageWhenUnauthorizedRecruiter() throws Exception {
		this.mockMvc.perform(get("/recruiter")).andDo(print()).andExpect(status().isFound())
				.andExpect(redirectedUrl(LOCALHOST+"/login"));
	}

}
