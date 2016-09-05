package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.model.Resume;

import java.util.Comparator;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    List<Resume> getAllSorted();

    int size();
}
