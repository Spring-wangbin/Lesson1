package com.study.singleton.threadlocal;

public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> SINGLETON_THREAD_LOCAL =
        new ThreadLocal<ThreadLocalSingleton>(){
            @Override
            protected ThreadLocalSingleton initialValue() {
                return new ThreadLocalSingleton();
            }
        };

    private ThreadLocalSingleton(){}

    public static ThreadLocalSingleton getInstance(){
        return SINGLETON_THREAD_LOCAL.get();
    }
}
