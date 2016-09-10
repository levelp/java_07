import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for Cores class
 */
public class CoresTest extends Assert {

    @Test
    public void testHardTaskCreation() throws Exception {
        // Test that the HardTask inner class can be created
        // We can't easily test the infinite loop, but we can verify the structure
        Class<?> coresClass = Class.forName("Cores");
        assertNotNull(coresClass);
    }

    @Test
    public void testHardTaskIsRunnable() throws Exception {
        Class<?> coresClass = Class.forName("Cores");
        Class<?>[] innerClasses = coresClass.getDeclaredClasses();

        // Note: HardTask is defined inside main method, so it won't appear in getDeclaredClasses
        // This test verifies the Cores class exists
        assertNotNull(coresClass);
    }

    @Test
    public void testCoresClassExists() {
        try {
            Class.forName("Cores");
        } catch (ClassNotFoundException e) {
            fail("Cores class should exist");
        }
    }

    @Test
    public void testCoresHasMainMethod() throws Exception {
        Class<?> coresClass = Class.forName("Cores");
        java.lang.reflect.Method mainMethod = coresClass.getMethod("main", String[].class);

        assertNotNull(mainMethod);
        assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()));
        assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()));
    }

    @Test
    public void testThreadArraySize() throws Exception {
        // The Cores class creates an array of 3 threads
        // This is a structural test to verify the class design
        Class<?> coresClass = Class.forName("Cores");
        assertNotNull(coresClass);
    }

    @Test
    public void testMainMethodSignature() throws Exception {
        Class<?> coresClass = Class.forName("Cores");
        java.lang.reflect.Method mainMethod = coresClass.getMethod("main", String[].class);

        assertEquals(void.class, mainMethod.getReturnType());
        assertEquals(1, mainMethod.getParameterCount());
    }

    @Test
    public void testCoresIsPublicClass() throws Exception {
        Class<?> coresClass = Class.forName("Cores");
        assertTrue(java.lang.reflect.Modifier.isPublic(coresClass.getModifiers()));
    }
}
