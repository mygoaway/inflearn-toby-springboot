package tobyspring.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class PropertyPlaceHolderConfig {
    // @Value를 사용하기 위한 설정
    @Bean
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
