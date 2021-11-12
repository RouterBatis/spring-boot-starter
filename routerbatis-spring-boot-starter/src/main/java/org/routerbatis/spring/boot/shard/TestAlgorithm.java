package org.routerbatis.spring.boot.shard;

public class TestAlgorithm implements ShardAlgorithm{
    @Override
    public String generateTableSuffix(Object... args) {
        String suffix = "";
        if (args[0] instanceof String){
            String s = (String) args[0];
            suffix = (s).substring(s.lastIndexOf("T"));
        }
        return suffix;
    }
}
