package pro.bolshakov.resumestorage.exception;

public class StorageException extends RuntimeException {

    protected final String uuid;

    public StorageException(String uuid) {
        this.uuid = uuid;
    }

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
