package put.io.testing.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FailureOrErrorTest {

    @Test
    public void test1() {
        assertTrue(false);
    }

    @Test
    public void test2() throws Exception {
        throw new Exception("This is an unexpected exception");
    }

    @Test
    public void test3() {
        try {
            assertTrue(false);
        } catch (Throwable t) {
            System.out.println("Get type: " + t.getClass().getName());
            t.printStackTrace();
        }
    }
}