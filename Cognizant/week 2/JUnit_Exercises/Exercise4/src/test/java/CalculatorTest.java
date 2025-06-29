import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        // Arrange
        calculator = new Calculator();
        System.out.println("Setup complete.");
    }

    @AfterEach
    public void tearDown() {
        calculator.close();
        System.out.println("Teardown complete.");
    }

    @Test
    public void testAddition() {
        // Act
        int result = calculator.add(5, 7);

        // Assert
        assertEquals(12, result, "Addition should return correct sum");
    }

    @Test
    public void testAdditionWithNegativeNumbers() {
        // Act
        int result = calculator.add(-3, -7);

        // Assert
        assertEquals(-10, result, "Should correctly add negative numbers");
    }
}
