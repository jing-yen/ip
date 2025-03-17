package util;

import exception.VoyagerException;

/**
 * Parses user commands and executes corresponding actions on the TaskList.
 */
public class Parser {
    private Ui ui = Ui.getInstance();

    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String FIND = "find";

    /**
     * Parses the given command and performs the corresponding action on the task list.
     *
     * @param taskList The TaskList to be modified.
     * @param commands Array of command strings to parse and execute.
     * @return True if the application should continue running, false if it should terminate (on "bye" command).
     */
    public boolean parse(TaskList taskList, String... commands) {
        boolean isMark = false;

        for (String command: commands) {
            try {
                String[] parts = command.split(" ", 2);

                switch (parts[0]) {
                    case FIND:
                        ui.speak("Here are the matching tasks in your list:");
                        taskList
                                .getAllTasks()
                                .stream()
                                .filter(task -> task.getDescription().contains(parts[1]))
                                .forEach(task -> ui.speak(task.toString()));
                        break;
                    case LIST:
                        taskList.list();
                        break;
                    case BYE:
                        return false;
                    case MARK:
                        isMark = true;
                    case UNMARK:
                        taskList.mark(isMark, Integer.parseInt(parts[1]));
                        break;
                    case TODO:
                        taskList.create(TaskList.TODO, parts[1]);
                        break;
                    case EVENT:
                        taskList.create(TaskList.EVENT, parts[1]);
                        break;
                    case DEADLINE:
                        taskList.create(TaskList.DEADLINE, parts[1]);
                        break;
                    case DELETE:
                        taskList.delete(Integer.parseInt(parts[1]));
                        break;
                    default:
                        throw new VoyagerException(VoyagerException.Cause.INVALID_COMMAND);
                }
            } catch (VoyagerException e) {
                ui.printException(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printException("Invalid parameter for command: " + command.split(" ")[0]);
            }
        }

        ui.drawLine();
        return true;
    }
}
