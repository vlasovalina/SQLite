package com.example.user.sqlite.Model;

public class Person {

    private  int id;
    private  byte[] img;
    private  String name;
    private  String surname;
    private  int age;

    public Person() {}

    public Person(int id, byte[] img, String name, String surname, int age )
    {
        this.id = id;
        this.img = img;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getImg() {return img;}
    public void setImg(byte[] img) {this.img = img;}

}
