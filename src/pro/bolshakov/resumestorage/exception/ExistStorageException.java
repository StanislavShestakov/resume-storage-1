package pro.bolshakov.resumestorage.exception;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String uuid) {
        super("Resume not exist",uuid);
    }
}
