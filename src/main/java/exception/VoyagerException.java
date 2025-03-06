package exception;

/**
 * Custom exception class for Voyager application.
 * Represents exceptions specific to the Voyager application logic.
 */
public class VoyagerException extends Exception {
    /**
     * Enum representing different causes of VoyagerException.
     */
    public enum Cause {
        /**
         * Indicates an invalid command was entered by the user.
         */
        INVALID_COMMAND("...... I cannot process.... this... request ......"),
        /**
         * Indicates an invalid index was provided, e.g., when marking or deleting a task.
         */
        INVALID_INDEX("Number not in list. Remember to write in decimal."),
        /**
         * Indicates missing parameters for a command.
         */
        INVALID_PARAMETER("Missing parameters after keyword.");

        public final String label;

        private Cause(String label) {
            this.label = label;
        }
    }

    /**
     * Constructor for VoyagerException.
     *
     * @param c The Cause of the exception.
     */
    public VoyagerException(Cause c) {
        super(c.label);
    }
}
