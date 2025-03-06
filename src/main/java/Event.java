/**
 * Represents an event task.
 * An event task is a task with a start and end time.
 */
public class Event extends Task {
    private String from, to;

    /**
     * Constructor for Event.
     * @param description Description of the event task.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        return "[D][" + (this.isDone?"done":"undone") + "]: { desc: "
                + this.description + ", from: " + from + ", to: " + to + " }";
    }

    /**
     * Returns a command string representation of the Event task.
     * This is used for saving the task to a file.
     * @return Command string representation of the Event task.
     */
    @Override
    public String toCommand() {
        return "event " + description + " /from " + from + " /to " + to;
    }
}
