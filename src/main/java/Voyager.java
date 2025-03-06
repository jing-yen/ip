import exception.VoyagerException;
import util.Parser;
import util.Storage;
import util.TaskList;
import util.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Voyager {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

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

    public static void main(String[] args) {
        new Voyager("tasks.txt").run();
    }
}
