public class Deadline extends Task {
    private String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String toString() {
        return "[D][" + (this.isDone?"done":"undone") + "]:  { desc: "
                + this.description + ", by: " + date + " }";
    }
    public String toCommand() {
        return "deadline " + description + " /by " + date;
    }
}
