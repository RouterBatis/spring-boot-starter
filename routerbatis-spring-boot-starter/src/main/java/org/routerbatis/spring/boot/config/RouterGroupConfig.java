package org.routerbatis.spring.boot.config;

import org.routerbatis.spring.boot.shard.ShardAlgorithm;

import java.util.Map;

public class RouterGroupConfig {

    private String driver;

    private String[] mastersUrl;

    private String[] mastersUsername;

    private String[] mastersPassword;

    private String[] slavesUrl;

    private String[] slavesUsername;

    private String[] slavesPassword;

    private RouterTableConfig[] tableConfigs;

    public RouterGroupConfig(String driver, String[] mastersUrl, String[] mastersUsername, String[] mastersPassword, String[] slavesUrl, String[] slavesUsername, String[] slavesPassword, RouterTableConfig[] tableConfigs) {
        this.driver = driver;
        this.mastersUrl = mastersUrl;
        this.mastersUsername = mastersUsername;
        this.mastersPassword = mastersPassword;
        this.slavesUrl = slavesUrl;
        this.slavesUsername = slavesUsername;
        this.slavesPassword = slavesPassword;
        this.tableConfigs = tableConfigs;
    }
}
