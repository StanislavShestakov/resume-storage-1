package pro.bolshakov.resumestorage.storage;

import org.junit.Before;
import org.junit.Test;
import pro.bolshakov.resumestorage.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractStorageTest {

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Name1");
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2, "Name2");
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3, "Name3");
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4, "Name4");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void testClear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void testUpdate() throws Exception {
        Resume r = new Resume(UUID_1, "New Name");
        storage.update(r);
        assertEquals(r, storage.get(UUID_1));
    }

    @Test
    public void testSave() throws Exception {
        storage.save(RESUME_4);
        assertArrayWithSort(RESUME_1,RESUME_2,RESUME_3,RESUME_4);
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(RESUME_1, storage.get(UUID_1));
        assertEquals(RESUME_2, storage.get(UUID_2));
        assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(UUID_1);
        assertArrayWithSort(RESUME_2,RESUME_3);
    }

    @Test
    public void testGetAll() throws Exception {
        assertArrayWithSort(RESUME_1,RESUME_2,RESUME_3);
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(3, storage.size());
    }

    private void assertArrayWithSort(Resume... resumes){
        List<Resume> arr = Arrays.asList(resumes);
        assertTrue(arr.containsAll(storage.getAllSorted()));
    }

}