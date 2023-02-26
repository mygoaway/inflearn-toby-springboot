package tobyspring.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyAutoConfigImportSelector.class) // Enable 이런 기능을 사용하게 해줘 // configuration 파일을 동적으로 빈 등록
public @interface EnableMyAutoConfiguration {
}