package pro.bolshakov.resumestorage.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume not exist",uuid);
    }
}
