package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.exception.ExistStorageException;
import pro.bolshakov.resumestorage.exception.NotExistStorageException;
import pro.bolshakov.resumestorage.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage{

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        int ind = storage.indexOf(r);
        if(ind == -1){
            throw new NotExistStorageException(r.getUuid());
        }
        storage.add(ind, r);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i<storage.size(); i++){
            if(storage.get(i).getUuid().equals(uuid)){
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Integer)searchKey).intValue());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Integer)searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.set((Integer)searchKey, r);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }


}
