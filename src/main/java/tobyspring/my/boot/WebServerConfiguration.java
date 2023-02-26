package tobyspring.my.boot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

//@Configuration(proxyBeanMethods = false)
public class WebServerConfiguration {
    // 커스텀(사용자 구성정보) 빈 등록
    @Bean
    ServletWebServerFactory customerWebServerFactory() {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setPort(9090);
        return serverFactory;
    }
}
