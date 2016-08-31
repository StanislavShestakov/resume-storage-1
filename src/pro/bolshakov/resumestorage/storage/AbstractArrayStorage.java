package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.exception.ExistStorageException;
import pro.bolshakov.resumestorage.exception.NotExistStorageException;
import pro.bolshakov.resumestorage.exception.StorageException;
import pro.bolshakov.resumestorage.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r){

        if (r == null) return;
        int ind = getIndex(r.getUuid());
        if(ind > -1){
            throw new ExistStorageException(r.getUuid());
        }

        if (size == storage.length){
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertElement(r, ind);
        size++;
    }

    public void delete(String uuid) {
        int ind = getIndex(uuid);
        if(ind < 0){
            throw new NotExistStorageException(uuid);
        }

        fillDeletedElement(ind);
        storage[size-1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int ind = getIndex(uuid);
        if(ind < 0){
            throw new NotExistStorageException(uuid);
        }
        else {
            return storage[ind];
        }
    }

    public void update(Resume r) {
        int ind = getIndex(r.getUuid());
        if(ind < 0){
            throw new NotExistStorageException(r.getUuid());
        }
        else {
            storage[ind] = r;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

}
