package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest{

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new DataStreamSerializer()));
    }
}