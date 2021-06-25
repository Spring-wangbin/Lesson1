package com.study.singleton.register;

import java.io.Serializable;

/**
 * 注册式单例 枚举
 * 优点：线程安全(类加载即创建)，反射无法破坏(枚举类底层 禁止反射调用)，代码优雅
 * 缺点：类似饿汉式，类加载即创建，可能内存浪费（大批量创建对象）
 */
public enum EnumSingleton implements Serializable {
    instance;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return instance;
    }
}
