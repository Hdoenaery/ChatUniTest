package practice;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

public class Addition_1_2Test {

	@Test
    @Timeout(8000)
	public void testMain() {
		// Mocking JOptionPane
		JOptionPane mockOptionPane = mock(JOptionPane.class);
		when(mockOptionPane.showInputDialog(any())).thenReturn("5", "10");
		when(mockOptionPane.showMessageDialog(any(), any(), any(), any())).thenReturn(null);

		// Mocking System
		System mockSystem = mock(System.class);

		// Invoking private method
		try {
			Addition.class.getDeclaredMethod("main", String[].class).invoke(null, (Object) null);
		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}

		// Verifying JOptionPane interactions
		verify(mockOptionPane, times(2)).showInputDialog(any());
		verify(mockOptionPane).showMessageDialog(any(), eq("The sum is 15"), eq("Result"), eq(JOptionPane.PLAIN_MESSAGE));

		// Verifying System interactions
		verify(mockSystem).exit(0);
	}
}