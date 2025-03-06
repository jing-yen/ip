package task;

public class Event extends Task {
    private String from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "]: { desc: "
                + this.description + ", from: " + from + ", to: " + to + " }";
    }

    @Override
    public String toCommand() {
        return "event " + description + " /from " + from + " /to " + to;
    }
}
