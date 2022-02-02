package it.unimol.decathlon.app;

public class SlotException extends Exception {
    private int code;

    public SlotException(int code, String error) {
        super(error);
        this.code = code;
    }

    public static int MAX_SLOT_ERR = 0;

    public static int PREVIOUS_SLOT_ERR = 1;

    public int getCode() {
        return this.code;
    }
}
