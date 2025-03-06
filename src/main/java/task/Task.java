package task;

/**
 * Abstract class representing a generic task.
 * Serves as a base class for different types of tasks (Todo, Deadline, Event).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "/" if done, "X" if not done.
     *
     * @return Status icon string.
     */
    public String getStatusIcon() {
        return (isDone ? "/" : "X");
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param b True if task is done, false otherwise.
     */
    public void setIsDone(boolean b) {
        isDone = b;
    }

    /**
     * Returns a string representation of the Task.
     * Must be implemented by subclasses.
     *
     * @return String representation of the Task.
     */
    public abstract String toString();

    /**
     * Returns a command string representation of the Task.
     * Used for saving and loading tasks from storage.
     * Must be implemented by subclasses.
     *
     * @return Command string for Task.
     */
    public abstract String toCommand();
}
