package com.study.singleton.lazy;

public class ExecutorRun implements Runnable {
    @Override
    public void run() {
        LazySingleton singleton = null;
        try {
            singleton = LazySingleton.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(singleton);
    }
}
