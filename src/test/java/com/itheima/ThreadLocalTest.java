package com.itheima;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet() {
        // 提供一个 ThreadLocal 对象
        ThreadLocal threadLocal = new ThreadLocal();

        // 开启两个线程
        new Thread(() -> {
            threadLocal.set("Java");
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        }, "线程 1").start();

        new Thread(() -> {
            threadLocal.set("C++");
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        }, "线程 2").start();
    }

}
