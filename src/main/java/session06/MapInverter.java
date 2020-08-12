package session06;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class MapInverter {
  public static <K, V, C extends Collection<K>> Map<V, C> invert(
      Map<K, V> map, Supplier<Map<V, C>> mapFactory,
      Supplier<C> collectionFactory) {
    return map.entrySet().stream()
        .collect(Collectors.groupingBy(
            entry -> entry.getValue(),
            mapFactory,
            Collectors.mapping(
                entry -> entry.getKey(),
                Collectors.toCollection(collectionFactory)
            )
        ));
  }
  public static <K, V, C extends Collection<K>> Map<V, C> invert(
      Map<K, V> map, Supplier<C> collectionFactory) {
    return invert(map, LinkedHashMap::new, collectionFactory);
  }

  public static <K, V> Map<V, List<K>> invert(Map<K, V> map) {
    return invert(map, ArrayList::new);
  }
}
