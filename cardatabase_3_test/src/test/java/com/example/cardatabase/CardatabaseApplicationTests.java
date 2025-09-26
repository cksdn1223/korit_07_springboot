package com.example.cardatabase;

import com.example.cardatabase.web.CarController;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CardatabaseApplicationTests {
	@Autowired
	private CarController controller;

	@Test
	@DisplayName("First Example Test Case")
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
}
