package webapp.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Map;

/**
 * Comprehensive unit tests for Resume class
 */
public class ResumeTest extends Assert {

    private Resume resume;

    @Before
    public void setUp() {
        resume = new Resume("John Doe", "San Francisco");
    }

    @Test
    public void testDefaultConstructor() {
        Resume r = new Resume();
        assertNotNull(r);
    }

    @Test
    public void testTwoParameterConstructor() {
        Resume r = new Resume("Jane Doe", "New York");
        assertEquals("Jane Doe", r.getFullName());
        assertEquals("New York", r.getLocation());
        assertNotNull(r.getUuid());
    }

    @Test
    public void testThreeParameterConstructor() {
        String uuid = "test-uuid-123";
        Resume r = new Resume(uuid, "Bob Smith", "Chicago");

        assertEquals(uuid, r.getUuid());
        assertEquals("Bob Smith", r.getFullName());
        assertEquals("Chicago", r.getLocation());
    }

    @Test
    public void testGetUuid() {
        assertNotNull(resume.getUuid());
    }

    @Test
    public void testSetUuid() {
        String newUuid = "new-uuid-456";
        resume.setUuid(newUuid);
        assertEquals(newUuid, resume.getUuid());
    }

    @Test
    public void testGetFullName() {
        assertEquals("John Doe", resume.getFullName());
    }

    @Test
    public void testSetFullName() {
        resume.setFullName("Jane Smith");
        assertEquals("Jane Smith", resume.getFullName());
    }

    @Test
    public void testGetLocation() {
        assertEquals("San Francisco", resume.getLocation());
    }

    @Test
    public void testSetLocation() {
        resume.setLocation("Los Angeles");
        assertEquals("Los Angeles", resume.getLocation());
    }

    @Test
    public void testSetLocationWithNull() {
        resume.setLocation(null);
        assertEquals("", resume.getLocation());
    }

    @Test
    public void testAddContact() {
        resume.addContact(ContactType.PHONE, "123-456-7890");
        assertEquals("123-456-7890", resume.getContact(ContactType.PHONE));
    }

    @Test
    public void testAddMultipleContacts() {
        resume.addContact(ContactType.PHONE, "123-456-7890");
        resume.addContact(ContactType.MAIL, "john@example.com");
        resume.addContact(ContactType.SKYPE, "john.doe");

        assertEquals("123-456-7890", resume.getContact(ContactType.PHONE));
        assertEquals("john@example.com", resume.getContact(ContactType.MAIL));
        assertEquals("john.doe", resume.getContact(ContactType.SKYPE));
    }

    @Test
    public void testGetContactNonExistent() {
        assertNull(resume.getContact(ContactType.ICQ));
    }

    @Test
    public void testGetContacts() {
        resume.addContact(ContactType.PHONE, "123-456-7890");
        resume.addContact(ContactType.MAIL, "john@example.com");

        Map<ContactType, String> contacts = resume.getContacts();
        assertNotNull(contacts);
        assertEquals(2, contacts.size());
    }

    @Test
    public void testAddSectionWithTextSection() {
        TextSection section = new TextSection("Objective text");
        resume.addSection(SectionType.OBJECTIVE, section);

        assertEquals(section, resume.getSection(SectionType.OBJECTIVE));
    }

    @Test
    public void testAddSectionWithStringVarargs() {
        resume.addSection(SectionType.ACHIEVEMENT, "Achievement 1", "Achievement 2");

        Section section = resume.getSection(SectionType.ACHIEVEMENT);
        assertNotNull(section);
        assertTrue(section instanceof TextSection);
        assertEquals(2, section.getContent().size());
    }

    @Test
    public void testAddSectionWithOrganizationVarargs() {
        Organization org = new Organization("Company", "https://company.com");
        resume.addSection(SectionType.EXPERIENCE, org);

        Section section = resume.getSection(SectionType.EXPERIENCE);
        assertNotNull(section);
        assertTrue(section instanceof OrganizationSection);
    }

    @Test
    public void testGetSection() {
        TextSection section = new TextSection("Content");
        resume.addSection(SectionType.QUALIFICATIONS, section);

        assertEquals(section, resume.getSection(SectionType.QUALIFICATIONS));
    }

    @Test
    public void testGetSectionNonExistent() {
        assertNull(resume.getSection(SectionType.EDUCATION));
    }

    @Test
    public void testGetSections() {
        resume.addSection(SectionType.OBJECTIVE, "Objective");
        resume.addSection(SectionType.ACHIEVEMENT, "Achievement");

        Map<SectionType, Section> sections = resume.getSections();
        assertNotNull(sections);
        assertEquals(2, sections.size());
    }

    @Test
    public void testCreateUuid() {
        String oldUuid = resume.getUuid();
        resume.createUuid();
        String newUuid = resume.getUuid();

        assertNotNull(newUuid);
        assertNotEquals(oldUuid, newUuid);
    }

    @Test
    public void testSatisfyByNameMatching() {
        assertTrue(resume.satisfyByName("John"));
        assertTrue(resume.satisfyByName("Doe"));
        assertTrue(resume.satisfyByName("john"));
        assertTrue(resume.satisfyByName("DOE"));
        assertTrue(resume.satisfyByName("john doe"));
    }

    @Test
    public void testSatisfyByNameNotMatching() {
        assertFalse(resume.satisfyByName("Smith"));
        assertFalse(resume.satisfyByName("Jane"));
    }

    @Test
    public void testSatisfyByNameCaseInsensitive() {
        Resume r = new Resume("Alice Johnson", "Boston");
        assertTrue(r.satisfyByName("alice"));
        assertTrue(r.satisfyByName("ALICE"));
        assertTrue(r.satisfyByName("Alice"));
        assertTrue(r.satisfyByName("aLiCe"));
    }

    @Test
    public void testCompareToByFullName() {
        Resume r1 = new Resume("Alice", "NY");
        Resume r2 = new Resume("Bob", "LA");

        assertTrue(r1.compareTo(r2) < 0);
        assertTrue(r2.compareTo(r1) > 0);
    }

    @Test
    public void testCompareToSameFullNameDifferentUuid() {
        Resume r1 = new Resume("uuid1", "Alice", "NY");
        Resume r2 = new Resume("uuid2", "Alice", "LA");

        int result = r1.compareTo(r2);
        assertNotEquals(0, result);
        assertEquals(result, r1.getUuid().compareTo(r2.getUuid()));
    }

    @Test
    public void testCompareToSameResume() {
        assertEquals(0, resume.compareTo(resume));
    }

    @Test
    public void testEqualsWithSameValues() {
        Resume r1 = new Resume("uuid1", "John Doe", "SF");
        Resume r2 = new Resume("uuid1", "John Doe", "SF");

        assertEquals(r1, r2);
    }

    @Test
    public void testEqualsWithDifferentUuid() {
        Resume r1 = new Resume("uuid1", "John Doe", "SF");
        Resume r2 = new Resume("uuid2", "John Doe", "SF");

        assertNotEquals(r1, r2);
    }

    @Test
    public void testEqualsWithDifferentFullName() {
        Resume r1 = new Resume("uuid1", "John Doe", "SF");
        Resume r2 = new Resume("uuid1", "Jane Doe", "SF");

        assertNotEquals(r1, r2);
    }

    @Test
    public void testEqualsWithDifferentLocation() {
        Resume r1 = new Resume("uuid1", "John Doe", "SF");
        Resume r2 = new Resume("uuid1", "John Doe", "NY");

        assertNotEquals(r1, r2);
    }

    @Test
    public void testEqualsWithSameObject() {
        assertEquals(resume, resume);
    }

    @Test
    public void testEqualsWithNull() {
        assertNotEquals(resume, null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        assertNotEquals(resume, new Object());
    }

    @Test
    public void testHashCodeConsistency() {
        Resume r1 = new Resume("uuid1", "John Doe", "SF");
        Resume r2 = new Resume("uuid1", "John Doe", "SF");

        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    public void testHashCodeMultipleCalls() {
        assertEquals(resume.hashCode(), resume.hashCode());
    }

    @Test
    public void testToString() {
        String str = resume.toString();

        assertTrue(str.contains("Resume{"));
        assertTrue(str.contains("uuid="));
        assertTrue(str.contains("fullName="));
        assertTrue(str.contains("location="));
        assertTrue(str.contains("contacts="));
        assertTrue(str.contains("sections="));
    }

    @Test
    public void testEmptyConstant() {
        assertNotNull(Resume.EMPTY);
        assertNotNull(Resume.EMPTY.getUuid());
        assertNotNull(Resume.EMPTY.getSections());

        // Check that all section types are present
        for (SectionType type : SectionType.values()) {
            assertNotNull(Resume.EMPTY.getSection(type));
        }
    }

    @Test
    public void testSerialVersionUID() {
        assertEquals(1L, Resume.serialVersionUID);
    }

    @Test
    public void testIsSerializable() {
        assertTrue(Resume.class.getInterfaces().length > 0);
        boolean isSerializable = false;
        boolean isComparable = false;
        for (Class<?> iface : Resume.class.getInterfaces()) {
            if (iface.equals(java.io.Serializable.class)) {
                isSerializable = true;
            }
            if (iface.equals(Comparable.class)) {
                isComparable = true;
            }
        }
        assertTrue(isSerializable);
        assertTrue(isComparable);
    }

    @Test
    public void testAddSectionReplaces() {
        resume.addSection(SectionType.OBJECTIVE, "First objective");
        resume.addSection(SectionType.OBJECTIVE, "Second objective");

        Section section = resume.getSection(SectionType.OBJECTIVE);
        assertEquals(1, section.getContent().size());
        assertTrue(section.getContent().contains("Second objective"));
    }

    @Test
    public void testComplexResumeWithAllData() {
        Resume r = new Resume("complex-uuid", "Alice Johnson", "Seattle");

        r.addContact(ContactType.PHONE, "555-1234");
        r.addContact(ContactType.MAIL, "alice@example.com");
        r.addContact(ContactType.SKYPE, "alice.j");

        r.addSection(SectionType.OBJECTIVE, "Senior Developer Position");
        r.addSection(SectionType.ACHIEVEMENT, "Achievement 1", "Achievement 2");

        Period period = new Period(2020, Calendar.JANUARY, 2023, Calendar.DECEMBER, "Dev", "Java");
        Organization org = new Organization("TechCorp", "https://techcorp.com", period);
        r.addSection(SectionType.EXPERIENCE, org);

        assertEquals(3, r.getContacts().size());
        assertEquals(3, r.getSections().size());
        assertNotNull(r.getContact(ContactType.PHONE));
        assertNotNull(r.getSection(SectionType.OBJECTIVE));
        assertNotNull(r.getSection(SectionType.EXPERIENCE));
    }

    @Test
    public void testSatisfyByNameWithEmptyQuery() {
        assertTrue(resume.satisfyByName(""));
    }

    @Test
    public void testSatisfyByNameWithPartialMatch() {
        assertTrue(resume.satisfyByName("Jo"));
        assertTrue(resume.satisfyByName("Do"));
        assertTrue(resume.satisfyByName("ohn"));
    }

    @Test
    public void testLocationMaskingWithEmptyString() {
        resume.setLocation("");
        assertEquals("", resume.getLocation());
    }

    @Test
    public void testCompareToOrdering() {
        Resume r1 = new Resume("Alice Anderson", "NY");
        Resume r2 = new Resume("Bob Brown", "LA");
        Resume r3 = new Resume("Charlie Chen", "SF");

        assertTrue(r1.compareTo(r2) < 0);
        assertTrue(r2.compareTo(r3) < 0);
        assertTrue(r1.compareTo(r3) < 0);
    }
}
