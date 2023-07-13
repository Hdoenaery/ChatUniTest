package practice;
import org.junit.jupiter.api.Timeout;

import static org.mockito.Mockito.*;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.Test;

class Addition_1_3Test {

    @Test
    @Timeout(8000)
    void testMain() {
        // Mocking the JOptionPane class
        JOptionPane mockOptionPane = mock(JOptionPane.class);
        when(mockOptionPane.showInputDialog(any())).thenReturn("10", "20");
        when(mockOptionPane.showMessageDialog(any(), any(), any(), anyInt())).thenReturn(null);

        // Mocking the System class
        System mockSystem = mock(System.class);

        // Creating an instance of the Addition class
        Addition addition = new Addition();

        // Invoking the private method using reflection
        try {
            java.lang.reflect.Method privateMethod = Addition.class.getDeclaredMethod("main", String[].class);
            privateMethod.setAccessible(true);
            privateMethod.invoke(addition, (Object) null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Verifying the interactions
        verify(mockOptionPane, times(2)).showInputDialog(any());
        verify(mockOptionPane).showMessageDialog(any(), any(), any(), anyInt());
        verify(mockSystem).exit(0);
    }
}