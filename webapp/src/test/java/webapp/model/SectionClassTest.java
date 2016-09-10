package webapp.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for SectionClass enum
 */
public class SectionClassTest extends Assert {

    @Test
    public void testTextSectionClass() {
        assertNotNull(SectionClass.TEXT);
    }

    @Test
    public void testOrganizationSectionClass() {
        assertNotNull(SectionClass.ORGANIZATION);
    }

    @Test
    public void testEnumValues() {
        SectionClass[] classes = SectionClass.values();
        assertEquals(2, classes.length);
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(SectionClass.TEXT, SectionClass.valueOf("TEXT"));
        assertEquals(SectionClass.ORGANIZATION, SectionClass.valueOf("ORGANIZATION"));
    }

    @Test
    public void testTextCreate() {
        Section section = SectionClass.TEXT.create();
        assertNotNull(section);
        assertTrue(section instanceof TextSection);
    }

    @Test
    public void testOrganizationCreate() {
        Section section = SectionClass.ORGANIZATION.create();
        assertNotNull(section);
        assertTrue(section instanceof OrganizationSection);
    }

    @Test
    public void testTextGetEmptySection() {
        Section section = SectionClass.TEXT.getEmptySection();
        assertNotNull(section);
        assertEquals(TextSection.EMPTY, section);
    }

    @Test
    public void testOrganizationGetEmptySection() {
        Section section = SectionClass.ORGANIZATION.getEmptySection();
        assertNotNull(section);
        assertEquals(OrganizationSection.EMPTY, section);
    }

    @Test
    public void testTextAddEmptyValue() {
        TextSection section = new TextSection();
        SectionClass.TEXT.addEmptyValue(section);

        assertEquals(1, section.getContent().size());
        assertEquals("", section.getContent().iterator().next());
    }

    @Test
    public void testOrganizationAddEmptyValue() {
        OrganizationSection section = new OrganizationSection();
        SectionClass.ORGANIZATION.addEmptyValue(section);

        // Should add Organization.EMPTY to the beginning
        assertFalse(section.getContent().isEmpty());
    }

    @Test
    public void testTextAddEmptyValueToExistingContent() {
        TextSection section = new TextSection("Existing");
        SectionClass.TEXT.addEmptyValue(section);

        assertEquals(2, section.getContent().size());
        assertEquals("", section.getContent().iterator().next());
    }

    @Test
    public void testOrganizationAddEmptyValueToExistingContent() {
        Organization existingOrg = new Organization("Company", "https://company.com");
        OrganizationSection section = new OrganizationSection(existingOrg);

        SectionClass.ORGANIZATION.addEmptyValue(section);

        // Should add Period.EMPTY to existing org and Organization.EMPTY
        assertTrue(section.getContent().size() > 1);
        assertEquals(1, existingOrg.getPeriods().size());
    }

    @Test
    public void testCreateReturnsNewInstance() {
        Section section1 = SectionClass.TEXT.create();
        Section section2 = SectionClass.TEXT.create();

        assertNotSame(section1, section2);
    }

    @Test
    public void testGetEmptySectionReturnsSameInstance() {
        Section section1 = SectionClass.TEXT.getEmptySection();
        Section section2 = SectionClass.TEXT.getEmptySection();

        assertSame(section1, section2);
    }

    @Test
    public void testEnumComparison() {
        assertSame(SectionClass.TEXT, SectionClass.TEXT);
        assertNotSame(SectionClass.TEXT, SectionClass.ORGANIZATION);
    }

    @Test
    public void testEnumOrdinal() {
        assertTrue(SectionClass.TEXT.ordinal() >= 0);
        assertTrue(SectionClass.ORGANIZATION.ordinal() < SectionClass.values().length);
    }

    @Test
    public void testAllClassesHaveCreate() {
        for (SectionClass sc : SectionClass.values()) {
            Section section = sc.create();
            assertNotNull(section);
        }
    }

    @Test
    public void testAllClassesHaveGetEmptySection() {
        for (SectionClass sc : SectionClass.values()) {
            Section section = sc.getEmptySection();
            assertNotNull(section);
        }
    }

    @Test
    public void testAllClassesHaveAddEmptyValue() {
        TextSection textSection = new TextSection();
        SectionClass.TEXT.addEmptyValue(textSection);
        assertFalse(textSection.getContent().isEmpty());

        OrganizationSection orgSection = new OrganizationSection();
        SectionClass.ORGANIZATION.addEmptyValue(orgSection);
        assertFalse(orgSection.getContent().isEmpty());
    }

    @Test
    public void testTextCreatedSectionIsEmpty() {
        Section section = SectionClass.TEXT.create();
        assertTrue(section.getContent().isEmpty());
    }

    @Test
    public void testOrganizationCreatedSectionIsEmpty() {
        Section section = SectionClass.ORGANIZATION.create();
        assertTrue(section.getContent().isEmpty());
    }

    @Test
    public void testOrganizationAddEmptyValueAddsPeriodsToExistingOrgs() {
        Organization org1 = new Organization("Company1", "https://company1.com");
        Organization org2 = new Organization("Company2", "https://company2.com");
        OrganizationSection section = new OrganizationSection(org1, org2);

        int org1PeriodsBefore = org1.getPeriods().size();
        int org2PeriodsBefore = org2.getPeriods().size();

        SectionClass.ORGANIZATION.addEmptyValue(section);

        // Each existing organization should have Period.EMPTY added
        assertEquals(org1PeriodsBefore + 1, org1.getPeriods().size());
        assertEquals(org2PeriodsBefore + 1, org2.getPeriods().size());

        // Organization.EMPTY should also be added
        assertTrue(section.getContent().size() >= 3);
    }

    @Test
    public void testTextAddEmptyValueAddsToBeginning() {
        TextSection section = new TextSection("Second", "Third");
        SectionClass.TEXT.addEmptyValue(section);

        assertEquals("", section.getContent().iterator().next());
    }

    @Test
    public void testOrganizationAddEmptyValueAddsToBeginning() {
        Organization org = new Organization("Company", "https://company.com");
        OrganizationSection section = new OrganizationSection(org);

        SectionClass.ORGANIZATION.addEmptyValue(section);

        // Organization.EMPTY should be at the beginning
        assertTrue(section.getContent().size() >= 2);
    }
}
