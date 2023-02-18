package tobyspring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // 클래스, 인터페이스, ENUM
@Configuration
@ComponentScan // 합성 애노테이션 적용
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {
}
