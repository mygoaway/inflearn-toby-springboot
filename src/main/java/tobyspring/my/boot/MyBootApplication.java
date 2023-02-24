package tobyspring.my.boot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class MyBootApplication {

	/*
	@Bean
	ApplicationRunner applicationRunner(Environment environment) {
		return args -> {
			String name = environment.getProperty("my.name");
			System.out.println("my.name: " + name);
		};
	}
	*/

	public static void main(String[] args) {
		SpringApplication.run(MyBootApplication.class, args);
	}
}

