package util;

import exception.VoyagerException;

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

    public boolean parse(TaskList taskList, String... commands) {
        boolean isMark = false;

        for (String command: commands) {
            try {
                String[] parts = command.split(" ", 2);

                switch (parts[0]) {
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
        return true;
    }
}
