package com.study.singleton.lazy;

import java.lang.reflect.Constructor;

public class ReflectBroken {
    public static void main(String[] args) {
        try {
            Class clazz = StaticInnerClassSingleton.class;
            Constructor c = clazz.getDeclaredConstructor();
            c.setAccessible(true);
            Object o1 = c.newInstance();
            System.out.println(o1);
            Object o2 = c.newInstance();
            System.out.println(o2);
            System.out.println(o1 == o2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
