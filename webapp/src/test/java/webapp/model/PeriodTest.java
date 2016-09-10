package webapp.model;

import org.junit.Assert;
import org.junit.Test;
import webapp.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Comprehensive unit tests for Period class
 */
public class PeriodTest extends Assert {

    @Test
    public void testDefaultConstructor() {
        Period period = new Period();
        assertNotNull(period);
        assertNull(period.getStartDate());
        assertNull(period.getEndDate());
        assertNull(period.getPosition());
        assertNull(period.getContent());
    }

    @Test
    public void testParameterizedConstructor() {
        Date start = DateUtil.getDate(2020, Calendar.JANUARY);
        Date end = DateUtil.getDate(2021, Calendar.DECEMBER);
        Period period = new Period(start, end, "Developer", "Java development");

        assertEquals(start, period.getStartDate());
        assertEquals(end, period.getEndDate());
        assertEquals("Developer", period.getPosition());
        assertEquals("Java development", period.getContent());
    }

    @Test
    public void testIntegerConstructor() {
        Period period = new Period(2020, Calendar.MARCH, 2022, Calendar.JUNE, "Manager", "Project management");

        assertNotNull(period.getStartDate());
        assertNotNull(period.getEndDate());
        assertEquals("Manager", period.getPosition());
        assertEquals("Project management", period.getContent());

        assertEquals("2020", DateUtil.getYear(period.getStartDate()));
        assertEquals("2022", DateUtil.getYear(period.getEndDate()));
    }

    @Test
    public void testEmptyConstant() {
        assertNotNull(Period.EMPTY);
    }

    @Test
    public void testGetStartDate() {
        Date date = DateUtil.getDate(2020, Calendar.JANUARY);
        Period period = new Period(date, null, null, null);
        assertEquals(date, period.getStartDate());
    }

    @Test
    public void testGetEndDate() {
        Date date = DateUtil.getDate(2021, Calendar.DECEMBER);
        Period period = new Period(null, date, null, null);
        assertEquals(date, period.getEndDate());
    }

    @Test
    public void testGetPosition() {
        Period period = new Period(null, null, "Senior Developer", null);
        assertEquals("Senior Developer", period.getPosition());
    }

    @Test
    public void testGetContent() {
        Period period = new Period(null, null, null, "Full stack development");
        assertEquals("Full stack development", period.getContent());
    }

    @Test
    public void testEqualsWithSameValues() {
        Date start = DateUtil.getDate(2020, Calendar.JANUARY);
        Date end = DateUtil.getDate(2021, Calendar.DECEMBER);
        Period period1 = new Period(start, end, "Dev", "Content");
        Period period2 = new Period(start, end, "Dev", "Content");
        assertEquals(period1, period2);
    }

    @Test
    public void testEqualsWithDifferentStartDate() {
        Date start1 = DateUtil.getDate(2020, Calendar.JANUARY);
        Date start2 = DateUtil.getDate(2021, Calendar.JANUARY);
        Date end = DateUtil.getDate(2022, Calendar.DECEMBER);
        Period period1 = new Period(start1, end, "Dev", "Content");
        Period period2 = new Period(start2, end, "Dev", "Content");
        assertNotEquals(period1, period2);
    }

    @Test
    public void testEqualsWithDifferentEndDate() {
        Date start = DateUtil.getDate(2020, Calendar.JANUARY);
        Date end1 = DateUtil.getDate(2021, Calendar.DECEMBER);
        Date end2 = DateUtil.getDate(2022, Calendar.DECEMBER);
        Period period1 = new Period(start, end1, "Dev", "Content");
        Period period2 = new Period(start, end2, "Dev", "Content");
        assertNotEquals(period1, period2);
    }

    @Test
    public void testEqualsWithDifferentPosition() {
        Date start = DateUtil.getDate(2020, Calendar.JANUARY);
        Date end = DateUtil.getDate(2021, Calendar.DECEMBER);
        Period period1 = new Period(start, end, "Dev", "Content");
        Period period2 = new Period(start, end, "Manager", "Content");
        assertNotEquals(period1, period2);
    }

    @Test
    public void testEqualsWithDifferentContent() {
        Date start = DateUtil.getDate(2020, Calendar.JANUARY);
        Date end = DateUtil.getDate(2021, Calendar.DECEMBER);
        Period period1 = new Period(start, end, "Dev", "Content1");
        Period period2 = new Period(start, end, "Dev", "Content2");
        assertNotEquals(period1, period2);
    }

    @Test
    public void testEqualsWithSameObject() {
        Period period = new Period(null, null, "Dev", "Content");
        assertEquals(period, period);
    }

    @Test
    public void testEqualsWithNull() {
        Period period = new Period(null, null, "Dev", "Content");
        assertNotEquals(period, null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Period period = new Period(null, null, "Dev", "Content");
        assertNotEquals(period, new Object());
    }

    @Test
    public void testEqualsWithAllNullFields() {
        Period period1 = new Period();
        Period period2 = new Period();
        assertEquals(period1, period2);
    }

    @Test
    public void testHashCodeConsistency() {
        Date start = DateUtil.getDate(2020, Calendar.JANUARY);
        Date end = DateUtil.getDate(2021, Calendar.DECEMBER);
        Period period1 = new Period(start, end, "Dev", "Content");
        Period period2 = new Period(start, end, "Dev", "Content");
        assertEquals(period1.hashCode(), period2.hashCode());
    }

    @Test
    public void testHashCodeWithNullFields() {
        Period period = new Period();
        int hashCode = period.hashCode();
        assertEquals(0, hashCode);
    }

    @Test
    public void testHashCodeMultipleCalls() {
        Period period = new Period(null, null, "Dev", "Content");
        assertEquals(period.hashCode(), period.hashCode());
    }

    @Test
    public void testToString() {
        Date start = DateUtil.getDate(2020, Calendar.JANUARY);
        Date end = DateUtil.getDate(2021, Calendar.DECEMBER);
        Period period = new Period(start, end, "Developer", "Java development");
        String str = period.toString();

        assertTrue(str.contains("Period{"));
        assertTrue(str.contains("startDate="));
        assertTrue(str.contains("endDate="));
        assertTrue(str.contains("position="));
        assertTrue(str.contains("content="));
        assertTrue(str.contains("Developer"));
        assertTrue(str.contains("Java development"));
    }

    @Test
    public void testToStringWithNullFields() {
        Period period = new Period();
        String str = period.toString();
        assertTrue(str.contains("null"));
    }

    @Test
    public void testSerialVersionUID() {
        assertEquals(1L, Period.serialVersionUID);
    }

    @Test
    public void testIsSerializable() {
        assertTrue(Period.class.getInterfaces().length > 0);
        boolean isSerializable = false;
        for (Class<?> iface : Period.class.getInterfaces()) {
            if (iface.equals(java.io.Serializable.class)) {
                isSerializable = true;
                break;
            }
        }
        assertTrue(isSerializable);
    }

    @Test
    public void testIntegerConstructorWithSameStartAndEnd() {
        Period period = new Period(2020, Calendar.JUNE, 2020, Calendar.JUNE, "Position", "Content");
        assertEquals(DateUtil.getYear(period.getStartDate()), DateUtil.getYear(period.getEndDate()));
    }

    @Test
    public void testLongPosition() {
        String longPosition = "Senior Full Stack Software Engineer and Team Lead";
        Period period = new Period(null, null, longPosition, "Content");
        assertEquals(longPosition, period.getPosition());
    }

    @Test
    public void testLongContent() {
        String longContent = "Developed and maintained multiple enterprise-level applications using Java, Spring, and microservices architecture. Led a team of 5 developers and managed project timelines and deliverables.";
        Period period = new Period(null, null, "Dev", longContent);
        assertEquals(longContent, period.getContent());
    }

    @Test
    public void testEqualsSymmetry() {
        Period period1 = new Period(null, null, "Dev", "Content");
        Period period2 = new Period(null, null, "Dev", "Content");
        assertEquals(period1, period2);
        assertEquals(period2, period1);
    }

    @Test
    public void testEqualsTransitivity() {
        Period period1 = new Period(null, null, "Dev", "Content");
        Period period2 = new Period(null, null, "Dev", "Content");
        Period period3 = new Period(null, null, "Dev", "Content");
        assertEquals(period1, period2);
        assertEquals(period2, period3);
        assertEquals(period1, period3);
    }

    @Test
    public void testPeriodWithOnlyStartDate() {
        Date start = DateUtil.getDate(2020, Calendar.JANUARY);
        Period period = new Period(start, null, "Current Position", "Ongoing work");
        assertNotNull(period.getStartDate());
        assertNull(period.getEndDate());
    }

    @Test
    public void testPeriodWithEmptyStrings() {
        Period period = new Period(null, null, "", "");
        assertEquals("", period.getPosition());
        assertEquals("", period.getContent());
    }
}
