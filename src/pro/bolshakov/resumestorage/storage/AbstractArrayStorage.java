package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{

    public static final String RESUME_DID_NOT_FIND_BY_UUID_S = "Resume did not find by uuid=%s";
    public static final String THE_STORAGE_ALREADY_HAVE_A_RESUME_BY_UUID_S = "The storage already have a resume by uuid=%s";
    public static final String STORAGE_IS_FULL = "Storage is full";

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
            System.out.println(String.format(THE_STORAGE_ALREADY_HAVE_A_RESUME_BY_UUID_S, r.getUuid()));
            return;
        }

        if (size == storage.length){
            System.out.println(STORAGE_IS_FULL);
            return;
        }
        insertElement(r, ind);
        size++;
    }

    public void delete(String uuid) {
        int ind = getIndex(uuid);
        if(ind < 0){
            System.out.println(String.format(RESUME_DID_NOT_FIND_BY_UUID_S, uuid));
            return;
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
            System.out.println(String.format(RESUME_DID_NOT_FIND_BY_UUID_S, uuid));
            return null;
        }
        else {
            return storage[ind];
        }
    }

    public void update(Resume r) {
        int ind = getIndex(r.getUuid());
        if(ind < 0){
            System.out.println(String.format(RESUME_DID_NOT_FIND_BY_UUID_S, r.getUuid()));
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
