package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        int result = calc.add(3, 2);
        System.out.println("Addition Result: " + result);
        assertEquals(5, result);
    }

    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        int result = calc.multiply(3, 4);
        System.out.println("Multiplication Result: " + result);
        assertEquals(12, result);
    }
}
