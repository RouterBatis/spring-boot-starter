package org.routerbatis.spring.boot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Objects;

@Configuration
@ConditionalOnProperty(prefix = SpringBootConfiguration.DATASOURCE_PREFIX, name = "names")
public class SpringBootConfiguration implements EnvironmentAware {
    public final static String DATASOURCE_PREFIX = "routerbatis.datasource";

    @Override
    public void setEnvironment(final Environment environment) {
        System.out.println(Arrays.toString(getDataSourceNames(environment)));
    }

    private String[] getDataSourceNames(final Environment environment) {
        return Objects.requireNonNull(environment.getProperty(DATASOURCE_PREFIX + ".names")).split(",");
    }


}
