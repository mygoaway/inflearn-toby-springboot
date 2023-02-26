package tobyspring.config;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;
import tobyspring.config.autoconfig.ServerProperties;

//@MyAutoConfiguration
public class ServerPropertiesConfig {
    @Bean
    ServerProperties serverProperties(Environment environment) {
        return Binder.get(environment).bind("",ServerProperties.class).get();

        /*
        ServerProperties serverProperties = new ServerProperties();

        serverProperties.setContextPath(environment.getProperty("contextPath"));
        serverProperties.setPort(Integer.parseInt(environment.getProperty("port")));

        return serverProperties;
        */

    }
}
