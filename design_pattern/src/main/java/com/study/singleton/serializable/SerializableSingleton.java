package com.study.singleton.serializable;

import java.io.Serializable;

public class SerializableSingleton implements Serializable {

    private static SerializableSingleton instance;
    private SerializableSingleton(){}

    public static SerializableSingleton getInstance(){
        if(instance == null){
            instance = new SerializableSingleton();
        }
        return instance;
    }

    private Object readResolve(){
        return instance;
    }
}
