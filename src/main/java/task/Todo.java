package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "]: { desc: " + this.description + " }";
    }

    @Override
    public String toCommand() {
        return "todo " + description;
    }
}
