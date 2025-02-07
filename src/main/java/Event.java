public class Event extends Task {
    private String from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public String toString() {
        return "[D][" + (this.isDone?"done":"undone") + "]: { desc: "
                + this.description + ", from: " + from + ", to: " + to + " }";
    }
}