import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final String TEXT_ERROR = "ERROR";

    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        storage = new Resume[10000];
        size = 0;
    }

    void update(Resume r){
        int ind = getIndexByUuid(r.uuid);
        if(ind == -1){
            System.out.println(TEXT_ERROR);
        }
        else {
            storage[ind] = r;
        }
    }

    void save(Resume r) {

        if (r == null) return;
        int ind = getIndexByUuid(r.uuid);
        if(ind != -1){
            System.out.println(TEXT_ERROR);
            return;
        }

        if (size == storage.length){
            System.out.println("Storage is full");
            return;
//            Arrays.copyOf(storage, storage.length * 2);
        }

        storage[size++] = r;

    }

    Resume get(String uuid) {
        int ind = getIndexByUuid(uuid);
        if(ind == -1){
            System.out.println(TEXT_ERROR);
            return null;
        }
        else {
            return storage[ind];
        }
    }

    void delete(String uuid) {

        int ind = getIndexByUuid(uuid);
        if(ind != -1){
            //taken from ArrayList
            /*int numMoved = size - ind - 1;
            if (numMoved > 0)
                System.arraycopy(storage, ind + 1, storage, ind,
                        numMoved);
            storage[--size] = null;*/
            storage[ind] = storage[size-1];
            storage[size-1] = null;
            size--;
        }
        else{
            System.out.println(TEXT_ERROR);
        }

    }

    private int getIndexByUuid(String uuid){
        int j = -1;
        for (int i=0;i<size;i++){
            if (storage[i].uuid.equals(uuid)){
                j = i;
                break;
            }
        }
        return j;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
