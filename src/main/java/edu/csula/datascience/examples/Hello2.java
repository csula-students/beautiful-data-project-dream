package edu.csula.datascience.examples;

/**
 * Simple test
 */
public class Hello2 {
    private final String name;
    public static void main(String[] args) {
        Hello2 ds = new Hello2("Data Science2");

        System.out.println(String.format("Hello %s", ds.getName()));
    }

    public Hello2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
