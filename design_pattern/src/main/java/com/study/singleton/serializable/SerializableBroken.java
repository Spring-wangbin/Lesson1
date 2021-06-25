package com.study.singleton.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableBroken {

    public static void main(String[] args) {
        try{
            SerializableSingleton instance = SerializableSingleton.getInstance();

            FileOutputStream fo = new FileOutputStream("serial.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fo);
            oos.writeObject(instance);
            oos.flush();
            fo.close();
            oos.close();

            FileInputStream fis = new FileInputStream("serial.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            SerializableSingleton instance2 = (SerializableSingleton) ois.readObject();
            ois.close();
            fis.close();

            System.out.println(instance);
            System.out.println(instance2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
