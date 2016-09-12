package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.model.Resume;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public abstract class AdapterAbstractFilesStorage implements Storage {

    private static final boolean USE_PATH = true;
    AbstractStorage storage;

    public AdapterAbstractFilesStorage(String path) {
        if(USE_PATH){
            this.storage = new AbstractPathStorage(path) {
                @Override
                protected void doWrite(Resume r, OutputStream os) throws IOException {
                    AdapterAbstractFilesStorage.this.doWrite(r, os);
                }

                @Override
                protected Resume doRead(InputStream is) throws IOException {
                    return AdapterAbstractFilesStorage.this.doRead(is);
                }
            };
        }
        else {
            this.storage = new AbstractFileStorage(new File(path)) {
                @Override
                protected void doWrite(Resume r, OutputStream os) throws IOException {
                    AdapterAbstractFilesStorage.this.doWrite(r, os);
                }

                @Override
                protected Resume doRead(InputStream is) throws IOException {
                    return  AdapterAbstractFilesStorage.this.doRead(is);
                }
            };
        }


    }

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        storage.update(r);
    }

    @Override
    public void save(Resume r) {
        storage.save(r);
    }

    @Override
    public Resume get(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        storage.delete(uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage.getAllSorted();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
