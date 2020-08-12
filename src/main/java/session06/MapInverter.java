package session06;

import java.util.*;

public class MapInverter {
  public static <K, V> Map<V, List<K>> invert(Map<K, V> map) {
    Map<V, List<K>> result = new HashMap<>();
    for (Map.Entry<K, V> entry : map.entrySet()) {
      List<K> list = result.get(entry.getValue());
      if (list == null) {
        list = new ArrayList<>();
        result.put(entry.getValue(), list);
      }
      list.add(entry.getKey());
    }
    return result;
  }
}
