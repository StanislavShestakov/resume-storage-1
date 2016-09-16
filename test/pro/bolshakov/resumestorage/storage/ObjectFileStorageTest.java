package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.storage.serializer.ObjectStreamSerializer;

import java.io.File;

public class ObjectFileStorageTest extends AbstractStorageTest{

    public ObjectFileStorageTest() {
        super(new FileStorage(new File(STORAGE_DIR), new ObjectStreamSerializer()));
    }
}