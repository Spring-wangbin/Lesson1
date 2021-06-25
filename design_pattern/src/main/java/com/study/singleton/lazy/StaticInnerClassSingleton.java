package com.study.singleton.lazy;

/**
 * 静态内部类，在调用getInstance()方法的时候，才会加载
 * 优点：代码优化，利用java本身语法特点
 * 缺点：可以被反射破坏
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton(){
        if(LazyHolder.instance != null){
            throw new RuntimeException("不允许非法访问");
        }
    }

    public static StaticInnerClassSingleton getInstance(){
        return LazyHolder.instance;
    }

    private static class LazyHolder{
        public static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }
}
