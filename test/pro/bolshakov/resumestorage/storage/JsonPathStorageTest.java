package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest{

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new JsonStreamSerializer()));
    }
}