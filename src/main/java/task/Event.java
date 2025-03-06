package task;

/**
 * Represents an event task with a start and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {
    private String from, to;

    /**
     * Constructor for Event.
     *
     * @param description Description of the event task.
     * @param from        Start time of the event.
     * @param to          End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     * Includes task type, status icon, description, start time, and end time.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "]: { desc: "
                + this.description + ", from: " + from + ", to: " + to + " }";
    }

    /**
     * Returns a command string representation of the Event task.
     * Used for saving and loading tasks from storage.
     *
     * @return Command string for Event task.
     */
    @Override
    public String toCommand() {
        return "event " + description + " /from " + from + " /to " + to;
    }
}
