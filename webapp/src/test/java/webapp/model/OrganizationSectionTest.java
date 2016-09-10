package webapp.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Collection;

/**
 * Comprehensive unit tests for OrganizationSection class
 */
public class OrganizationSectionTest extends Assert {

    @Test
    public void testDefaultConstructor() {
        OrganizationSection section = new OrganizationSection();
        assertNotNull(section);
        assertNotNull(section.getContent());
        assertTrue(section.getContent().isEmpty());
    }

    @Test
    public void testVarargsConstructor() {
        Organization org1 = new Organization("Company1", "https://company1.com");
        Organization org2 = new Organization("Company2", "https://company2.com");

        OrganizationSection section = new OrganizationSection(org1, org2);
        assertEquals(2, section.getContent().size());
    }

    @Test
    public void testVarargsConstructorWithOneElement() {
        Organization org = new Organization("Company", "https://company.com");
        OrganizationSection section = new OrganizationSection(org);

        assertEquals(1, section.getContent().size());
        assertTrue(section.getContent().contains(org));
    }

    @Test
    public void testVarargsConstructorWithEmptyArray() {
        OrganizationSection section = new OrganizationSection();
        assertTrue(section.getContent().isEmpty());
    }

    @Test
    public void testAdd() {
        OrganizationSection section = new OrganizationSection();
        Organization org = new Organization("Company", "https://company.com");

        section.add(org);

        assertEquals(1, section.getContent().size());
        assertTrue(section.getContent().contains(org));
    }

    @Test
    public void testAddMultiple() {
        OrganizationSection section = new OrganizationSection();
        Organization org1 = new Organization("Company1", "https://company1.com");
        Organization org2 = new Organization("Company2", "https://company2.com");
        Organization org3 = new Organization("Company3", "https://company3.com");

        section.add(org1);
        section.add(org2);
        section.add(org3);

        assertEquals(3, section.getContent().size());
    }

    @Test
    public void testAddFirst() {
        Organization org1 = new Organization("Company1", "https://company1.com");
        Organization org2 = new Organization("Company2", "https://company2.com");

        OrganizationSection section = new OrganizationSection(org1);
        section.addFirst(org2);

        Collection<Organization> content = section.getContent();
        assertEquals(2, content.size());
        assertEquals(org2, content.iterator().next());
    }

    @Test
    public void testGetContent() {
        Organization org = new Organization("Company", "https://company.com");
        OrganizationSection section = new OrganizationSection(org);

        Collection<Organization> content = section.getContent();
        assertNotNull(content);
        assertEquals(1, content.size());
        assertTrue(content.contains(org));
    }

    @Test
    public void testGetValues() {
        Organization org = new Organization("Company", "https://company.com");
        OrganizationSection section = new OrganizationSection(org);

        Collection<Organization> values = section.getValues();
        assertNotNull(values);
        assertEquals(1, values.size());
        assertTrue(values.contains(org));
    }

    @Test
    public void testGetContentAndGetValuesReturnSame() {
        OrganizationSection section = new OrganizationSection();
        assertSame(section.getContent(), section.getValues());
    }

    @Test
    public void testEmptyConstant() {
        assertNotNull(OrganizationSection.EMPTY);
        assertEquals(1, OrganizationSection.EMPTY.getContent().size());
        assertTrue(OrganizationSection.EMPTY.getContent().contains(Organization.EMPTY));
    }

    @Test
    public void testEqualsWithSameValues() {
        Organization org = new Organization("Company", "https://company.com");
        OrganizationSection section1 = new OrganizationSection(org);
        OrganizationSection section2 = new OrganizationSection(org);

        assertEquals(section1, section2);
    }

    @Test
    public void testEqualsWithDifferentValues() {
        Organization org1 = new Organization("Company1", "https://company1.com");
        Organization org2 = new Organization("Company2", "https://company2.com");

        OrganizationSection section1 = new OrganizationSection(org1);
        OrganizationSection section2 = new OrganizationSection(org2);

        assertNotEquals(section1, section2);
    }

    @Test
    public void testEqualsWithSameObject() {
        OrganizationSection section = new OrganizationSection();
        assertEquals(section, section);
    }

    @Test
    public void testEqualsWithNull() {
        OrganizationSection section = new OrganizationSection();
        assertNotEquals(section, null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        OrganizationSection section = new OrganizationSection();
        assertNotEquals(section, new Object());
    }

    @Test
    public void testHashCodeConsistency() {
        Organization org = new Organization("Company", "https://company.com");
        OrganizationSection section1 = new OrganizationSection(org);
        OrganizationSection section2 = new OrganizationSection(org);

        assertEquals(section1.hashCode(), section2.hashCode());
    }

    @Test
    public void testHashCodeMultipleCalls() {
        Organization org = new Organization("Company", "https://company.com");
        OrganizationSection section = new OrganizationSection(org);

        assertEquals(section.hashCode(), section.hashCode());
    }

    @Test
    public void testToString() {
        Organization org = new Organization("Google", "https://google.com");
        OrganizationSection section = new OrganizationSection(org);

        String str = section.toString();
        assertTrue(str.contains("Section"));
    }

    @Test
    public void testSerialVersionUID() {
        assertEquals(1L, OrganizationSection.serialVersionUID);
    }

    @Test
    public void testExtendsSection() {
        OrganizationSection section = new OrganizationSection();
        assertTrue(section instanceof Section);
    }

    @Test
    public void testWithOrganizationsContainingPeriods() {
        Period period = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");
        Organization org = new Organization("Company", "https://company.com", period);

        OrganizationSection section = new OrganizationSection(org);

        assertEquals(1, section.getContent().size());
        Organization retrievedOrg = section.getContent().iterator().next();
        assertEquals(1, retrievedOrg.getPeriods().size());
    }

    @Test
    public void testAddNull() {
        OrganizationSection section = new OrganizationSection();
        section.add(null);

        assertEquals(1, section.getContent().size());
        assertTrue(section.getContent().contains(null));
    }

    @Test
    public void testMultipleAddFirstCalls() {
        Organization org1 = new Organization("Company1", "https://company1.com");
        Organization org2 = new Organization("Company2", "https://company2.com");
        Organization org3 = new Organization("Company3", "https://company3.com");

        OrganizationSection section = new OrganizationSection();
        section.add(org3);
        section.addFirst(org2);
        section.addFirst(org1);

        Collection<Organization> content = section.getContent();
        assertEquals(3, content.size());
        assertEquals(org1, content.iterator().next());
    }

    @Test
    public void testMixedAddAndAddFirst() {
        Organization org1 = new Organization("Company1", "https://company1.com");
        Organization org2 = new Organization("Company2", "https://company2.com");
        Organization org3 = new Organization("Company3", "https://company3.com");

        OrganizationSection section = new OrganizationSection();
        section.add(org2);
        section.addFirst(org1);
        section.add(org3);

        assertEquals(3, section.getContent().size());
    }

    @Test
    public void testWithComplexOrganizations() {
        Period period1 = new Period(2018, Calendar.JANUARY, 2019, Calendar.DECEMBER, "Junior Dev", "Learning");
        Period period2 = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Senior Dev", "Leading");

        Organization org = new Organization("TechCorp", "https://techcorp.com", period1, period2);
        OrganizationSection section = new OrganizationSection(org);

        assertEquals(1, section.getContent().size());
        assertEquals(2, org.getPeriods().size());
    }

    @Test
    public void testEqualsSymmetry() {
        Organization org = new Organization("Company", "https://company.com");
        OrganizationSection section1 = new OrganizationSection(org);
        OrganizationSection section2 = new OrganizationSection(org);

        assertEquals(section1, section2);
        assertEquals(section2, section1);
    }

    @Test
    public void testEqualsTransitivity() {
        Organization org = new Organization("Company", "https://company.com");
        OrganizationSection section1 = new OrganizationSection(org);
        OrganizationSection section2 = new OrganizationSection(org);
        OrganizationSection section3 = new OrganizationSection(org);

        assertEquals(section1, section2);
        assertEquals(section2, section3);
        assertEquals(section1, section3);
    }
}
