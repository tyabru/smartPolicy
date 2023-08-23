package com.jingyu;

public class test {
    public static void main(String[] args) {
        String s = "1425,1425,1452,,,,";
        String[] split = s.split(",");
        System.out.println(split.length);
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }

    }
}