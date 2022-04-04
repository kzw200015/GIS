package cc.jktu.gis.model.exception;

public abstract class UserException extends RuntimeException {

    public UserException(String msg) {
        super(msg);
    }

}
