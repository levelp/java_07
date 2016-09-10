import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Comprehensive unit tests for MainStringBuilder class
 */
public class MainStringBuilderTest extends Assert {

    @Test
    public void testPrintMethod() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        MainStringBuilder.print(42);

        System.setOut(originalOut);
        assertEquals("42" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testPrintNegativeNumber() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        MainStringBuilder.print(-10);

        System.setOut(originalOut);
        assertEquals("-10" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testPrintZero() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        MainStringBuilder.print(0);

        System.setOut(originalOut);
        assertEquals("0" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testPrintLargeNumber() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        MainStringBuilder.print(999999);

        System.setOut(originalOut);
        assertEquals("999999" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testStringBuilderAppend() {
        String[] arr = new String[]{"1", "2", "3", "4"};
        StringBuilder res = new StringBuilder();
        for (String anArr : arr) {
            res.append(anArr).append("-");
        }
        assertEquals("1-2-3-4-", res.toString());
    }

    @Test
    public void testStringBuilderWithEmptyArray() {
        String[] arr = new String[]{};
        StringBuilder res = new StringBuilder();
        for (String anArr : arr) {
            res.append(anArr).append("-");
        }
        assertEquals("", res.toString());
    }

    @Test
    public void testStringBuilderWithSingleElement() {
        String[] arr = new String[]{"only"};
        StringBuilder res = new StringBuilder();
        for (String anArr : arr) {
            res.append(anArr).append("-");
        }
        assertEquals("only-", res.toString());
    }

    @Test
    public void testStringBuilderWithDifferentStrings() {
        String[] arr = new String[]{"a", "bb", "ccc"};
        StringBuilder res = new StringBuilder();
        for (String anArr : arr) {
            res.append(anArr).append("-");
        }
        assertEquals("a-bb-ccc-", res.toString());
    }

    @Test
    public void testClassIsAbstract() {
        try {
            Class<?> clazz = MainStringBuilder.class;
            int modifiers = clazz.getModifiers();
            assertTrue(java.lang.reflect.Modifier.isAbstract(modifiers));
        } catch (Exception e) {
            fail("Unable to verify class is abstract: " + e.getMessage());
        }
    }

    @Test
    public void testPrintMethodAccessibility() {
        // Verify that the print method is static and accessible
        try {
            java.lang.reflect.Method method = MainStringBuilder.class.getDeclaredMethod("print", int.class);
            assertTrue(java.lang.reflect.Modifier.isStatic(method.getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("print method not found");
        }
    }

    @Test
    public void testStringBuilderPerformance() {
        String[] arr = new String[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = String.valueOf(i);
        }

        StringBuilder res = new StringBuilder();
        for (String anArr : arr) {
            res.append(anArr).append("-");
        }

        assertTrue(res.length() > 1000);
        assertTrue(res.toString().startsWith("0-1-2-"));
    }

    @Test
    public void testStringBuilderWithSpecialCharacters() {
        String[] arr = new String[]{"!", "@", "#", "$"};
        StringBuilder res = new StringBuilder();
        for (String anArr : arr) {
            res.append(anArr).append("-");
        }
        assertEquals("!-@-#-$-", res.toString());
    }
}
