package practice;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Addition2_2_1Test {

    private static final String INPUT = "10\n20\n";
    private static final String EXPECTED_OUTPUT = "The sum is 30";

    @BeforeEach
    public void setup() {
        System.setIn(new ByteArrayInputStream(INPUT.getBytes()));
    }

    @Test
    @Timeout(8000)
    public void testMain() {
        try (MockedStatic<Addition2> mockedStatic = Mockito.mockStatic(Addition2.class)) {
            mockedStatic.when(Addition2::main).thenCallRealMethod();
            Addition2.main(new String[]{});
            assertEquals(EXPECTED_OUTPUT, getConsoleOutput());
        }
    }

    private String getConsoleOutput() {
        return new String(System.out.toByteArray());
    }
}