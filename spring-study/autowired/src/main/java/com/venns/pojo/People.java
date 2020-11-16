package com.venns.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class People {
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
    public Cat getCat() {
        return cat;
    }
    public Dog getDog() {
        return dog;
    }
    public String getName() {
        return name;
    }
}
