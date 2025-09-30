package com.example.messangermix;

public class User {

    private String id; // уникальный код
    private String name;
    private String lastName;
    private int age;
    private Boolean online;


    public User(String id, String name, String lastName, int age, Boolean isOnline) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.online = isOnline;
    }

    public String getId() {
        return id;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", isOnline=" + online +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Boolean getOnline() {
        return online;
    }
}
