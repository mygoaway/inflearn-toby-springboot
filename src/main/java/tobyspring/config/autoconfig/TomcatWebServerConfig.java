package tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

// 톰캣 또는 제티 중 라이브러리가 포함 여부를 확인하고 해당 웹서버를 띄운다.
@MyAutoConfiguration // 안적어도 됨
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@EnableMyConfigurationProperties(ServerProperties.class)
public class TomcatWebServerConfig {
    /*
    @Value("${contextPath}")
    String contextPath;
    */

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean // ServletWebServerFactory 등록된게 없는 경우 빈 등록
    public ServletWebServerFactory servletWebServerFactory(ServerProperties serverProperties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setContextPath(serverProperties.getContextPath());
        factory.setPort(serverProperties.getPort());
        return factory;
    }
}
