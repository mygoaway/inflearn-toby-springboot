package tobyspring.my.boot;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MyBootApplication.class)
@TestPropertySource("classpath:/application.properties")
@Transactional // Test 에서 반영되면 전체 트랜잭션, 결과는 롤백
public @interface HelloBootTest {
}
