package util;

import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
