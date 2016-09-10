package webapp.model;

import org.junit.Assert;
import org.junit.Test;
import webapp.util.DateUtil;

import java.util.Calendar;
import java.util.Collection;

/**
 * Comprehensive unit tests for Organization class
 */
public class OrganizationTest extends Assert {

    @Test
    public void testDefaultConstructor() {
        Organization org = new Organization();
        assertNotNull(org);
        assertEquals(Link.EMPTY, org.getLink());
        assertNotNull(org.getPeriods());
        assertTrue(org.getPeriods().isEmpty());
    }

    @Test
    public void testConstructorWithNameUrlAndVarargs() {
        Period period1 = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");
        Period period2 = new Period(2022, Calendar.JANUARY, 2023, Calendar.DECEMBER, "Lead", "Team");

        Organization org = new Organization("Company", "https://company.com", period1, period2);

        assertEquals("Company", org.getLink().getName());
        assertEquals("https://company.com", org.getLink().getUrl());
        assertEquals(2, org.getPeriods().size());
    }

    @Test
    public void testConstructorWithNoPeriods() {
        Organization org = new Organization("Company", "https://company.com");

        assertEquals("Company", org.getLink().getName());
        assertEquals("https://company.com", org.getLink().getUrl());
        assertTrue(org.getPeriods().isEmpty());
    }

    @Test
    public void testAddPeriod() {
        Organization org = new Organization();
        Period period = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");

        org.add(period);

        assertEquals(1, org.getPeriods().size());
        assertTrue(org.getPeriods().contains(period));
    }

    @Test
    public void testAddMultiplePeriods() {
        Organization org = new Organization();
        Period period1 = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");
        Period period2 = new Period(2022, Calendar.JANUARY, 2023, Calendar.DECEMBER, "Lead", "Team");

        org.add(period1);
        org.add(period2);

        assertEquals(2, org.getPeriods().size());
    }

    @Test
    public void testAddFirstPeriod() {
        Organization org = new Organization();
        Period period1 = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");
        Period period2 = new Period(2022, Calendar.JANUARY, 2023, Calendar.DECEMBER, "Lead", "Team");

        org.add(period1);
        org.addFirstPeriod(period2);

        Collection<Period> periods = org.getPeriods();
        assertEquals(2, periods.size());
        assertEquals(period2, periods.iterator().next());
    }

    @Test
    public void testGetLink() {
        Organization org = new Organization("Google", "https://google.com");
        Link link = org.getLink();

        assertNotNull(link);
        assertEquals("Google", link.getName());
        assertEquals("https://google.com", link.getUrl());
    }

    @Test
    public void testGetPeriods() {
        Period period = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");
        Organization org = new Organization("Company", "https://company.com", period);

        Collection<Period> periods = org.getPeriods();
        assertNotNull(periods);
        assertEquals(1, periods.size());
    }

    @Test
    public void testEmptyConstant() {
        assertNotNull(Organization.EMPTY);
        assertEquals(Link.EMPTY, Organization.EMPTY.getLink());
        assertFalse(Organization.EMPTY.getPeriods().isEmpty());
        assertTrue(Organization.EMPTY.getPeriods().contains(Period.EMPTY));
    }

    @Test
    public void testEqualsWithSameValues() {
        Period period = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");
        Organization org1 = new Organization("Company", "https://company.com", period);
        Organization org2 = new Organization("Company", "https://company.com", period);

        assertEquals(org1, org2);
    }

    @Test
    public void testEqualsWithDifferentLinks() {
        Period period = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");
        Organization org1 = new Organization("Company1", "https://company1.com", period);
        Organization org2 = new Organization("Company2", "https://company2.com", period);

        assertNotEquals(org1, org2);
    }

    @Test
    public void testEqualsWithDifferentPeriods() {
        Period period1 = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");
        Period period2 = new Period(2022, Calendar.JANUARY, 2023, Calendar.DECEMBER, "Lead", "Team");
        Organization org1 = new Organization("Company", "https://company.com", period1);
        Organization org2 = new Organization("Company", "https://company.com", period2);

        assertNotEquals(org1, org2);
    }

    @Test
    public void testEqualsWithSameObject() {
        Organization org = new Organization();
        assertEquals(org, org);
    }

    @Test
    public void testEqualsWithNull() {
        Organization org = new Organization();
        assertNotEquals(org, null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Organization org = new Organization();
        assertNotEquals(org, new Object());
    }

    @Test
    public void testHashCodeConsistency() {
        Period period = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");
        Organization org1 = new Organization("Company", "https://company.com", period);
        Organization org2 = new Organization("Company", "https://company.com", period);

        assertEquals(org1.hashCode(), org2.hashCode());
    }

    @Test
    public void testHashCodeMultipleCalls() {
        Organization org = new Organization("Company", "https://company.com");
        assertEquals(org.hashCode(), org.hashCode());
    }

    @Test
    public void testToString() {
        Period period = new Period(2020, Calendar.JANUARY, 2021, Calendar.DECEMBER, "Dev", "Java");
        Organization org = new Organization("Company", "https://company.com", period);

        String str = org.toString();
        assertTrue(str.contains("Organization{"));
        assertTrue(str.contains("link="));
        assertTrue(str.contains("periods="));
    }

    @Test
    public void testSerialVersionUID() {
        assertEquals(1L, Organization.serialVersionUID);
    }

    @Test
    public void testIsSerializable() {
        assertTrue(Organization.class.getInterfaces().length > 0);
        boolean isSerializable = false;
        for (Class<?> iface : Organization.class.getInterfaces()) {
            if (iface.equals(java.io.Serializable.class)) {
                isSerializable = true;
                break;
            }
        }
        assertTrue(isSerializable);
    }

    @Test
    public void testAddNullPeriod() {
        Organization org = new Organization();
        org.add(null);
        assertEquals(1, org.getPeriods().size());
        assertTrue(org.getPeriods().contains(null));
    }

    @Test
    public void testConstructorWithNullUrl() {
        Organization org = new Organization("Company", null);
        assertEquals("Company", org.getLink().getName());
        assertNull(org.getLink().getUrl());
    }

    @Test
    public void testPeriodsOrderAfterAddFirst() {
        Organization org = new Organization();
        Period period1 = new Period(2020, Calendar.JANUARY, 2020, Calendar.DECEMBER, "P1", "C1");
        Period period2 = new Period(2021, Calendar.JANUARY, 2021, Calendar.DECEMBER, "P2", "C2");
        Period period3 = new Period(2022, Calendar.JANUARY, 2022, Calendar.DECEMBER, "P3", "C3");

        org.add(period1);
        org.add(period2);
        org.addFirstPeriod(period3);

        assertEquals(3, org.getPeriods().size());
        assertEquals(period3, org.getPeriods().iterator().next());
    }

    @Test
    public void testMultipleAddsAndAddFirsts() {
        Organization org = new Organization();

        for (int i = 0; i < 5; i++) {
            Period period = new Period(2020 + i, Calendar.JANUARY, 2020 + i, Calendar.DECEMBER, "Pos" + i, "Con" + i);
            if (i % 2 == 0) {
                org.add(period);
            } else {
                org.addFirstPeriod(period);
            }
        }

        assertEquals(5, org.getPeriods().size());
    }

    @Test
    public void testEqualsSymmetry() {
        Organization org1 = new Organization("Company", "https://company.com");
        Organization org2 = new Organization("Company", "https://company.com");
        assertEquals(org1, org2);
        assertEquals(org2, org1);
    }

    @Test
    public void testEqualsTransitivity() {
        Organization org1 = new Organization("Company", "https://company.com");
        Organization org2 = new Organization("Company", "https://company.com");
        Organization org3 = new Organization("Company", "https://company.com");
        assertEquals(org1, org2);
        assertEquals(org2, org3);
        assertEquals(org1, org3);
    }
}
