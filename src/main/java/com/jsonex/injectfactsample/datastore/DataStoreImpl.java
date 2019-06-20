package com.jsonex.injectfactsample.datastore;

import com.jsonex.core.type.Identifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DataStoreImpl implements DataStore {
  public DataStoreImpl(String name) {
    // Read data source configuration based on the the name
  }

  @Override public void save(Identifiable obj) { }

  @Override public void update(Identifiable obj) { }

  @Override public <T extends Identifiable> T getById(Object id, Class<T> cls) { return null; }

  @Override public <T extends Identifiable> List<T> find(Predicate<T> predicate, Class<T> cls) {
    return new ArrayList<T>();
  }
}
