package task;

/**
 * Represents a task with a deadline.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    private String date;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline task.
     * @param date        Deadline date/time.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes task type, status icon, description, and deadline date.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "]:  { desc: "
                + this.description + ", by: " + date + " }";
    }

    /**
     * Returns a command string representation of the Deadline task.
     * Used for saving and loading tasks from storage.
     *
     * @return Command string for Deadline task.
     */
    @Override
    public String toCommand() {
        return "deadline " + description + " /by " + date;
    }
}
