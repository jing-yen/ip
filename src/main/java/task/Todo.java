package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[D][" + (this.isDone?"done":"undone") + "]: { desc: " + this.description + " }";
    }

    @Override
    public String toCommand() {
        return "todo " + description;
    }
}
