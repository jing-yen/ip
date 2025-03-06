package util;

import exception.VoyagerException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

public class TaskList {
    public static final int TODO = 0;
    public static final int DEADLINE = 1;
    public static final int EVENT = 2;

    private int count = 0;
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void create(int type, String input) {
        switch (type) {
            case TODO:
                String desc = input.trim();
                tasks.add(count++, new Todo(desc));
                System.out.println("Roger. Ground control requests for Todo: ");
                System.out.println("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+ tasks.get(count - 1).toString());
                System.out.println("My memory bank is "+(count)+"/100 full.");
                break;
            case DEADLINE:
                desc = input.split("/by")[0].trim();
                String date = input.split("/by")[1].trim();
                tasks.add(count++, new Deadline(desc, date));
                System.out.println("Roger. Ground control requests for Deadline: ");
                System.out.println("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+ tasks.get(count - 1).toString());
                System.out.println("My memory bank is "+(count)+"/100 full.");
                break;
            case EVENT:
                desc = input.split("/from")[0].trim();
                String from = input.split("/from", 2)[1].split("/to")[0].trim();
                String to = input.split("/to ")[1];
                tasks.add(count++, new Event(desc, from, to));
                System.out.println("Roger. Ground control requests for Event: ");
                System.out.println("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+ tasks.get(count - 1).toString());
                System.out.println("My memory bank is "+(count)+"/100 full.");
                break;
        }
    }
    
    public void mark(boolean isMark, int idx) throws VoyagerException {
        if (idx>=count) {
            throw new VoyagerException(VoyagerException.Cause.INVALID_INDEX);
        }

        String idxInBinary = Integer.toBinaryString((1 << 8) | idx).substring(1);
        if (isMark) {
            tasks.get(idx).setIsDone(true);
            System.out.println("Beep-boop. Marked task "+ idxInBinary +" as done.");
        } else {
            tasks.get(idx).setIsDone(false);
            System.out.println("Beeeeeeep. Unmarked task "+ idxInBinary +".");
        }

        System.out.println("  "+ idxInBinary +". "+ tasks.get(count - 1).toString());

    }

    public void delete(int idx) throws VoyagerException, IndexOutOfBoundsException {
        if (idx>=count) {
            throw new VoyagerException(VoyagerException.Cause.INVALID_INDEX);
        }

        tasks.remove(idx);
        System.out.println("You have purged item "+(idx)+" from my memory bank.");
        System.out.println("My memory bank is "+(--count)+"/100 full.");
    }

    public void list() {
        System.out.println("Accessing my Digital Tape Recorder (DTR)...");

        if (tasks.isEmpty()) System.out.println("My memory is empty.");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Integer.toBinaryString( (1 << 8) | i ).substring( 1 )
                    +". "+ tasks.get(i).toString());
        }
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
