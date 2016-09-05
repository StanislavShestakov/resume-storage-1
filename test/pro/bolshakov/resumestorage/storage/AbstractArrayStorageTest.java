package pro.bolshakov.resumestorage.storage;

import org.junit.Before;
import org.junit.Test;
import pro.bolshakov.resumestorage.exception.ExistStorageException;
import pro.bolshakov.resumestorage.exception.NotExistStorageException;
import pro.bolshakov.resumestorage.exception.StorageException;
import pro.bolshakov.resumestorage.model.Resume;


import static org.junit.Assert.*;

public class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void testSaveOverflow() throws Exception {
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT+1; i++){
            storage.save(new Resume());
        }
    }

}