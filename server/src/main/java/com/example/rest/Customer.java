package com.example.rest;

public class Customer {
    /**
     * Class that will be used to hold the data for the Rest API Example
     */

    private final int id;
    private String name;
    private int age;
    private static int currentId = 0;

    public Customer(String name, int age) {
        this.id = currentId++;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return String.format("Hello, my ID is %s, my name is %s and I am %d years old",
                this.getId(), this.getName(), this.getAge());
    }

}
