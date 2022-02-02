package it.unimol.decathlon.app;

public class AttemptException extends Exception {
    private int code;
    public AttemptException(int code, String error) {
        super(error);
        this.code = code;
    }

    public static int MAX_ATTEMPT_ERR = 0;

    public int getCode() {
        return this.code;
    }
}
