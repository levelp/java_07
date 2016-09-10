package webapp.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for SectionType enum
 */
public class SectionTypeTest extends Assert {

    @Test
    public void testObjectiveType() {
        assertEquals("Позиция", SectionType.OBJECTIVE.getTitle());
        assertEquals(SectionClass.TEXT, SectionType.OBJECTIVE.getSectionClass());
    }

    @Test
    public void testAchievementType() {
        assertEquals("Достижения", SectionType.ACHIEVEMENT.getTitle());
        assertEquals(SectionClass.TEXT, SectionType.ACHIEVEMENT.getSectionClass());
    }

    @Test
    public void testQualificationsType() {
        assertEquals("Квалификация", SectionType.QUALIFICATIONS.getTitle());
        assertEquals(SectionClass.TEXT, SectionType.QUALIFICATIONS.getSectionClass());
    }

    @Test
    public void testExperienceType() {
        assertEquals("Опыт работы", SectionType.EXPERIENCE.getTitle());
        assertEquals(SectionClass.ORGANIZATION, SectionType.EXPERIENCE.getSectionClass());
    }

    @Test
    public void testEducationType() {
        assertEquals("Образование", SectionType.EDUCATION.getTitle());
        assertEquals(SectionClass.ORGANIZATION, SectionType.EDUCATION.getSectionClass());
    }

    @Test
    public void testEnumValues() {
        SectionType[] types = SectionType.values();
        assertEquals(5, types.length);
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(SectionType.OBJECTIVE, SectionType.valueOf("OBJECTIVE"));
        assertEquals(SectionType.ACHIEVEMENT, SectionType.valueOf("ACHIEVEMENT"));
        assertEquals(SectionType.QUALIFICATIONS, SectionType.valueOf("QUALIFICATIONS"));
        assertEquals(SectionType.EXPERIENCE, SectionType.valueOf("EXPERIENCE"));
        assertEquals(SectionType.EDUCATION, SectionType.valueOf("EDUCATION"));
    }

    @Test
    public void testAddEmptyValueForObjective() {
        TextSection section = new TextSection();
        SectionType.OBJECTIVE.addEmptyValue(section);
        // OBJECTIVE has empty implementation, so nothing should be added
        assertTrue(section.getContent().isEmpty());
    }

    @Test
    public void testAddEmptyValueForAchievement() {
        TextSection section = new TextSection();
        SectionType.ACHIEVEMENT.addEmptyValue(section);
        // Should add empty string to the beginning
        assertEquals(1, section.getContent().size());
        assertEquals("", section.getContent().iterator().next());
    }

    @Test
    public void testAddEmptyValueForQualifications() {
        TextSection section = new TextSection();
        SectionType.QUALIFICATIONS.addEmptyValue(section);
        // Should add empty string to the beginning
        assertEquals(1, section.getContent().size());
    }

    @Test
    public void testAddEmptyValueForExperience() {
        OrganizationSection section = new OrganizationSection();
        SectionType.EXPERIENCE.addEmptyValue(section);
        // Should add Organization.EMPTY to the beginning
        assertFalse(section.getContent().isEmpty());
    }

    @Test
    public void testAddEmptyValueForEducation() {
        OrganizationSection section = new OrganizationSection();
        SectionType.EDUCATION.addEmptyValue(section);
        // Should add Organization.EMPTY to the beginning
        assertFalse(section.getContent().isEmpty());
    }

    @Test
    public void testAllTypesHaveTitle() {
        for (SectionType type : SectionType.values()) {
            assertNotNull(type.getTitle());
            assertFalse(type.getTitle().isEmpty());
        }
    }

    @Test
    public void testAllTypesHaveSectionClass() {
        for (SectionType type : SectionType.values()) {
            assertNotNull(type.getSectionClass());
        }
    }

    @Test
    public void testTextSectionTypes() {
        assertEquals(SectionClass.TEXT, SectionType.OBJECTIVE.getSectionClass());
        assertEquals(SectionClass.TEXT, SectionType.ACHIEVEMENT.getSectionClass());
        assertEquals(SectionClass.TEXT, SectionType.QUALIFICATIONS.getSectionClass());
    }

    @Test
    public void testOrganizationSectionTypes() {
        assertEquals(SectionClass.ORGANIZATION, SectionType.EXPERIENCE.getSectionClass());
        assertEquals(SectionClass.ORGANIZATION, SectionType.EDUCATION.getSectionClass());
    }

    @Test
    public void testEnumComparison() {
        assertSame(SectionType.OBJECTIVE, SectionType.OBJECTIVE);
        assertNotSame(SectionType.OBJECTIVE, SectionType.ACHIEVEMENT);
    }

    @Test
    public void testEnumOrdinal() {
        assertTrue(SectionType.OBJECTIVE.ordinal() >= 0);
        assertTrue(SectionType.EDUCATION.ordinal() < SectionType.values().length);
    }

    @Test
    public void testGetTitleReturnsCorrectValues() {
        assertEquals("Позиция", SectionType.OBJECTIVE.getTitle());
        assertEquals("Достижения", SectionType.ACHIEVEMENT.getTitle());
        assertEquals("Квалификация", SectionType.QUALIFICATIONS.getTitle());
        assertEquals("Опыт работы", SectionType.EXPERIENCE.getTitle());
        assertEquals("Образование", SectionType.EDUCATION.getTitle());
    }

    @Test
    public void testAddEmptyValueWithExistingContent() {
        TextSection section = new TextSection("Existing content");
        SectionType.ACHIEVEMENT.addEmptyValue(section);

        // Should add empty string to the beginning
        assertEquals(2, section.getContent().size());
        assertEquals("", section.getContent().iterator().next());
    }

    @Test
    public void testAddEmptyValueForOrganizationWithExistingOrgs() {
        Organization existingOrg = new Organization("Company", "https://company.com");
        OrganizationSection section = new OrganizationSection(existingOrg);

        SectionType.EXPERIENCE.addEmptyValue(section);

        // Should add empty period to existing org and add EMPTY org
        assertTrue(section.getContent().size() > 1);
    }

    @Test
    public void testObjectiveAddEmptyValueDoesNothing() {
        TextSection section = new TextSection("Content");
        int sizeBefore = section.getContent().size();

        SectionType.OBJECTIVE.addEmptyValue(section);

        assertEquals(sizeBefore, section.getContent().size());
    }
}
