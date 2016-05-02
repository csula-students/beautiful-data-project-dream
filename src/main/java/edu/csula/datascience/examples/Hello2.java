package edu.csula.datascience.examples;

/**
 * Simple test
 */
public class Hello2 {
    private final String name;
    public static void main(String[] args) {
        Hello2 ds = new Hello2("Awesome Data Science");

        System.out.println(String.format("Hola %s", ds.getName()));
    }

    public Hello2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
