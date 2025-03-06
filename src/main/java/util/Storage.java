package util;

import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath Path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the tasks from the TaskList to the storage file.
     * Each task is converted to its command string representation and written to a new line.
     *
     * @param taskList The TaskList to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void write(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String text = "";
        for (Task task: taskList.getAllTasks()) {
            if (task != null) {
                text += task.getStatusIcon() + task.toCommand() + System.lineSeparator();
            }
        }
        fw.write(text);
        fw.close();
    }

    /**
     * Loads tasks from the storage file and returns them as an array of command strings.
     * Each line in the file is assumed to be a command string representing a task.
     * Marked tasks are indicated by a leading '/' character in the file.
     *
     * @return An array of command strings loaded from the file.
     *         Returns an empty array if the file does not exist or is empty.
     * @throws FileNotFoundException If the storage file is not found.
     */
    public String[] loadAsCommands() throws FileNotFoundException {
        ArrayList<String> commands = new ArrayList<>();

        File f = new File(filePath);
        if (!f.exists()) return commands.toArray(new String[0]);

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.isEmpty()) continue;
            commands.add(line.substring(1));
            if (line.charAt(0)=='/') commands.add("mark "+(commands.size()-1));
        }

        return commands.toArray(new String[0]);
    }
}
