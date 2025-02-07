public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public String toString() {
        return "[D][" + (this.isDone?"done":"undone") + "]: { desc: " + this.description + " }";
    }
}
