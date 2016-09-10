package webapp;

import org.junit.Assert;
import org.junit.Test;
import webapp.model.Resume;

/**
 * Comprehensive unit tests for WebAppException
 */
public class WebAppExceptionTest extends Assert {

    @Test
    public void testConstructorWithMessage() {
        String message = "Test error message";
        WebAppException exception = new WebAppException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getResume());
        assertNull(exception.getUuid());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndThrowable() {
        String message = "Test error message";
        Throwable cause = new RuntimeException("Cause");
        WebAppException exception = new WebAppException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertNull(exception.getResume());
        assertNull(exception.getUuid());
    }

    @Test
    public void testConstructorWithMessageAndResume() {
        String message = "Test error message";
        Resume resume = new Resume("uuid-123");
        WebAppException exception = new WebAppException(message, resume);

        assertEquals(message, exception.getMessage());
        assertEquals(resume, exception.getResume());
        assertNull(exception.getUuid());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageResumeAndThrowable() {
        String message = "Test error message";
        Resume resume = new Resume("uuid-456");
        Throwable cause = new RuntimeException("Cause");
        WebAppException exception = new WebAppException(message, resume, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(resume, exception.getResume());
        assertEquals(cause, exception.getCause());
        assertNull(exception.getUuid());
    }

    @Test
    public void testConstructorWithMessageAndUuid() {
        String message = "Test error message";
        String uuid = "uuid-789";
        WebAppException exception = new WebAppException(message, uuid);

        assertEquals(message, exception.getMessage());
        assertEquals(uuid, exception.getUuid());
        assertNull(exception.getResume());
        assertNull(exception.getCause());
    }

    @Test
    public void testGetResume() {
        Resume resume = new Resume("test-uuid");
        WebAppException exception = new WebAppException("Message", resume);

        assertEquals(resume, exception.getResume());
    }

    @Test
    public void testGetResumeWhenNull() {
        WebAppException exception = new WebAppException("Message");
        assertNull(exception.getResume());
    }

    @Test
    public void testGetUuid() {
        String uuid = "test-uuid-123";
        WebAppException exception = new WebAppException("Message", uuid);

        assertEquals(uuid, exception.getUuid());
    }

    @Test
    public void testGetUuidWhenNull() {
        WebAppException exception = new WebAppException("Message");
        assertNull(exception.getUuid());
    }

    @Test
    public void testIsRuntimeException() {
        WebAppException exception = new WebAppException("Message");
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    public void testThrowException() {
        try {
            throw new WebAppException("Test exception");
        } catch (WebAppException e) {
            assertEquals("Test exception", e.getMessage());
        }
    }

    @Test
    public void testExceptionWithNullMessage() {
        WebAppException exception = new WebAppException(null);
        assertNull(exception.getMessage());
    }

    @Test
    public void testExceptionWithEmptyMessage() {
        WebAppException exception = new WebAppException("");
        assertEquals("", exception.getMessage());
    }

    @Test
    public void testExceptionWithNullResume() {
        WebAppException exception = new WebAppException("Message", (Resume) null);
        assertNull(exception.getResume());
    }

    @Test
    public void testExceptionWithNullUuid() {
        WebAppException exception = new WebAppException("Message", (String) null);
        assertNull(exception.getUuid());
    }

    @Test
    public void testExceptionWithNullCause() {
        WebAppException exception = new WebAppException("Message", (Throwable) null);
        assertNull(exception.getCause());
    }

    @Test
    public void testExceptionWithResumeAndCause() {
        Resume resume = new Resume("uuid");
        Throwable cause = new IllegalArgumentException("Cause");
        WebAppException exception = new WebAppException("Message", resume, cause);

        assertEquals("Message", exception.getMessage());
        assertEquals(resume, exception.getResume());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testExceptionCanBeCaught() {
        boolean caught = false;
        try {
            throw new WebAppException("Test");
        } catch (RuntimeException e) {
            caught = true;
            assertTrue(e instanceof WebAppException);
        }
        assertTrue(caught);
    }
}
