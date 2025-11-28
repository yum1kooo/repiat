package org.example.Anotation;


public class MyAnotTest {
    @MyAnnotation(name = "kirill", dateCreate = 2025, description = "test")
    public void hello(){
        System.out.println("hello world");
    }
}
