package org.routerbatis.spring.boot.config;

import org.routerbatis.spring.boot.shard.ShardAlgorithm;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

@Configuration
@ConditionalOnProperty(prefix = SpringBootConfiguration.DATASOURCE_PREFIX, name = "names")
public class RouterConfig implements EnvironmentAware {
    public final static String DATASOURCE_PREFIX = "routerbatis.datasource";

    private RouterGroupConfig[] groupConfigs;

    @Override
    public void setEnvironment(Environment environment) {
        String nameString = environment.getProperty("routerbatis.datasource.names");
        if (!StringUtils.hasText(nameString)) {
            throw new BeanInitializationException("!StringUtils.hasText(nameString)");
        }
        String[] names = nameString.split(",");
        groupConfigs = new RouterGroupConfig[names.length];
        for (String name : names) {
            RouterGroupConfig routerGroupConfig = new RouterGroupConfig(DATASOURCE_PREFIX + "." + name + ".driver",
                    (DATASOURCE_PREFIX + "." + name + ".masters.url").split(","),
                    (DATASOURCE_PREFIX + "." + name + ".masters.username").split(","),
                    (DATASOURCE_PREFIX + "." + name + ".masters.password").split(","),
                    (DATASOURCE_PREFIX + "." + name + ".slaves.url").split(","),
                    (DATASOURCE_PREFIX + "." + name + ".slaves.username").split(","),
                    (DATASOURCE_PREFIX + "." + name + ".slaves.password").split(","),
                    getTableName(name, environment));
        }
    }

    private RouterTableConfig[] getTableName(String groupName, Environment environment) {
        String tableNameString = environment.getProperty("routerbatis.datasource" + "." + groupName + ".tables");
        assert tableNameString != null;
        String[] tableNames = tableNameString.split(",");

        RouterTableConfig[] tableConfigs = new RouterTableConfig[tableNames.length];
        for (int i = 0; i < tableNames.length; i++) {
            String tableName = tableNames[i];
            String shardColumns = environment.getProperty("routerbatis.datasource" + "." + groupName + "." + tableName + ".shard-column");
            Class<?> shardAlgorithmClass = null;
            try {
                shardAlgorithmClass = Class.forName("routerbatis.datasource" + "." + groupName + "." + tableName + ".shard-algorithm");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            assert shardColumns != null;
            tableConfigs[i] = new RouterTableConfig(tableName, shardColumns.split(","), shardAlgorithmClass);
        }
        return tableConfigs;
    }
}
