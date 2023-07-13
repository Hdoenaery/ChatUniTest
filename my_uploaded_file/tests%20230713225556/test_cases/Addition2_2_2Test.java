package practice;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Addition2_2_2Test {

    private Addition2 addition;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        addition = new Addition2();
        resetScanner();
    }

    @Test
    @Timeout(8000)
    void testMain() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Arrange
        String input = "10\n20\n";
        String expectedOutput = "Enter first integer\nEnter second integer\nThe sum is 30\n";

        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Act
        invokePrivateMethod(addition, "main", String[].class, new String[] {});

        // Assert
        assertEquals(expectedOutput, out.toString());

        // Restore stdin and stdout
        System.setIn(stdin);
        System.setOut(stdout);
    }

    private void resetScanner() throws NoSuchFieldException, IllegalAccessException {
        Field scannerField = Addition2.class.getDeclaredField("intext");
        scannerField.setAccessible(true);
        scannerField.set(addition, new Scanner(System.in));
    }

    private Object invokePrivateMethod(Object targetObject, String methodName, Class<?> parameterTypes,
            Object... arguments) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = targetObject.getClass().getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(targetObject, arguments);
    }
}