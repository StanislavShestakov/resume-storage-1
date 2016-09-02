package pro.bolshakov.resumestorage.storage;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListStorageTest extends AbstractStorageTest{

    public ListStorageTest() {
        super(new ListStorage());
    }
}