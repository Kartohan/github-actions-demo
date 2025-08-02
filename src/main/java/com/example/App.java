package com.example;

/**
 * A simple class to generate greetings.
 */
public class App {

    /**
     * Generates a greeting message for the given name.
     * @param name The name to greet.
     * @return A greeting string. Returns a generic greeting if the name is null or blank.
     */
    public String greet(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Hello, World!";
        }
        return "Hello, " + name + "!";
    }

    public static void main(String[] args) {
        App app = new App();
        // Приклад використання
        System.out.println(app.greet("User"));
        System.out.println(app.greet(null));
    }
}