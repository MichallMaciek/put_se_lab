package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        assertEquals(3, calculator.add(1, 2));
        assertEquals(0, calculator.add(-1, 1));
    }
    @Test
    public void testMultiply(){
        assertEquals(24, calculator.multiply(4, 6));
        assertEquals(-10, calculator.multiply(-2, 5));
    }

    @Test
    public void testAddPositiveNumbers(){
        assertEquals(10, calculator.addPositiveNumbers(4, 6));
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.addPositiveNumbers(-5, 10);
        });
    }
}