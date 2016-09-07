package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.exception.ExistStorageException;
import pro.bolshakov.resumestorage.exception.NotExistStorageException;
import pro.bolshakov.resumestorage.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract List<Resume> doCopyAll();

    public void save(Resume r){
        SK searchKey = getNotExistSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        SK searchKey = getExistSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        SK searchKey = getExistSearchKey(uuid);
        return doGet(searchKey);
    }

    public void update(Resume r) {
        SK searchKey = getExistSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    private SK getExistSearchKey(String uuid){
        SK searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)){
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistSearchKey(String uuid){
        SK searchKey = getSearchKey(uuid);
        if(isExist(searchKey)){
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted(){
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }



}
