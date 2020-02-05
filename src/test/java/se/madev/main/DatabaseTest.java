package se.madev.main;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest

public class DatabaseTest{
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void contextLoads() {
		mvc.perform(MockMvcRequestBuilders)
	}
	
}
