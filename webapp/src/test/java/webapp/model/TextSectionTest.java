package webapp.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * Comprehensive unit tests for TextSection class
 */
public class TextSectionTest extends Assert {

    @Test
    public void testDefaultConstructor() {
        TextSection section = new TextSection();
        assertNotNull(section);
        assertNotNull(section.getContent());
        assertTrue(section.getContent().isEmpty());
    }

    @Test
    public void testVarargsConstructor() {
        TextSection section = new TextSection("Line1", "Line2", "Line3");
        assertEquals(3, section.getContent().size());
    }

    @Test
    public void testVarargsConstructorWithOneElement() {
        TextSection section = new TextSection("Single line");
        assertEquals(1, section.getContent().size());
        assertTrue(section.getContent().contains("Single line"));
    }

    @Test
    public void testVarargsConstructorWithEmptyArray() {
        TextSection section = new TextSection();
        assertTrue(section.getContent().isEmpty());
    }

    @Test
    public void testAdd() {
        TextSection section = new TextSection();
        section.add("First line");
        assertEquals(1, section.getContent().size());
        assertTrue(section.getContent().contains("First line"));
    }

    @Test
    public void testAddMultiple() {
        TextSection section = new TextSection();
        section.add("Line 1");
        section.add("Line 2");
        section.add("Line 3");
        assertEquals(3, section.getContent().size());
    }

    @Test
    public void testAddFirst() {
        TextSection section = new TextSection("Second");
        section.addFirst("First");

        Collection<String> content = section.getContent();
        assertEquals(2, content.size());
        assertEquals("First", content.iterator().next());
    }

    @Test
    public void testGetContent() {
        TextSection section = new TextSection("Text1", "Text2");
        Collection<String> content = section.getContent();

        assertNotNull(content);
        assertEquals(2, content.size());
        assertTrue(content.contains("Text1"));
        assertTrue(content.contains("Text2"));
    }

    @Test
    public void testGetValues() {
        TextSection section = new TextSection("Value1", "Value2");
        Collection<String> values = section.getValues();

        assertNotNull(values);
        assertEquals(2, values.size());
        assertTrue(values.contains("Value1"));
        assertTrue(values.contains("Value2"));
    }

    @Test
    public void testGetContentAndGetValuesReturnSame() {
        TextSection section = new TextSection("Test");
        assertSame(section.getContent(), section.getValues());
    }

    @Test
    public void testEmptyConstant() {
        assertNotNull(TextSection.EMPTY);
        assertEquals(1, TextSection.EMPTY.getContent().size());
        assertTrue(TextSection.EMPTY.getContent().contains(""));
    }

    @Test
    public void testEqualsWithSameValues() {
        TextSection section1 = new TextSection("Text1", "Text2");
        TextSection section2 = new TextSection("Text1", "Text2");
        assertEquals(section1, section2);
    }

    @Test
    public void testEqualsWithDifferentValues() {
        TextSection section1 = new TextSection("Text1");
        TextSection section2 = new TextSection("Text2");
        assertNotEquals(section1, section2);
    }

    @Test
    public void testEqualsWithDifferentSizes() {
        TextSection section1 = new TextSection("Text1", "Text2");
        TextSection section2 = new TextSection("Text1");
        assertNotEquals(section1, section2);
    }

    @Test
    public void testEqualsWithSameObject() {
        TextSection section = new TextSection("Text");
        assertEquals(section, section);
    }

    @Test
    public void testEqualsWithNull() {
        TextSection section = new TextSection("Text");
        assertNotEquals(section, null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        TextSection section = new TextSection("Text");
        assertNotEquals(section, new Object());
    }

    @Test
    public void testHashCodeConsistency() {
        TextSection section1 = new TextSection("Text1", "Text2");
        TextSection section2 = new TextSection("Text1", "Text2");
        assertEquals(section1.hashCode(), section2.hashCode());
    }

    @Test
    public void testHashCodeMultipleCalls() {
        TextSection section = new TextSection("Text");
        assertEquals(section.hashCode(), section.hashCode());
    }

    @Test
    public void testToString() {
        TextSection section = new TextSection("Achievement 1", "Achievement 2");
        String str = section.toString();

        assertTrue(str.contains("Section"));
        assertTrue(str.contains("Achievement 1"));
        assertTrue(str.contains("Achievement 2"));
    }

    @Test
    public void testSerialVersionUID() {
        assertEquals(1L, TextSection.serialVersionUID);
    }

    @Test
    public void testExtendsSection() {
        TextSection section = new TextSection();
        assertTrue(section instanceof Section);
    }

    @Test
    public void testAddNull() {
        TextSection section = new TextSection();
        section.add(null);
        assertEquals(1, section.getContent().size());
        assertTrue(section.getContent().contains(null));
    }

    @Test
    public void testAddEmptyString() {
        TextSection section = new TextSection();
        section.add("");
        assertEquals(1, section.getContent().size());
        assertTrue(section.getContent().contains(""));
    }

    @Test
    public void testLongText() {
        String longText = "This is a very long achievement description that spans multiple lines " +
                "and contains detailed information about the accomplishments and results achieved " +
                "during the course of the project or initiative.";
        TextSection section = new TextSection(longText);

        assertEquals(1, section.getContent().size());
        assertTrue(section.getContent().contains(longText));
    }

    @Test
    public void testMultipleAddFirstCalls() {
        TextSection section = new TextSection();
        section.add("Third");
        section.addFirst("Second");
        section.addFirst("First");

        Collection<String> content = section.getContent();
        assertEquals(3, content.size());

        String first = content.iterator().next();
        assertEquals("First", first);
    }

    @Test
    public void testMixedAddAndAddFirst() {
        TextSection section = new TextSection();
        section.add("Middle");
        section.addFirst("First");
        section.add("Last");

        assertEquals(3, section.getContent().size());
    }

    @Test
    public void testWithSpecialCharacters() {
        TextSection section = new TextSection("Text with <html> tags", "Text with & symbols", "Text with \"quotes\"");
        assertEquals(3, section.getContent().size());
    }

    @Test
    public void testWithUnicodeCharacters() {
        TextSection section = new TextSection("Достижение 1", "Achievement 2", "成就 3");
        assertEquals(3, section.getContent().size());
    }

    @Test
    public void testEqualsSymmetry() {
        TextSection section1 = new TextSection("Text");
        TextSection section2 = new TextSection("Text");
        assertEquals(section1, section2);
        assertEquals(section2, section1);
    }

    @Test
    public void testEqualsTransitivity() {
        TextSection section1 = new TextSection("Text");
        TextSection section2 = new TextSection("Text");
        TextSection section3 = new TextSection("Text");
        assertEquals(section1, section2);
        assertEquals(section2, section3);
        assertEquals(section1, section3);
    }
}
