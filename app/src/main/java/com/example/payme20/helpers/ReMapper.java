package com.example.payme20.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used to update the reference of the Key in a map
 */
public enum ReMapper {
    INSTANCE;
    public <K, V> Map<K, V> remapKeys(Map<K, V> fromMap, List<K> list) {
        Map<K, V> returnMap = new HashMap<>();
        for(K k : list) {
            if(fromMap.containsKey(k)) {
                returnMap.put(k, fromMap.get(k));
            }
        }
        return returnMap;
    }
}
