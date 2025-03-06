package exception;

public class VoyagerException extends Exception {
    public enum Cause {
        INVALID_COMMAND("...... I cannot process.... this... request ......"),
        INVALID_INDEX("Number not in list. Remember to write in decimal."),
        INVALID_PARAMETER("Missing parameters after keyword.");

        public final String label;

        private Cause(String label) {
            this.label = label;
        }
    }
    public VoyagerException(Cause c) {
        super(c.label);
    }
}
