package webapp.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Comprehensive unit tests for DateUtil class
 */
public class DateUtilTest extends Assert {

    @Test
    public void testGetDate() {
        Date date = DateUtil.getDate(2020, Calendar.JANUARY);
        assertNotNull(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertEquals(2020, cal.get(Calendar.YEAR));
        assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
    }

    @Test
    public void testGetDateDecember() {
        Date date = DateUtil.getDate(2021, Calendar.DECEMBER);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertEquals(2021, cal.get(Calendar.YEAR));
        assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
    }

    @Test
    public void testGetDateFebruary() {
        Date date = DateUtil.getDate(2022, Calendar.FEBRUARY);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertEquals(2022, cal.get(Calendar.YEAR));
        assertEquals(Calendar.FEBRUARY, cal.get(Calendar.MONTH));
    }

    @Test
    public void testGetYearWithValidDate() {
        Date date = DateUtil.getDate(2020, Calendar.MARCH);
        String year = DateUtil.getYear(date);
        assertEquals("2020", year);
    }

    @Test
    public void testGetYearWithNull() {
        String year = DateUtil.getYear(null);
        assertEquals("", year);
    }

    @Test
    public void testGetYearDifferentYears() {
        assertEquals("2015", DateUtil.getYear(DateUtil.getDate(2015, Calendar.APRIL)));
        assertEquals("2020", DateUtil.getYear(DateUtil.getDate(2020, Calendar.MAY)));
        assertEquals("2025", DateUtil.getYear(DateUtil.getDate(2025, Calendar.JUNE)));
    }

    @Test
    public void testGetMonthWithValidDate() {
        Date date = DateUtil.getDate(2020, Calendar.JULY);
        int month = DateUtil.getMonth(date);
        assertEquals(Calendar.JULY, month);
    }

    @Test
    public void testGetMonthWithNull() {
        int month = DateUtil.getMonth(null);
        assertEquals(-1, month);
    }

    @Test
    public void testGetMonthAllMonths() {
        assertEquals(Calendar.JANUARY, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.JANUARY)));
        assertEquals(Calendar.FEBRUARY, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.FEBRUARY)));
        assertEquals(Calendar.MARCH, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.MARCH)));
        assertEquals(Calendar.APRIL, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.APRIL)));
        assertEquals(Calendar.MAY, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.MAY)));
        assertEquals(Calendar.JUNE, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.JUNE)));
        assertEquals(Calendar.JULY, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.JULY)));
        assertEquals(Calendar.AUGUST, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.AUGUST)));
        assertEquals(Calendar.SEPTEMBER, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.SEPTEMBER)));
        assertEquals(Calendar.OCTOBER, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.OCTOBER)));
        assertEquals(Calendar.NOVEMBER, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.NOVEMBER)));
        assertEquals(Calendar.DECEMBER, DateUtil.getMonth(DateUtil.getDate(2020, Calendar.DECEMBER)));
    }

    @Test
    public void testFormatWithValidDate() {
        Date date = DateUtil.getDate(2020, Calendar.MARCH);
        String formatted = DateUtil.format(date);
        assertNotNull(formatted);
        assertTrue(formatted.contains("2020"));
        assertTrue(formatted.contains("04")); // March is month 3 (0-indexed), displays as 04
    }

    @Test
    public void testFormatWithNull() {
        String formatted = DateUtil.format(null);
        assertEquals(DateUtil.NOW, formatted);
        assertEquals("сейчас", formatted);
    }

    @Test
    public void testFormatMultipleDates() {
        Date date1 = DateUtil.getDate(2020, Calendar.JANUARY);
        Date date2 = DateUtil.getDate(2021, Calendar.DECEMBER);

        String formatted1 = DateUtil.format(date1);
        String formatted2 = DateUtil.format(date2);

        assertNotNull(formatted1);
        assertNotNull(formatted2);
        assertNotEquals(formatted1, formatted2);
    }

    @Test
    public void testMonthArray() {
        assertNotNull(DateUtil.MONTH);
        assertEquals(13, DateUtil.MONTH.length);
        assertEquals("", DateUtil.MONTH[0]);
        assertEquals("JANUARY", DateUtil.MONTH[1]);
        assertEquals("FEBRUARY", DateUtil.MONTH[2]);
        assertEquals("MARCH", DateUtil.MONTH[3]);
        assertEquals("APRIL", DateUtil.MONTH[4]);
        assertEquals("MAY", DateUtil.MONTH[5]);
        assertEquals("JUNE", DateUtil.MONTH[6]);
        assertEquals("JULY", DateUtil.MONTH[7]);
        assertEquals("AUGUST", DateUtil.MONTH[8]);
        assertEquals("SEPTEMBER", DateUtil.MONTH[9]);
        assertEquals("OCTOBER", DateUtil.MONTH[10]);
        assertEquals("NOVEMBER", DateUtil.MONTH[11]);
        assertEquals("DECEMBER", DateUtil.MONTH[12]);
    }

    @Test
    public void testNowConstant() {
        assertEquals("сейчас", DateUtil.NOW);
    }

    @Test
    public void testGetDateRoundTrip() {
        Date originalDate = DateUtil.getDate(2020, Calendar.JUNE);
        String year = DateUtil.getYear(originalDate);
        int month = DateUtil.getMonth(originalDate);

        assertEquals("2020", year);
        assertEquals(Calendar.JUNE, month);

        Date reconstructedDate = DateUtil.getDate(Integer.parseInt(year), month);
        assertEquals(DateUtil.getYear(originalDate), DateUtil.getYear(reconstructedDate));
        assertEquals(DateUtil.getMonth(originalDate), DateUtil.getMonth(reconstructedDate));
    }

    @Test
    public void testFormatIsThreadSafe() throws InterruptedException {
        // Test that synchronized format method works correctly
        Date date = DateUtil.getDate(2020, Calendar.MARCH);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                assertNotNull(DateUtil.format(date));
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                assertNotNull(DateUtil.format(date));
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    @Test
    public void testGetDateWithDifferentYears() {
        Date date1980 = DateUtil.getDate(1980, Calendar.JANUARY);
        Date date2050 = DateUtil.getDate(2050, Calendar.DECEMBER);

        assertEquals("1980", DateUtil.getYear(date1980));
        assertEquals("2050", DateUtil.getYear(date2050));
    }

    @Test
    public void testGetMonthReturnValue() {
        // Month should be 0-11 (Calendar.JANUARY = 0, Calendar.DECEMBER = 11)
        int month = DateUtil.getMonth(DateUtil.getDate(2020, Calendar.JANUARY));
        assertTrue(month >= 0 && month <= 11);
    }
}
