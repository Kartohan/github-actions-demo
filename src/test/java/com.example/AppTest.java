package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private final OldFeatures app = new OldFeatures();

    @Test
    public void shouldGreetWithName() {
        // Перевіряємо вітання з конкретним ім'ям
        String result = app.greet("Alice");
        assertEquals("Hello, Alice!", result);
    }

    @Test
    public void shouldGreetWithWorldWhenNameIsNull() {
        // Перевіряємо вітання, коли ім'я null
        String result = app.greet(null);
        assertEquals("Hello, World!", result);
    }

    @Test
    public void shouldGreetWithWorldWhenNameIsBlank() {
        // Перевіряємо вітання, коли ім'я - це пустий рядок або пробіли
        String result = app.greet("   ");
        assertEquals("Hello, World!", result);
    }
}