package pro.bolshakov.resumestorage.storage;

import pro.bolshakov.resumestorage.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

     public void save(Resume r) {

        if (r == null) return;
        int ind = getIndex(r.getUuid());
        if(ind != -1){
            System.out.println(String.format(THE_STORAGE_ALREADY_HAVE_A_RESUME_BY_UUID_S, r.getUuid()));
            return;
        }

        if (size == storage.length){
            System.out.println(STORAGE_IS_FULL);
            return;
//            Arrays.copyOf(storage, storage.length * 2);
        }

        storage[size++] = r;

    }

    public void delete(String uuid) {
        int ind = getIndex(uuid);
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
            System.out.println(String.format(RESUME_DID_NOT_FIND_BY_UUID_S, uuid));
        }
    }

     protected int getIndex(String uuid){
        int j = -1;
        for (int i=0;i<size;i++){
            if (storage[i].getUuid().equals(uuid)){
                j = i;
                break;
            }
        }
        return j;
    }

}
