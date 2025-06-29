package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    @Test
    public void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);

        // Assert true
        assertTrue(5 > 3);

        // Assert false
        assertFalse(5 < 3);

        // Assert null
        String nullValue = null;
        assertNull(nullValue);

        // Assert not null
        String notNullValue = "JUnit";
        assertNotNull(notNullValue);

        System.out.println("All assertions passed successfully.");
    }
}
