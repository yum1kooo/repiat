package org.example.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Ref {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Person("1", 1);
        Class<? extends Person> person1 = person.getClass();
        Class<Person> person2 = Person.class;
        //Class<?> person3 = Class.forName("Reflection.Ref.Person");


        Method[] methods = person2.getMethods();
        for(Method method : methods){
            System.out.println(method.getName() + " , " + method.getReturnType() + " , " + Arrays.toString(method.getParameterTypes()));
        }

        System.out.println("////////////////////");


        Field[] fields = person2.getDeclaredFields();
        for (Field field : fields){
            System.out.println(field.getName() + " , " + field.getType() + " , ");

        }
    }
}

class Person{
    private String name;
    private  int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }


    public void sayHello(){
        System.out.println("hello" + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
