package webapp.storage;

import java.io.File;

public class DataStreamStorageTest extends StorageTest {
    static {
        File file = new File(STORAGE_DIR);
        file.mkdir();
        storage = new DataStreamStorage(STORAGE_DIR);
    }
}
