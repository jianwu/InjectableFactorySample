package com.jsonex.samples.ambientcontext.trace;

import com.jsonex.core.factory.TimeProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter @RequiredArgsConstructor @ToString() @Slf4j
public class Trace implements AutoCloseable {
  private static ThreadLocal<Trace> current = ThreadLocal.withInitial(Trace::new);

  @ToString.Exclude
  final Trace parent;
  final String name;
  final long start = TimeProvider.get().getTimeMillis();
  long end;

  public Trace() { this(null, "root"); }

  @Override public void close() {
    end = TimeProvider.get().getTimeMillis();
    if (current.get().getParent() != null)
      current.set(current.get().getParent());
    log.info(this.toString() + ",parent=" + parent.name);
  }

  /** Create a child trace of current trace */
  public static Trace of(String name) {
    Trace result = new Trace(current.get(), name);
    current.set(result);
    return result;
  }

  /** Get current trace */
  public static Trace get() { return current.get(); }

  /** Get current trace */
  public static void set(Trace trace) { current.set(trace); }
}
