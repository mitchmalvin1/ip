package task;

/**
 * Represents a class to hold the basic information about the Task such as the {@link #description}.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon()+ "] " + description;
    }

    public String toSaveString() {
        return toString();
    }
}
