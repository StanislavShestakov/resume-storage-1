import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        storage = new Resume[10000];
        size = 0;
    }

    void save(Resume r) {
        if (r == null) return;
        //find resume. if storage contain resume
        // then end
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
//                storage[i] = r;
                return;
            }
        }

        //if size full, create new array
        if (size == storage.length) Arrays.copyOf(storage, storage.length * 2);
        //add resume
        storage[size++] = r;

    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(uuid)){
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int j = -1;
        for (int i=0;i<size;i++){
            if (storage[i].uuid.equals(uuid)){
                j = i;
                break;
            }
        }

        if(j != -1){
            //taked from ArrayList
            int numMoved = size - j - 1;
            if (numMoved > 0)
                System.arraycopy(storage, j + 1, storage, j,
                        numMoved);
            storage[--size] = null;

        }
        else{
            System.out.println(String.format("Can not delete Resume(uuid=%s). Not found", uuid));
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, storage.length);
    }

    int size() {
        return size;
    }
}
