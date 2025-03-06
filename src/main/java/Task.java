/**
 * Abstract class representing a Task.
 * It serves as a base class for different types of tasks like Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task, indicating whether it is done or undone.
     * @return "done" if the task is done, "undone" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "done" : "undone");
    }

    /**
     * Sets the completion status of the task.
     * @param b True if the task is done, false otherwise.
     */
    public void setIsDone(boolean b) {
        isDone = b;
    }

    /**
     * Returns a string representation of the Task.
     * This method is abstract and must be implemented by subclasses.
     * @return String representation of the Task.
     */
    public abstract String toString();

    /**
     * Returns a command string representation of the Task.
     * This method is abstract and must be implemented by subclasses.
     * This is used for saving the task to a file.
     * @return Command string representation of the Task.
     */
    public abstract String toCommand();
}
