package org.plinovodi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App {

	public static void main (String [] arguments) {
		SpringApplication.run(App.class, arguments);
	}

}
