import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Voyager is a personal assistant chatbot that helps users manage tasks.
 * It supports creating, listing, marking, unmarking, and deleting tasks.
 * Tasks can be of type Todo, Deadline, or Event.
 * Voyager persists tasks to a file named "phonograph.txt".
 */
public class Voyager {
    private static int count = 0;
    private static boolean isSilent = false;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";

    private static final String INTRO_GRAPHICS =
        """
                    .       .                   .       .      .     .      .
                    .    .         .    .            .     ______
                .           .             .               ////////
                            .    .   ________   .  .      /////////     .    .
                        .            |.____.  /\\        ./////////    .
                .                 .//      \\/  |\\     /////////
                    .       .    .//          \\ |  \\ /////////       .     .   .
                                ||.    .    .| |  ///////// .     .
                .    .         ||           | |//`,/////                .
                        .       \\\\        ./ //  /  \\/   .
            .                    \\\\.___./ //\\` '   ,_\\     .     .
                    .           .     \\ //////\\ , /   \\                 .    .
                                    .    ///////// \\|  '  |    .
                .        .          ///////// .   \\ _ /          .
                                    /////////                              .
                            .   ./////////     .     .
                    .           --------   .                  ..             .
            .               .        .         .                       .
                                    ________________________
            ____________------------                        -------------_________
        """;

    private static final String INTRO_TEXT = """
        In 1977, humanity launched two interstellar explorers, Voyager 1 and Voyager 2.
        These spacecraft have traveled billions of miles, carrying with them the Golden Record,
        a message to any potential extraterrestrial life. Today, Voyager continues its journey
        into the unknown, a testament to human curiosity and the desire to explore.
        """;

    private static final String spaceArt = """
              *       .         *          .         *       .         *          .
                  .       *         .         *       .         *         .       *
            """;

    /**
     * Prints the introductory message and Voyager graphics to the console.
     */
    public static void sayHi() {
        println(INTRO_TEXT);
        println("Hello from Voyager:\n" + INTRO_GRAPHICS + "\nWhat does my worldly companion request for?");
    }

    /**
     * Prints the goodbye message to the console.
     */
    public static void sayBye() {
        println("Bye. Hope not to see you in the cold, dark outer space!");
    }

    /**
     * Prints a decorative line to the console.
     */
    public static void drawLine() {
        println(spaceArt);
    }

    /**
     * Reads user input from the console.
     * @return The line of text entered by the user.
     */
    public static String listen() {
        Scanner in = new Scanner(System.in);
        print("(You) Ground ctrl > ");
        return in.nextLine();
    }

    /**
     * Lists all tasks currently stored in Voyager's memory.
     * If there are no tasks, it indicates that the memory is empty.
     * Tasks are displayed with their index in binary format and their string representation.
     */
    public static void list() {
        System.out.println("Accessing my Digital Tape Recorder (DTR)...");
        if (count==0) System.out.println("My memory is empty.");
        for (int i = 0; i < count; i++) {
            println(
                    Integer.toBinaryString( (1 << 8) | i ).substring( 1 )
                            +". "+ tasks.get(i).toString());
        }
    }

    /**
     * Marks or unmarks a task as done based on the provided index and boolean flag.
     * @param isMark True to mark the task as done, false to unmark.
     * @param idx The index of the task to mark/unmark (0-based).
     * @throws VoyagerIndexException If the provided index is out of bounds.
     * @throws IOException If there is an error writing to the file.
     */
    public static void mark(boolean isMark, int idx) throws VoyagerIndexException, IOException {
        if (idx>=count) {
            throw new VoyagerIndexException();
        }

        String idxInBinary = Integer.toBinaryString((1 << 8) | idx).substring(1);
        if (isMark) {
            tasks.get(idx).setIsDone(true);
            println("Beep-boop. Marked task "+ idxInBinary +" as done.");
        } else {
            tasks.get(idx).setIsDone(false);
            println("Beeeeeeep. Unmarked task "+ idxInBinary +".");
        }

        println("  "+ idxInBinary +". "+ tasks.get(count - 1).toString());

        writeToFile();
    }

    /**
     * Creates a new task based on the user input string.
     * Task type is determined by the first word of the input (todo, deadline, event).
     * @param input User input string containing the task command and details.
     * @throws IOException If there is an error writing to the file.
     */
    public static void create(String input) throws IOException {
        switch (input.split(" ")[0]) {
        case TODO:
            String desc = input.split(" ", 2)[1].trim();
            tasks.add(count++, new Todo(desc));
            println("Roger. Ground control requests for Todo: ");
            println("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+ tasks.get(count - 1).toString());
            println("My memory bank is "+(count)+"/100 full.");
            break;
        case DEADLINE:
            desc = input.split(" ", 2)[1].split("/by")[0].trim();
            String date = input.split("/by")[1].trim();
            tasks.add(count++, new Deadline(desc, date));
            println("Roger. Ground control requests for Deadline: ");
            println("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+ tasks.get(count - 1).toString());
            println("My memory bank is "+(count)+"/100 full.");
            break;
        case EVENT:
            desc = input.split(" ", 2)[1].split("/from")[0].trim();
            String from = input.split("/from", 2)[1].split("/to")[0].trim();
            String to = input.split("/to ")[1];
            tasks.add(count++, new Event(desc, from, to));
            println("Roger. Ground control requests for Event: ");
            println("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+ tasks.get(count - 1).toString());
            println("My memory bank is "+(count)+"/100 full.");
            break;
        }

        writeToFile();
    }

    /**
     * Writes the current list of tasks to the "phonograph.txt" file.
     * Each task is saved in a command format that can be parsed later.
     * @throws IOException If there is an error writing to the file.
     */
    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("phonograph.txt");
        String text = "";
        for (Task task: tasks) {
            if (task !=null) text += (task.isDone?'/':'X') + task.toCommand() + System.lineSeparator();
        }
        fw.write(text);
        fw.close();
    }

    /**
     * Initializes the task list from the "phonograph.txt" file at program startup.
     * Reads each line from the file, parses it as a command, and adds the corresponding task to the list.
     * If a task is marked as done in the file (indicated by '/'), it is marked as done after creation.
     */
    public static void initializeFromFile() {
        File f = new File("phonograph.txt");
        if (!f.exists()) return;

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.isEmpty()) continue;
                process(line.substring(1), false);
                if (line.charAt(0)=='/') process("mark "+(count-1), false);
            }
        } catch (Exception e) {
            println("Error: I can't access my golden phonograph record...");
        }
    }

    /**
     * Deletes a task from the task list based on its index.
     * @param idx The index of the task to delete (0-based).
     * @throws VoyagerIndexException If the provided index is out of bounds.
     * @throws IOException If there is an error writing to the file.
     */
    public static void delete(int idx) throws VoyagerIndexException, IOException {
        if (idx>=count) {
            throw new VoyagerIndexException();
        }

        tasks.remove(idx);
        println("You have purged item "+(idx)+" from my memory bank.");
        println("My memory bank is "+(--count)+"/100 full.");

        writeToFile();
    }

    /**
     * Processes user commands and performs corresponding actions.
     * This is the main command processing logic of Voyager.
     * @param command The command string entered by the user.
     * @param shouldPrint A boolean flag to control whether to print output to the console.
     * @return True if Voyager should continue processing commands, false if it should exit (e.g., on "bye" command).
     */
    private static boolean process(String command, boolean shouldPrint) {
        if (!shouldPrint) isSilent = true;
        boolean isMark = false;

        try {
            switch(command.split(" ")[0]) {
            case LIST:
                list();
                break;
            case BYE:
                sayBye();
                return false;
            case MARK:
                isMark = true;
            case UNMARK:
                mark(isMark, Integer.parseInt(command.split(" ")[1]));
                break;
            case TODO:
            case EVENT:
            case DEADLINE:
                create(command);
                break;
            case DELETE:
                delete(Integer.parseInt(command.split(" ")[1]));
                break;
            default:
                throw new VoyagerCommandException();
            }
        } catch (VoyagerIndexException e) {
            println("Error: Number not in list. Remember to write in decimal.");
        } catch (VoyagerCommandException e) {
            println("Error ...... I cannot process.... this... request ......");
        } catch (ArrayIndexOutOfBoundsException e) {
            println("Error: Missing parameters after keyword: "+command.split(" ")[0]);
        } catch (IOException e) {
            println("Error: I can't access my golden phonograph record...");
        }

        drawLine();
        isSilent = false;
        return true;
    }

    /**
     * Prints a string to the console if Voyager is not in silent mode.
     * @param s The string to print.
     */
    private static void println(String s) {
        if (!isSilent) System.out.println(s);
    }

    /**
     * Prints a string to the console without a newline if Voyager is not in silent mode.
     * @param s The string to print.
     */
    private static void print(String s) {
        if (!isSilent) System.out.print(s);
    }

    /**
     * Main entry point of the Voyager application.
     * Initializes Voyager, loads tasks from file, and starts the command processing loop.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        sayHi();
        initializeFromFile();

        String input;
        do {
            input = listen();
        } while (process(input, true));
    }
}
