package com.jsonex.injectfactsample.datastore;

import com.jsonex.core.type.Identifiable;
import com.jsonex.core.util.ListUtil;

import java.util.*;
import java.util.function.Predicate;

public class DataStoreMock implements DataStore {
  public DataStoreMock(String name) { }

  Map<Object, Identifiable> store = new HashMap<>();
  @Override public void save(Identifiable obj) { store.put(obj.getId(), obj); }
  @Override public void update(Identifiable obj) { store.put(obj.getId(), obj); }
  @Override public <T extends Identifiable> T getById(Object id, Class<T> t) { return (T)store.get(id); }
  @Override public <T extends Identifiable> List<T> find(Predicate<T> predicate, Class<T> cls) {
    return ListUtil.filter((Collection<T>)store.values(), predicate);
  }
}
