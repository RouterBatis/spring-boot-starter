package org.routerbatis.spring.boot.config;

import org.routerbatis.spring.boot.shard.ShardAlgorithm;

public class RouterTableConfig {

    private String tableName;
    private String[] shardColumn;
    private Class<?> shardAlgorithmClass;

    public RouterTableConfig(String tableName, String[] shardColumn, Class<?> shardAlgorithmClass) {
        this.tableName = tableName;
        this.shardColumn = shardColumn;
        this.shardAlgorithmClass = shardAlgorithmClass;
    }

    public String getTableName() {
        return tableName;
    }

    public String[] getShardColumn() {
        return shardColumn;
    }


}
