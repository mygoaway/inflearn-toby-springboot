package tobyspring.my.boot;

import org.springframework.boot.SpringApplication;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class MyBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyBootApplication.class, args);
	}
}

