package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
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

        ind = -ind - 1;
        for (int i = size; i > ind;i--){
            storage[i] = storage[i-1];
        }
        storage[ind] = r;
        size++;

    }

    public void delete(String uuid) {
        int ind = getIndex(uuid);
        if(ind != -1){
            //taken from ArrayList
            /*int numMoved = size - ind - 1;
            if (numMoved > 0)
                System.arraycopy(storage, ind + 1, storage, ind,
                        numMoved);
            storage[size-1] = null;*/

            //we save sort
            while (ind < storage.length){
                if(storage[ind]==null) break;
                storage[ind] = (ind == storage.length-1) ? null : storage[ind+1];
                ind++;
            }
            size--;
        }
        else{
            System.out.println(String.format(RESUME_DID_NOT_FIND_BY_UUID_S, uuid));
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage,0 , size, searchKey);
    }

}
