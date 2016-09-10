package webapp.storage;

public class SerializeStorageTest extends StorageTest {
    static {
        storage = new SerializeStorage("file_storage");
    }
}
