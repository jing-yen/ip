package util;

import exception.VoyagerException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

/**
 * Manages a list of tasks.
 * Provides methods to create, mark, unmark, delete, and list tasks.
 */
public class TaskList {
    private Ui ui = Ui.getInstance();

    public static final int TODO = 0;
    public static final int DEADLINE = 1;
    public static final int EVENT = 2;

    private int count = 0;
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates a new task and adds it to the task list.
     *
     * @param type  The type of task to create (TaskList.TODO, TaskList.DEADLINE, TaskList.EVENT).
     * @param input Command string containing task description and parameters.
     */
    public void create(int type, String input) {
        switch (type) {
            case TODO:
                String desc = input.trim();
                tasks.add(count++, new Todo(desc));
                ui.speak("Roger. Ground control requests for Todo: ");
                ui.speak("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+ tasks.get(count - 1).toString());
                ui.speak("My memory bank is "+(count)+"/100 full.");
                break;
            case DEADLINE:
                desc = input.split("/by")[0].trim();
                String date = input.split("/by")[1].trim();
                tasks.add(count++, new Deadline(desc, date));
                ui.speak("Roger. Ground control requests for Deadline: ");
                ui.speak("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+ tasks.get(count - 1).toString());
                ui.speak("My memory bank is "+(count)+"/100 full.");
                break;
            case EVENT:
                desc = input.split("/from")[0].trim();
                String from = input.split("/from", 2)[1].split("/to")[0].trim();
                String to = input.split("/to ")[1];
                tasks.add(count++, new Event(desc, from, to));
                ui.speak("Roger. Ground control requests for Event: ");
                ui.speak("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+ tasks.get(count - 1).toString());
                ui.speak("My memory bank is "+(count)+"/100 full.");
                break;
        }
    }

    /**
     * Marks or unmarks a task as done.
     *
     * @param isMark True to mark as done, false to unmark.
     * @param idx    Index of the task to mark/unmark (0-indexed).
     * @throws VoyagerException If the index is invalid.
     */
    public void mark(boolean isMark, int idx) throws VoyagerException {
        if (idx>=count) {
            throw new VoyagerException(VoyagerException.Cause.INVALID_INDEX);
        }

        String idxInBinary = Integer.toBinaryString((1 << 8) | idx).substring(1);
        if (isMark) {
            tasks.get(idx).setIsDone(true);
            ui.speak("Beep-boop. Marked task "+ idxInBinary +" as done.");
        } else {
            tasks.get(idx).setIsDone(false);
            ui.speak("Beeeeeeep. Unmarked task "+ idxInBinary +".");
        }

        ui.speak("  "+ idxInBinary +". "+ tasks.get(idx).toString());

    }

    /**
     * Deletes a task from the task list.
     *
     * @param idx Index of the task to delete (0-indexed).
     * @throws VoyagerException         If the index is invalid.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void delete(int idx) throws VoyagerException, IndexOutOfBoundsException {
        if (idx>=count) {
            throw new VoyagerException(VoyagerException.Cause.INVALID_INDEX);
        }

        tasks.remove(idx);
        ui.speak("You have purged item "+(idx)+" from my memory bank.");
        ui.speak("My memory bank is "+(--count)+"/100 full.");
    }

    /**
     * Lists all tasks in the task list.
     * Prints task index and task description for each task.
     * If the task list is empty, prints a message indicating that.
     */
    public void list() {
        ui.speak("Accessing my Digital Tape Recorder (DTR)...");

        if (tasks.isEmpty()) ui.speak("My memory is empty.");
        for (int i = 0; i < tasks.size(); i++) {
            ui.speak(Integer.toBinaryString( (1 << 8) | i ).substring( 1 )
                    +". "+ tasks.get(i).toString());
        }
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
