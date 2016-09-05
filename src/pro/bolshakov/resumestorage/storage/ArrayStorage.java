package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    @Override
    protected Integer getSearchKey(String uuid) {
        int j = -1;
        for (int i=0;i<size;i++){
            if (storage[i].getUuid().equals(uuid)){
                j = i;
                break;
            }
        }
        return j;
    }

    @Override
    protected void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[size-1];
    }

}
