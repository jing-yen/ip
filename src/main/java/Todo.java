/**
 * Represents a Todo task.
 * A Todo task is a simple task without any specific deadline or time frame.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * @return String representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[D][" + (this.isDone?"done":"undone") + "]: { desc: " + this.description + " }";
    }

    /**
     * Returns a command string representation of the Todo task.
     * This is used for saving the task to a file.
     * @return Command string representation of the Todo task.
     */
    @Override
    public String toCommand() {
        return "todo " + description;
    }
}
