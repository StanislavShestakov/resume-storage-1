package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.exception.ExistStorageException;
import pro.bolshakov.resumestorage.exception.NotExistStorageException;
import pro.bolshakov.resumestorage.exception.StorageException;
import pro.bolshakov.resumestorage.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage{

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract Integer getSearchKey (String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

    @Override
    protected void doSave(Resume r, Object index) {
        if (size == storage.length){
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertElement(r, (Integer) index);
        size++;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected void doDelete(Object index) {
        fillDeletedElement((Integer) index);
        storage[size-1] = null;
        size--;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return ((Integer) index) >= 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

}
