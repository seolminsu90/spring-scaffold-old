package com.example.springscaffold2.common.model;

public class ThreadLocalContext {
    private static final ThreadLocal<String> lookUpKey = new ThreadLocal<>();

    public static String get() {
        return lookUpKey.get();
    }

    public static void set(String labelKey) {
        lookUpKey.set(labelKey);
    }

    public static void remove() {
        lookUpKey.remove();
    }
}
