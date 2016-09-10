import org.junit.Assert;
import org.junit.Test;

/**
 * Comprehensive unit tests for ThreadDebug class and its inner MyClass
 */
public class ThreadDebugTest extends Assert {

    @Test
    public void testThreadDebugClassExists() {
        try {
            Class.forName("ThreadDebug");
        } catch (ClassNotFoundException e) {
            fail("ThreadDebug class should exist");
        }
    }

    @Test
    public void testMyClassExists() throws Exception {
        Class<?> threadDebugClass = Class.forName("ThreadDebug");
        Class<?>[] innerClasses = threadDebugClass.getDeclaredClasses();

        boolean foundMyClass = false;
        for (Class<?> innerClass : innerClasses) {
            if (innerClass.getSimpleName().equals("MyClass")) {
                foundMyClass = true;
                break;
            }
        }
        assertTrue("MyClass should exist as inner class", foundMyClass);
    }

    @Test
    public void testMyClassCreation() throws Exception {
        Class<?> myClass = Class.forName("ThreadDebug$MyClass");
        Object instance = myClass.newInstance();
        assertNotNull(instance);
    }

    @Test
    public void testMyClassHasIField() throws Exception {
        Class<?> myClass = Class.forName("ThreadDebug$MyClass");
        java.lang.reflect.Field iField = myClass.getField("i");
        assertNotNull(iField);
        assertEquals(int.class, iField.getType());
    }

    @Test
    public void testMyClassHasIncMethod() throws Exception {
        Class<?> myClass = Class.forName("ThreadDebug$MyClass");
        java.lang.reflect.Method incMethod = myClass.getMethod("inc");
        assertNotNull(incMethod);
        assertTrue(java.lang.reflect.Modifier.isSynchronized(incMethod.getModifiers()));
    }

    @Test
    public void testMyClassIncMethodIsSynchronized() throws Exception {
        Class<?> myClass = Class.forName("ThreadDebug$MyClass");
        java.lang.reflect.Method incMethod = myClass.getMethod("inc");
        assertTrue(java.lang.reflect.Modifier.isSynchronized(incMethod.getModifiers()));
    }

    @Test
    public void testMyClassIncrement() throws Exception {
        Class<?> myClass = Class.forName("ThreadDebug$MyClass");
        Object instance = myClass.newInstance();
        java.lang.reflect.Method incMethod = myClass.getMethod("inc");
        java.lang.reflect.Field iField = myClass.getField("i");

        assertEquals(0, iField.getInt(instance));

        incMethod.invoke(instance);
        assertEquals(1, iField.getInt(instance));

        incMethod.invoke(instance);
        assertEquals(2, iField.getInt(instance));
    }

    @Test
    public void testMyClassIncrementMultipleTimes() throws Exception {
        Class<?> myClass = Class.forName("ThreadDebug$MyClass");
        Object instance = myClass.newInstance();
        java.lang.reflect.Method incMethod = myClass.getMethod("inc");
        java.lang.reflect.Field iField = myClass.getField("i");

        for (int i = 0; i < 10; i++) {
            incMethod.invoke(instance);
        }

        assertEquals(10, iField.getInt(instance));
    }

    @Test
    public void testThreadDebugHasMainMethod() throws Exception {
        Class<?> clazz = Class.forName("ThreadDebug");
        java.lang.reflect.Method mainMethod = clazz.getMethod("main", String[].class);

        assertNotNull(mainMethod);
        assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()));
        assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()));
    }

    @Test
    public void testMyClassIsStatic() throws Exception {
        Class<?> myClass = Class.forName("ThreadDebug$MyClass");
        assertTrue(java.lang.reflect.Modifier.isStatic(myClass.getModifiers()));
        assertTrue(java.lang.reflect.Modifier.isPublic(myClass.getModifiers()));
    }

    @Test
    public void testMyClassThreadSafety() throws Exception {
        Class<?> myClass = Class.forName("ThreadDebug$MyClass");
        Object instance = myClass.newInstance();
        java.lang.reflect.Method incMethod = myClass.getMethod("inc");
        java.lang.reflect.Field iField = myClass.getField("i");

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    incMethod.invoke(instance);
                }
            } catch (Exception e) {
                fail("Exception in thread: " + e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    incMethod.invoke(instance);
                }
            } catch (Exception e) {
                fail("Exception in thread: " + e.getMessage());
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertEquals(200, iField.getInt(instance));
    }

    @Test
    public void testMainMethodSignature() throws Exception {
        Class<?> clazz = Class.forName("ThreadDebug");
        java.lang.reflect.Method mainMethod = clazz.getMethod("main", String[].class);

        assertEquals(void.class, mainMethod.getReturnType());
        assertEquals(1, mainMethod.getParameterCount());

        Class<?>[] exceptions = mainMethod.getExceptionTypes();
        boolean throwsInterruptedException = false;
        for (Class<?> ex : exceptions) {
            if (ex.equals(InterruptedException.class)) {
                throwsInterruptedException = true;
                break;
            }
        }
        assertTrue(throwsInterruptedException);
    }

    @Test
    public void testMyClassPublicModifier() throws Exception {
        Class<?> myClass = Class.forName("ThreadDebug$MyClass");
        assertTrue(java.lang.reflect.Modifier.isPublic(myClass.getModifiers()));
    }

    @Test
    public void testMyClassStaticModifier() throws Exception {
        Class<?> myClass = Class.forName("ThreadDebug$MyClass");
        assertTrue(java.lang.reflect.Modifier.isStatic(myClass.getModifiers()));
    }
}
