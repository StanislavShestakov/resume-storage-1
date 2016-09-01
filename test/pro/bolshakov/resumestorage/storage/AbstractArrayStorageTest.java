package pro.bolshakov.resumestorage.storage;

import org.junit.Before;
import org.junit.Test;
import pro.bolshakov.resumestorage.exception.ExistStorageException;
import pro.bolshakov.resumestorage.exception.NotExistStorageException;
import pro.bolshakov.resumestorage.exception.StorageException;
import pro.bolshakov.resumestorage.model.Resume;


import static org.junit.Assert.*;

public class AbstractArrayStorageTest {

    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected static final int STORAGE_LIMIT = 10000;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(UUID_1, storage.get(UUID_1).getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void testGetNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void testClear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void testSave() throws Exception {
        Resume r4 = new Resume(UUID_4);
        storage.save(r4);
        assertEquals(r4,storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void testSaveExist() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void testSaveOverflow() throws Exception {
        for (int i = 0; i<STORAGE_LIMIT+1; i++){
            storage.save(new Resume());
        }
    }

    @Test
    public void testDelete() throws Exception {
        Resume[] arr = {new Resume(UUID_1), new Resume(UUID_2)};
        storage.delete(UUID_3);
        assertArrayEquals(arr, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void testDeleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void testUpdate() throws Exception {
        Resume r1 = new Resume(UUID_1);
        storage.update(r1);
        assertTrue(r1 == storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void testUpdateNotExist() throws Exception {
        Resume r1 = new Resume(UUID_4);
        storage.update(r1);
    }

    @Test
    public void testGetAll() throws Exception {
        Resume[] arr = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(arr, storage.getAll());
    }


}