package webapp.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for ContactType enum
 */
public class ContactTypeTest extends Assert {

    @Test
    public void testPhoneType() {
        assertEquals("Тел.", ContactType.PHONE.getTitle());
    }

    @Test
    public void testMobileType() {
        assertEquals("Мобильный", ContactType.MOBILE.getTitle());
    }

    @Test
    public void testHomePhoneType() {
        assertEquals("Домашний тел.", ContactType.HOME_PHONE.getTitle());
    }

    @Test
    public void testSkypeType() {
        assertEquals("Skype", ContactType.SKYPE.getTitle());
    }

    @Test
    public void testMailType() {
        assertEquals("Почта", ContactType.MAIL.getTitle());
    }

    @Test
    public void testIcqType() {
        assertEquals("ICQ", ContactType.ICQ.getTitle());
    }

    @Test
    public void testVkType() {
        assertEquals("ВКонтакте", ContactType.VK.getTitle());
    }

    @Test
    public void testPhoneToHtml() {
        String result = ContactType.PHONE.toHtml("123-456-7890");
        assertEquals("Тел.: 123-456-7890", result);
    }

    @Test
    public void testMobileToHtml() {
        String result = ContactType.MOBILE.toHtml("+7-900-123-45-67");
        assertEquals("Мобильный: +7-900-123-45-67", result);
    }

    @Test
    public void testHomePhoneToHtml() {
        String result = ContactType.HOME_PHONE.toHtml("555-1234");
        assertEquals("Домашний тел.: 555-1234", result);
    }

    @Test
    public void testSkypeToHtml() {
        String result = ContactType.SKYPE.toHtml("john.doe");
        assertEquals("<a href='skype:john.doe'>john.doe</a>", result);
    }

    @Test
    public void testSkypeToHtmlWithSpecialChars() {
        String result = ContactType.SKYPE.toHtml("user_123");
        assertEquals("<a href='skype:user_123'>user_123</a>", result);
    }

    @Test
    public void testMailToHtml() {
        String result = ContactType.MAIL.toHtml("test@example.com");
        assertEquals("<a href='mailto:test@example.com'>test@example.com</a>", result);
    }

    @Test
    public void testMailToHtmlWithComplexEmail() {
        String result = ContactType.MAIL.toHtml("john.doe+tag@example.co.uk");
        assertEquals("<a href='mailto:john.doe+tag@example.co.uk'>john.doe+tag@example.co.uk</a>", result);
    }

    @Test
    public void testIcqToHtml() {
        String result = ContactType.ICQ.toHtml("123456789");
        assertEquals("ICQ: 123456789", result);
    }

    @Test
    public void testVkToHtml() {
        String result = ContactType.VK.toHtml("ivanov");
        assertEquals("<a href='https://vk.com/ivanov'>https://vk.com/ivanov</a>", result);
    }

    @Test
    public void testVkToHtmlWithId() {
        String result = ContactType.VK.toHtml("id12345678");
        assertEquals("<a href='https://vk.com/id12345678'>https://vk.com/id12345678</a>", result);
    }

    @Test
    public void testEnumValues() {
        ContactType[] types = ContactType.values();
        assertEquals(7, types.length);
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(ContactType.PHONE, ContactType.valueOf("PHONE"));
        assertEquals(ContactType.MOBILE, ContactType.valueOf("MOBILE"));
        assertEquals(ContactType.HOME_PHONE, ContactType.valueOf("HOME_PHONE"));
        assertEquals(ContactType.SKYPE, ContactType.valueOf("SKYPE"));
        assertEquals(ContactType.MAIL, ContactType.valueOf("MAIL"));
        assertEquals(ContactType.ICQ, ContactType.valueOf("ICQ"));
        assertEquals(ContactType.VK, ContactType.valueOf("VK"));
    }

    @Test
    public void testToHtmlWithEmptyString() {
        assertEquals("Тел.: ", ContactType.PHONE.toHtml(""));
        assertEquals("<a href='skype:'></a>", ContactType.SKYPE.toHtml(""));
        assertEquals("<a href='mailto:'></a>", ContactType.MAIL.toHtml(""));
        assertEquals("<a href='https://vk.com/'>https://vk.com/</a>", ContactType.VK.toHtml(""));
    }

    @Test
    public void testToHtmlWithNull() {
        assertEquals("Тел.: null", ContactType.PHONE.toHtml(null));
        assertEquals("<a href='skype:null'>null</a>", ContactType.SKYPE.toHtml(null));
        assertEquals("<a href='mailto:null'>null</a>", ContactType.MAIL.toHtml(null));
        assertEquals("<a href='https://vk.com/null'>https://vk.com/null</a>", ContactType.VK.toHtml(null));
    }

    @Test
    public void testAllTypesHaveTitle() {
        for (ContactType type : ContactType.values()) {
            assertNotNull(type.getTitle());
            assertFalse(type.getTitle().isEmpty());
        }
    }

    @Test
    public void testAllTypesHaveToHtml() {
        for (ContactType type : ContactType.values()) {
            String result = type.toHtml("test");
            assertNotNull(result);
            assertFalse(result.isEmpty());
        }
    }

    @Test
    public void testSkypeHtmlContainsLink() {
        String result = ContactType.SKYPE.toHtml("username");
        assertTrue(result.contains("<a href="));
        assertTrue(result.contains("skype:"));
    }

    @Test
    public void testMailHtmlContainsLink() {
        String result = ContactType.MAIL.toHtml("test@test.com");
        assertTrue(result.contains("<a href="));
        assertTrue(result.contains("mailto:"));
    }

    @Test
    public void testVkHtmlContainsLink() {
        String result = ContactType.VK.toHtml("user");
        assertTrue(result.contains("<a href="));
        assertTrue(result.contains("https://vk.com/"));
    }

    @Test
    public void testEnumComparison() {
        assertSame(ContactType.PHONE, ContactType.PHONE);
        assertNotSame(ContactType.PHONE, ContactType.MOBILE);
    }

    @Test
    public void testEnumOrdinal() {
        assertTrue(ContactType.PHONE.ordinal() >= 0);
        assertTrue(ContactType.VK.ordinal() < ContactType.values().length);
    }
}
