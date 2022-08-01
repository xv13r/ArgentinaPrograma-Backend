package ar.gob.inti.argentinaprograma.miportfolio.exception;

public class InternalServerError extends RuntimeException {

    public InternalServerError() {
    }

    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerError(Throwable cause) {
        super(cause);
    }
}
