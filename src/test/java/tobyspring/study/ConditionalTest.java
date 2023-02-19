package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {

    @Test
    void conditional() {
        // true
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(Config1.class);
        ac.refresh();

        MyBean bean = ac.getBean(MyBean.class);
        Assertions.assertThat(bean).isNotNull();

        // false
        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext();
        ac2.register(Config2.class);
        ac2.refresh();

        Assertions.assertThatThrownBy( () -> ac2.getBean(MyBean.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean { }

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            return (Boolean) annotationAttributes.get("value");
        }
    }
}
