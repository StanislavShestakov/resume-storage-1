package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.exception.ExistStorageException;
import pro.bolshakov.resumestorage.exception.NotExistStorageException;
import pro.bolshakov.resumestorage.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    Map<String,Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        if(!storage.containsKey(r.getUuid())){
            throw new NotExistStorageException(r.getUuid());
        }
        storage.put(r.getUuid(), r);
    }

    @Override
    public void save(Resume r) {
        if(storage.containsKey(r.getUuid())){
            throw new ExistStorageException(r.getUuid());
        }
        storage.put(r.getUuid(), r);
    }

    @Override
    public Resume get(String uuid) {
        Resume r = storage.get(uuid);
        if(r == null){
            throw new NotExistStorageException(uuid);
        }
        return r;
    }

    @Override
    public void delete(String uuid) {
        Resume deletedResume = storage.remove(uuid);
        if(deletedResume == null){
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        Resume[] arr = new Resume[storage.size()];
        storage.values().toArray(arr);
        return arr;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
