package practice;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Addition2_2_3Test {

    private Addition2 addition2;

    @BeforeEach
    public void setUp() {
        addition2 = new Addition2();
    }

    @Test
    @Timeout(8000)
    public void testMain() {
        // Prepare input
        String input = "10\n20\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Prepare expected output
        String expectedOutput = "Enter first integer\nEnter second integer\nThe sum is 30\n";

        // Invoke the main method using reflection
        try {
            Addition2.main(new String[]{});
        } catch (Exception e) {
            fail("An exception occurred: " + e.getMessage());
        }

        // Verify the output
        assertEquals(expectedOutput, getConsoleOutput());
    }

    private String getConsoleOutput() {
        return outContent.toString();
    }
}