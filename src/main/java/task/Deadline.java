package task;

public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D][" + (this.isDone?"done":"undone") + "]:  { desc: "
                + this.description + ", by: " + date + " }";
    }

    @Override
    public String toCommand() {
        return "deadline " + description + " /by " + date;
    }
}
