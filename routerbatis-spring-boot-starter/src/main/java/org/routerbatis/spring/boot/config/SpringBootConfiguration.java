package org.routerbatis.spring.boot.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Objects;

@Configuration
@ConditionalOnProperty(prefix = SpringBootConfiguration.DATASOURCE_PREFIX, name = "names")
public class SpringBootConfiguration implements EnvironmentAware {
    public final static String DATASOURCE_PREFIX = "routerbatis.datasource";

    @Autowired
    private RouterConfig routerConfig;

    @Override
    public void setEnvironment(final Environment environment) {
        System.out.println(Arrays.toString(getDataSourceNames(environment)));
    }

    private String[] getDataSourceNames(final Environment environment) {
        String[] groups = Objects.requireNonNull(environment.getProperty(DATASOURCE_PREFIX + ".names")).split(",");
        if (groups.length == 0) {
            throw new BeanInitializationException("groups.length == 0");
        }
        RouterGroupConfig[] groupConfigs = new RouterGroupConfig[groups.length];
        return groups;
    }

    /*@Bean(name = "")
    public DataSource[] setDataSource() {
    }*/

}
