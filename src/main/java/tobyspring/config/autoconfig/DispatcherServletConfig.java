package tobyspring.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration // 안적어도 적용이 안되는거 아님
public class DispatcherServletConfig {
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
}
