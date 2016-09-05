package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage{

    Map<String,Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put((String)searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.put((String)searchKey, r);
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
