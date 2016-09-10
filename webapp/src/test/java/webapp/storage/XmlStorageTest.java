package webapp.storage;


public class XmlStorageTest extends StorageTest {
    static {
        storage = new XmlStorage(STORAGE_DIR);
    }
}
