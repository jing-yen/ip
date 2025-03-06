package task;

/**
 * Represents a todo task.
 * Inherits from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * Includes task type, status icon, and description.
     *
     * @return String representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "]: { desc: " + this.description + " }";
    }

    /**
     * Returns a command string representation of the Todo task.
     * Used for saving and loading tasks from storage.
     *
     * @return Command string for Todo task.
     */
    @Override
    public String toCommand() {
        return "todo " + description;
    }
}
