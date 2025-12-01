package org.example;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> words = List.of("apple", "banana", "kiwi", "apricot", "blueberry", "avocado");
        List<String> list = words.stream()
                .filter(x -> x.length() > 5)
                .sorted()
                .toList();
        System.out.println("test " + list);
        //ya ychy git poэtomy bez commitov norm
    }
}
