package org.routerbatis.spring.boot.shard;

import java.util.HashMap;

public class AlgorithmContainer {

    private HashMap<String,ShardAlgorithm> algorithmHashMap;

    public AlgorithmContainer(HashMap<String, ShardAlgorithm> algorithmHashMap) {
        this.algorithmHashMap = algorithmHashMap;
    }

    public ShardAlgorithm getShardAlgorithm(String router){
        return algorithmHashMap.get(router);
    }
}
