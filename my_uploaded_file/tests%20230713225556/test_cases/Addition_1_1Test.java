package practice;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Addition_1_1Test {

    private Addition addition;

    @BeforeEach
    void setUp() {
        addition = spy(new Addition());
    }

    @Test
    @Timeout(8000)
    void testMain() {
        String firstNumber = "10";
        String secondNumber = "20";
        int expectedSum = 30;

        // Mocking the JOptionPane showInputDialog method
        when(JOptionPane.showInputDialog(anyString()))
                .thenReturn(firstNumber)
                .thenReturn(secondNumber);

        // Mocking the JOptionPane showMessageDialog method
        doNothing().when(JOptionPane.class);
        JOptionPane.showMessageDialog(any(), anyString(), anyString(), anyInt());

        // Mocking the System exit method
        doNothing().when(System.class);
        System.exit(0);

        // Calling the main method using reflection
        try {
            Addition.class.getMethod("main", String[].class).invoke(null, (Object) null);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }

        // Verifying the interactions and assertions
        verify(addition).main(null);
        verify(JOptionPane, times(2)).showInputDialog(anyString());
        verify(JOptionPane).showMessageDialog(any(), anyString(), anyString(), anyInt());
        verify(System).exit(0);
    }
}