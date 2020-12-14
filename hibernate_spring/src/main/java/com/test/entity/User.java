package com.test.entity;

import org.hibernate.annotations.Entity;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    private int id;
    private String sex;
    private int age;
    private String user_name;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", user_name='"  +user_name+ '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
