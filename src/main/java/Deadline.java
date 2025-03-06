/**
 * Represents a deadline task.
 * A deadline task is a task that needs to be done before a certain date/time.
 */
public class Deadline extends Task {
    private String date;

    /**
     * Constructor for Deadline.
     * @param description Description of the deadline task.
     * @param date Date/time of the deadline.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a string representation of the Deadline task.
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + (this.isDone?"done":"undone") + "]:  { desc: "
                + this.description + ", by: " + date + " }";
    }

    /**
     * Returns a command string representation of the Deadline task.
     * This is used for saving the task to a file.
     * @return Command string representation of the Deadline task.
     */
    @Override
    public String toCommand() {
        return "deadline " + description + " /by " + date;
    }
}
