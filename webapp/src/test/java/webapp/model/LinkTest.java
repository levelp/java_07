package webapp.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for Link class
 */
public class LinkTest extends Assert {

    @Test
    public void testDefaultConstructor() {
        Link link = new Link();
        assertEquals("", link.getName());
        assertNull(link.getUrl());
    }

    @Test
    public void testParameterizedConstructor() {
        Link link = new Link("Google", "https://google.com");
        assertEquals("Google", link.getName());
        assertEquals("https://google.com", link.getUrl());
    }

    @Test
    public void testConstructorWithNullUrl() {
        Link link = new Link("Test", null);
        assertEquals("Test", link.getName());
        assertNull(link.getUrl());
    }

    @Test
    public void testConstructorWithEmptyName() {
        Link link = new Link("", "https://example.com");
        assertEquals("", link.getName());
        assertEquals("https://example.com", link.getUrl());
    }

    @Test
    public void testGetName() {
        Link link = new Link("GitHub", "https://github.com");
        assertEquals("GitHub", link.getName());
    }

    @Test
    public void testGetUrl() {
        Link link = new Link("Example", "https://example.com");
        assertEquals("https://example.com", link.getUrl());
    }

    @Test
    public void testEmptyConstant() {
        assertNotNull(Link.EMPTY);
        assertEquals("", Link.EMPTY.getName());
        assertNull(Link.EMPTY.getUrl());
    }

    @Test
    public void testEqualsWithSameValues() {
        Link link1 = new Link("Test", "https://test.com");
        Link link2 = new Link("Test", "https://test.com");
        assertEquals(link1, link2);
    }

    @Test
    public void testEqualsWithDifferentNames() {
        Link link1 = new Link("Test1", "https://test.com");
        Link link2 = new Link("Test2", "https://test.com");
        assertNotEquals(link1, link2);
    }

    @Test
    public void testEqualsWithDifferentUrls() {
        Link link1 = new Link("Test", "https://test1.com");
        Link link2 = new Link("Test", "https://test2.com");
        assertNotEquals(link1, link2);
    }

    @Test
    public void testEqualsWithSameObject() {
        Link link = new Link("Test", "https://test.com");
        assertEquals(link, link);
    }

    @Test
    public void testEqualsWithNull() {
        Link link = new Link("Test", "https://test.com");
        assertNotEquals(link, null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Link link = new Link("Test", "https://test.com");
        assertNotEquals(link, new Object());
    }

    @Test
    public void testEqualsWithBothNullUrls() {
        Link link1 = new Link("Test", null);
        Link link2 = new Link("Test", null);
        assertEquals(link1, link2);
    }

    @Test
    public void testEqualsWithOneNullUrl() {
        Link link1 = new Link("Test", null);
        Link link2 = new Link("Test", "https://test.com");
        assertNotEquals(link1, link2);
    }

    @Test
    public void testHashCodeConsistency() {
        Link link1 = new Link("Test", "https://test.com");
        Link link2 = new Link("Test", "https://test.com");
        assertEquals(link1.hashCode(), link2.hashCode());
    }

    @Test
    public void testHashCodeDifferentNames() {
        Link link1 = new Link("Test1", "https://test.com");
        Link link2 = new Link("Test2", "https://test.com");
        assertNotEquals(link1.hashCode(), link2.hashCode());
    }

    @Test
    public void testHashCodeDifferentUrls() {
        Link link1 = new Link("Test", "https://test1.com");
        Link link2 = new Link("Test", "https://test2.com");
        assertNotEquals(link1.hashCode(), link2.hashCode());
    }

    @Test
    public void testHashCodeWithNullUrl() {
        Link link = new Link("Test", null);
        int hashCode = link.hashCode();
        assertTrue(hashCode != 0 || link.getName().isEmpty());
    }

    @Test
    public void testToString() {
        Link link = new Link("Test", "https://test.com");
        String str = link.toString();
        assertTrue(str.contains("Test"));
        assertTrue(str.contains("https://test.com"));
    }

    @Test
    public void testToStringWithNullUrl() {
        Link link = new Link("Test", null);
        String str = link.toString();
        assertTrue(str.contains("Test"));
        assertTrue(str.contains("null"));
    }

    @Test
    public void testToStringFormat() {
        Link link = new Link("Google", "https://google.com");
        String str = link.toString();
        assertTrue(str.startsWith("Link{"));
        assertTrue(str.contains("name="));
        assertTrue(str.contains("url="));
        assertTrue(str.endsWith("}"));
    }

    @Test
    public void testSerialVersionUID() {
        assertEquals(1L, Link.serialVersionUID);
    }

    @Test
    public void testIsSerializable() {
        assertTrue(Link.class.getInterfaces().length > 0);
        boolean isSerializable = false;
        for (Class<?> iface : Link.class.getInterfaces()) {
            if (iface.equals(java.io.Serializable.class)) {
                isSerializable = true;
                break;
            }
        }
        assertTrue(isSerializable);
    }

    @Test
    public void testEqualsSymmetry() {
        Link link1 = new Link("Test", "https://test.com");
        Link link2 = new Link("Test", "https://test.com");
        assertEquals(link1, link2);
        assertEquals(link2, link1);
    }

    @Test
    public void testEqualsTransitivity() {
        Link link1 = new Link("Test", "https://test.com");
        Link link2 = new Link("Test", "https://test.com");
        Link link3 = new Link("Test", "https://test.com");
        assertEquals(link1, link2);
        assertEquals(link2, link3);
        assertEquals(link1, link3);
    }

    @Test
    public void testHashCodeMultipleCalls() {
        Link link = new Link("Test", "https://test.com");
        int hash1 = link.hashCode();
        int hash2 = link.hashCode();
        assertEquals(hash1, hash2);
    }

    @Test
    public void testLongUrl() {
        String longUrl = "https://very-long-domain-name-for-testing-purposes.com/path/to/resource?param1=value1&param2=value2";
        Link link = new Link("Long", longUrl);
        assertEquals(longUrl, link.getUrl());
    }

    @Test
    public void testSpecialCharactersInName() {
        Link link = new Link("Test & Co.", "https://test.com");
        assertEquals("Test & Co.", link.getName());
    }

    @Test
    public void testUnicodeInName() {
        Link link = new Link("Тест", "https://test.com");
        assertEquals("Тест", link.getName());
    }
}
