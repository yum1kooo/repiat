package org.example.Anotation;

import java.util.ArrayList;
import java.util.List;

public class An  extends  A{
    public static void main(String[] args) {
        A a = new A();

        a.addValues();
        a.MyMethod();
    }
}

class A{
    public void tes(){
        System.out.println("hello from B");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void addValues() {
        List list = new ArrayList(); // raw type
        list.add("hello");
        list.add(123);
    }
    @Deprecated
    public void MyMethod(){
        System.out.println("other method ");
    }
}
