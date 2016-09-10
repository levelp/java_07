import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for Name class
 */
public class NameTest extends Assert {

    @Test
    public void testConstructor() {
        Name name = new Name("John");
        assertNotNull(name);
        assertEquals("John", name.name);
    }

    @Test
    public void testConstructorWithEmptyString() {
        Name name = new Name("");
        assertNotNull(name);
        assertEquals("", name.name);
    }

    @Test
    public void testConstructorWithNull() {
        Name name = new Name(null);
        assertNotNull(name);
        assertNull(name.name);
    }

    @Test
    public void testEqualsWithSameName() {
        Name name1 = new Name("Alice");
        Name name2 = new Name("Alice");
        assertEquals(name1, name2);
    }

    @Test
    public void testEqualsWithDifferentName() {
        Name name1 = new Name("Alice");
        Name name2 = new Name("Bob");
        assertNotEquals(name1, name2);
    }

    @Test
    public void testEqualsWithSameObject() {
        Name name = new Name("Charlie");
        assertEquals(name, name);
    }

    @Test
    public void testEqualsWithNull() {
        Name name = new Name("David");
        assertNotEquals(name, null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Name name = new Name("Eve");
        Object obj = new Object();
        assertNotEquals(name, obj);
    }

    @Test
    public void testHashCodeConsistency() {
        Name name1 = new Name("Frank");
        Name name2 = new Name("Frank");
        assertEquals(name1.hashCode(), name2.hashCode());
    }

    @Test
    public void testHashCodeDifferentNames() {
        Name name1 = new Name("Grace");
        Name name2 = new Name("Heidi");
        assertNotEquals(name1.hashCode(), name2.hashCode());
    }

    @Test
    public void testHashCodeWithEmptyString() {
        Name name = new Name("");
        assertEquals("".hashCode(), name.hashCode());
    }

    @Test
    public void testHashCodeMatchesStringHashCode() {
        String str = "TestName";
        Name name = new Name(str);
        assertEquals(str.hashCode(), name.hashCode());
    }

    @Test
    public void testEqualsWithEmptyStrings() {
        Name name1 = new Name("");
        Name name2 = new Name("");
        assertEquals(name1, name2);
    }

    @Test
    public void testNameFieldAccessibility() {
        Name name = new Name("Ivan");
        assertEquals("Ivan", name.name);

        name.name = "Jack";
        assertEquals("Jack", name.name);
    }

    @Test
    public void testEqualsAfterNameChange() {
        Name name1 = new Name("Kate");
        Name name2 = new Name("Kate");

        assertEquals(name1, name2);

        name1.name = "Laura";
        assertNotEquals(name1, name2);
    }

    @Test
    public void testHashCodeAfterNameChange() {
        Name name = new Name("Mike");
        int originalHashCode = name.hashCode();

        name.name = "Nancy";
        int newHashCode = name.hashCode();

        assertNotEquals(originalHashCode, newHashCode);
    }

    @Test
    public void testEqualsWithLongNames() {
        String longName = "ThisIsAVeryLongNameThatIsUsedForTestingPurposes";
        Name name1 = new Name(longName);
        Name name2 = new Name(longName);
        assertEquals(name1, name2);
    }

    @Test
    public void testEqualsWithSpecialCharacters() {
        Name name1 = new Name("O'Brien");
        Name name2 = new Name("O'Brien");
        assertEquals(name1, name2);
    }

    @Test
    public void testEqualsWithUnicodeCharacters() {
        Name name1 = new Name("Пётр");
        Name name2 = new Name("Пётр");
        assertEquals(name1, name2);
    }

    @Test
    public void testHashCodeWithUnicodeCharacters() {
        Name name = new Name("李明");
        assertEquals("李明".hashCode(), name.hashCode());
    }

    @Test
    public void testEqualsWithWhitespace() {
        Name name1 = new Name("First Last");
        Name name2 = new Name("First Last");
        assertEquals(name1, name2);
    }

    @Test
    public void testEqualsWithDifferentWhitespace() {
        Name name1 = new Name("First Last");
        Name name2 = new Name("First  Last");
        assertNotEquals(name1, name2);
    }

    @Test
    public void testEqualsSymmetry() {
        Name name1 = new Name("Quinn");
        Name name2 = new Name("Quinn");
        assertEquals(name1, name2);
        assertEquals(name2, name1);
    }

    @Test
    public void testEqualsTransitivity() {
        Name name1 = new Name("Rachel");
        Name name2 = new Name("Rachel");
        Name name3 = new Name("Rachel");

        assertEquals(name1, name2);
        assertEquals(name2, name3);
        assertEquals(name1, name3);
    }
}
