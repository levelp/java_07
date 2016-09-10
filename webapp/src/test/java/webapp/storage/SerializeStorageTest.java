package webapp.storage;

/**
 * Тестирование сохранения в сериализованный файл
 */
public class SerializeStorageTest extends StorageTest {
    static {
        storage = new SerializeStorage(STORAGE_DIR);
    }
}
