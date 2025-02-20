public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "done" : "undone");
    }

    public void setIsDone(boolean b) {
        isDone = b;
    }

    public abstract String toString();
    public abstract String toCommand();
}