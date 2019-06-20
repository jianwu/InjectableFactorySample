package com.jsonex.injectfactsample.datastore;

import com.jsonex.core.factory.InjectableFactory;
import com.jsonex.core.factory.InjectableFactory.CachePolicy;
import com.jsonex.core.type.Identifiable;

import java.util.List;
import java.util.function.Predicate;

public interface DataStore {
  InjectableFactory<String, DataStore> factory = InjectableFactory.of(DataStoreImpl::new, CachePolicy.GLOBAL);
  //InjectableFactory<String, DataStore> factory = InjectableFactory.of(name -> new DataStoreImpl(name), CachePolicy.GLOBAL);

  void save(Identifiable obj);
  void update(Identifiable obj);
  <T extends Identifiable> T getById(Object id, Class<T> t);
  <T extends Identifiable> List<T> find(Predicate<T> predicate, Class<T> cls);
}
