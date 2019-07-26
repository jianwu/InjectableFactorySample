package com.jsonex.samples.ambientcontext.trace;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j @RequiredArgsConstructor
public class TraceExample implements Runnable {
  final Trace parent;

  public void run() {
    if (parent != null)
      Trace.set(parent);
    @Cleanup Trace trace = Trace.of("run");
    sleep(10);
    method1();
    method2();
    sleep(10);
  }

  public void method1() {
    @Cleanup Trace trace = Trace.of("method1");
    sleep(10);
  }

  public void method2() {
    @Cleanup Trace trace = Trace.of("method2");
    sleep(10);
  }

  public static void main(String[] args) {
    @Cleanup Trace trace = Trace.of("main");
    sleep(10);
    final int NUM_THREAD = 2;
    List<Thread> threads = new ArrayList<>();
    for (int i = 0; i < NUM_THREAD; i++) {
      Thread th = new Thread(new TraceExample(Trace.get()), "Thread:" + i);
      th.start();
      threads.add(th);
      sleep(25);
    }
    threads.forEach(th -> join(th));
    sleep(10);
  }

  @SneakyThrows static void join(Thread e) { e.join(); }
  @SneakyThrows static void sleep(long time) { Thread.sleep(time); }
}
