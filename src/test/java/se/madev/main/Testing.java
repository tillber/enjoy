package se.madev.main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import se.madev.main.view.RequestDispatcher;

@SpringBootTest
class Testing {

	@Autowired
	private RequestDispatcher controller;
	
	@Test
	public void controllerIsNotNull() throws Exception{
		assertThat(controller).isNotNull();
	}

}
