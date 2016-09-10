package webapp.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for Util class
 */
public class UtilTest extends Assert {

    @Test
    public void testMaskWithNullString() {
        String result = Util.mask(null);
        assertEquals("", result);
        assertNotNull(result);
    }

    @Test
    public void testMaskWithEmptyString() {
        String result = Util.mask("");
        assertEquals("", result);
    }

    @Test
    public void testMaskWithNonEmptyString() {
        String result = Util.mask("test");
        assertEquals("test", result);
    }

    @Test
    public void testMaskWithWhitespace() {
        String result = Util.mask("   ");
        assertEquals("   ", result);
    }

    @Test
    public void testMaskWithSpecialCharacters() {
        String result = Util.mask("!@#$%^&*()");
        assertEquals("!@#$%^&*()", result);
    }

    @Test
    public void testIsEmptyArrayWithNull() {
        assertTrue(Util.isEmpty((Object[]) null));
    }

    @Test
    public void testIsEmptyArrayWithEmptyArray() {
        assertTrue(Util.isEmpty(new Object[0]));
    }

    @Test
    public void testIsEmptyArrayWithNonEmptyArray() {
        assertFalse(Util.isEmpty(new Object[]{1}));
        assertFalse(Util.isEmpty(new Object[]{1, 2, 3}));
    }

    @Test
    public void testIsEmptyArrayWithStringArray() {
        assertTrue(Util.isEmpty(new String[0]));
        assertFalse(Util.isEmpty(new String[]{"a", "b"}));
    }

    @Test
    public void testIsEmptyArrayWithIntegerArray() {
        assertTrue(Util.isEmpty(new Integer[0]));
        assertFalse(Util.isEmpty(new Integer[]{1, 2, 3}));
    }

    @Test
    public void testIsEmptyStringWithNull() {
        assertTrue(Util.isEmpty((String) null));
    }

    @Test
    public void testIsEmptyStringWithEmptyString() {
        assertTrue(Util.isEmpty(""));
    }

    @Test
    public void testIsEmptyStringWithWhitespace() {
        assertTrue(Util.isEmpty("   "));
        assertTrue(Util.isEmpty("\t"));
        assertTrue(Util.isEmpty("\n"));
        assertTrue(Util.isEmpty(" \t\n "));
    }

    @Test
    public void testIsEmptyStringWithNonEmptyString() {
        assertFalse(Util.isEmpty("test"));
        assertFalse(Util.isEmpty("a"));
        assertFalse(Util.isEmpty(" a "));
    }

    @Test
    public void testIsEmptyStringWithSingleCharacter() {
        assertFalse(Util.isEmpty("a"));
        assertFalse(Util.isEmpty("1"));
        assertFalse(Util.isEmpty("."));
    }

    @Test
    public void testMaskMultipleTimes() {
        assertEquals("", Util.mask(null));
        assertEquals("", Util.mask(null));
        assertEquals("test", Util.mask("test"));
        assertEquals("", Util.mask(null));
    }

    @Test
    public void testIsEmptyWithMixedCases() {
        // Array tests
        assertTrue(Util.isEmpty((Object[]) null));
        assertTrue(Util.isEmpty(new Object[]{}));
        assertFalse(Util.isEmpty(new Object[]{null}));
        assertFalse(Util.isEmpty(new Object[]{null, null}));

        // String tests
        assertTrue(Util.isEmpty((String) null));
        assertTrue(Util.isEmpty(""));
        assertTrue(Util.isEmpty("    "));
        assertFalse(Util.isEmpty(" a "));
        assertFalse(Util.isEmpty("test"));
    }

    @Test
    public void testMaskPreservesStringValue() {
        String input = "Hello World";
        String result = Util.mask(input);
        assertEquals(input, result);
    }

    @Test
    public void testIsEmptyArrayWithNullElements() {
        Object[] array = new Object[]{null};
        assertFalse(Util.isEmpty(array));
    }

    @Test
    public void testIsEmptyStringTrimBehavior() {
        assertTrue(Util.isEmpty("   "));
        assertTrue(Util.isEmpty("\t\t"));
        assertTrue(Util.isEmpty("  \n  "));
        assertFalse(Util.isEmpty("  a  "));
    }

    @Test
    public void testMaskWithLongString() {
        String longString = "This is a very long string with many characters";
        assertEquals(longString, Util.mask(longString));
    }

    @Test
    public void testIsEmptyWithUnicodeString() {
        assertFalse(Util.isEmpty("привет"));
        assertFalse(Util.isEmpty("你好"));
        assertFalse(Util.isEmpty("مرحبا"));
    }

    @Test
    public void testMaskWithUnicodeString() {
        String unicode = "привет мир";
        assertEquals(unicode, Util.mask(unicode));
    }

    @Test
    public void testIsEmptyArrayWithMixedTypes() {
        Object[] array = new Object[]{"string", 123, new Object()};
        assertFalse(Util.isEmpty(array));
    }
}
