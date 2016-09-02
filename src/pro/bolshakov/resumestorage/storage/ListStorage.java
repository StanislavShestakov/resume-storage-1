package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.exception.ExistStorageException;
import pro.bolshakov.resumestorage.exception.NotExistStorageException;
import pro.bolshakov.resumestorage.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage{

    List<Resume> storage = new ArrayList<>();

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
    public void save(Resume r) {
        if(storage.contains(r)){
            throw new ExistStorageException(r.getUuid());
        }
        storage.add(r);
    }

    @Override
    public Resume get(String uuid) {

        Resume r = null;
        for (Resume resume: storage){
            if(resume.getUuid().equals(uuid)){
                r = resume;
                break;
            }
        }

        if(r == null){
            throw new NotExistStorageException(uuid);
        }

        return r;
    }

    @Override
    public void delete(String uuid) {

        Iterator<Resume> iterator = storage.iterator();
        while (iterator.hasNext()){
            Resume r = iterator.next();
            if(r.getUuid().equals(uuid)){
                iterator.remove();
                return;
            }
        }

        throw new NotExistStorageException(uuid);

    }

    @Override
    public Resume[] getAll() {
        Resume[] arr = new Resume[storage.size()];
        storage.toArray(arr);
        return arr;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
