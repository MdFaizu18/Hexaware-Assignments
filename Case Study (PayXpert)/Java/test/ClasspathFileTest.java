package com.payxpert.test;

import java.io.InputStream;

public class ClasspathFileTest {
    public static void main(String[] args) {
        InputStream in = ClasspathFileTest.class.getClassLoader().getResourceAsStream("db.properties");
        if (in == null) {
            System.out.println("❌ db.properties not found in classpath");
        } else {
            System.out.println("✅ db.properties successfully loaded from classpath");
        }
    }
}
