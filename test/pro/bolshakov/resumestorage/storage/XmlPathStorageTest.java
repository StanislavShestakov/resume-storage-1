package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.storage.serializer.ObjectStreamSerializer;
import pro.bolshakov.resumestorage.storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest{

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new XmlStreamSerializer()));
    }
}