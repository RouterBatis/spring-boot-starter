package org.routerbatis.spring.boot.shard;

public interface ShardAlgorithm {

    String generateTableSuffix(Object... args);

}
