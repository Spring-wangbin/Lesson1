package com.study.singleton.register;

enum Color {
    red,blue,yellow;

    private Color(){
        System.out.println("Constructor called for : " + this.toString());
    }
}

public class Test{
    public static void main(String[] args) {
        EnumSingleton e = EnumSingleton.instance;
        e.setData("sadf");
    }
}