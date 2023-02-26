package tobyspring.my.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tobyspring.my.boot.hello.HelloController;
import tobyspring.my.boot.hello.HelloDecorator;
import tobyspring.my.boot.hello.SimpleHelloService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloApiTest {

    @Test
    void helloApi() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> result = testRestTemplate.getForEntity(
                "http://localhost:9090/app/hello?name={name}",
                String.class,
                "Spring"
        );

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.TEXT_PLAIN_VALUE)).isTrue();
        assertThat(result.getBody().trim()).isEqualTo("*Hello Spring*");
    }

    @UnitTest
    void simpleHelloService() {
        SimpleHelloService simpleHelloService = new SimpleHelloService();
        String result = simpleHelloService.sayHello("Test");
        assertThat(result).isEqualTo("Hello Test");
    }

    @UnitTest
    void helloDecorator() {
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);
        String result = helloDecorator.sayHello("Test");
        assertThat(result).isEqualTo("*Test*");
    }

    @Test
    void helloController() {
        HelloController helloController = new HelloController(name -> name);
        String result = helloController.hello("Test");
        assertThat(result).isEqualTo("Test");
    }

    @Test
    void helloControllerFailureCaseOne() {
        HelloController helloController = new HelloController(name -> name);
        assertThatThrownBy( () -> helloController.hello(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void helloControllerFailureCaseTwo() {
        HelloController helloController = new HelloController(name -> name);
        assertThatThrownBy( () -> helloController.hello(""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Test
@interface UnitTest {}