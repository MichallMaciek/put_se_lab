package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AudiobookPriceCalculatorTest {

    private AudiobookPriceCalculator calculator;
    private Audiobook audiobook;

    @BeforeEach
    public void setUp() {
        calculator = new AudiobookPriceCalculator();
        audiobook = new Audiobook("1984", 10.0);
    }

    @Test
    public void testPath1_Subscriber() {
        Customer customer = new Customer("Adam", Customer.LoyaltyLevel.STANDARD, true);
        assertEquals(0.0, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testPath2_SilverLoyalty() {
        Customer customer = new Customer("Bartek", Customer.LoyaltyLevel.SILVER, false);
        assertEquals(9.0, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testPath3_GoldLoyalty() {
        Customer customer = new Customer("Cecylia", Customer.LoyaltyLevel.GOLD, false);
        assertEquals(8.0, calculator.calculate(customer, audiobook));
    }

    @Test
    public void testPath4_StandardLoyalty() {
        Customer customer = new Customer("Dawid", Customer.LoyaltyLevel.STANDARD, false);
        assertEquals(10.0, calculator.calculate(customer, audiobook));
    }
}