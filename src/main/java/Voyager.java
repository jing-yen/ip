import util.Parser;
import util.Storage;
import util.TaskList;
import util.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class for the Voyager application.
 * Initializes and runs the chatbot.
 */
public class Voyager {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructor for Voyager.
     * Initializes UI, Storage, Parser, and TaskList.
     * Loads tasks from storage file if it exists.
     *
     * @param filePath Path to the file used for storing tasks.
     */
    public Voyager(String filePath) {
        ui = Ui.getInstance();
        storage = new Storage(filePath);
        parser = new Parser();
        taskList = new TaskList();

        ui.beSilent(true);
        try {
            parser.parse(taskList, storage.loadAsCommands());
        } catch (FileNotFoundException e) {
            ui.printException("File not found");
        }
        ui.beSilent(false);
    }

    /**
     * Runs the Voyager application.
     * Greets the user, then enters a loop to process user commands until the 'bye' command is given.
     * Saves tasks to storage after each command.
     */
    public void run() {
        ui.sayHi();

        String input;
        do {
            input = ui.listen();
            try{
                storage.write(taskList);
            } catch (IOException e) {
                ui.printException("The file doesn't like me");
            }
        } while (parser.parse(taskList, input));

        ui.sayBye();
    }

    /**
     * Main entry point for the Voyager application.
     * Creates a new Voyager instance and runs it.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Voyager("tasks.txt").run();
    }
}
