package webapp.storage;

import org.junit.Ignore;

@Ignore // TODO: надо настроить подключение к БД
public class SqlStorageTest extends StorageTest {
    static {
        storage = new SqlStorage();
    }
}
