import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Voyager {
    private static int count = 0;
    private static final Task[] tasks = new Task[100];
    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    
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

    public static void sayHi() {
        System.out.println(INTRO_TEXT);
        System.out.println("Hello from Voyager:\n" + INTRO_GRAPHICS + "\nWhat does my worldly companion request for?");
    }

    public static void sayBye() {
        System.out.println("Bye. Hope not to see you in the cold, dark outer space!");
    }

    public static void drawLine() {
        System.out.println(spaceArt);
    }

    public static String listen() {
        Scanner in = new Scanner(System.in);
        System.out.print("(You) Ground ctrl > ");
        return in.nextLine();
    }

    public static void list() {
        System.out.println("Accessing my Digital Tape Recorder (DTR)...");
        if (count==0) System.out.println("My memory is empty.");
        for (int i = 0; i < count; i++) {
            System.out.println(
                    Integer.toBinaryString( (1 << 8) | i ).substring( 1 )
                            +". "+tasks[i].toString());
        }
    }

    public static void mark(boolean isMark, int idx) throws VoyagerIndexException, IOException {
        if (idx>=count) {
            throw new VoyagerIndexException();
        }

        String idxInBinary = Integer.toBinaryString((1 << 8) | idx).substring(1);
        if (isMark) {
            tasks[idx].setIsDone(true);
            System.out.println("Beep-boop. Marked task "+ idxInBinary +" as done.");
        } else {
            tasks[idx].setIsDone(false);
            System.out.println("Beeeeeeep. Unmarked task "+ idxInBinary +".");
        }

        System.out.println("  "+ idxInBinary +". "+tasks[count-1].toString());

        writeToFile();
    }

    public static void create(String input) throws IOException {
        switch (input.split(" ")[0]) {
        case TODO:
            String desc = input.split(" ", 2)[1].trim();
            tasks[count++] = new Todo(desc);
            System.out.println("Roger. Ground control requests for Todo: ");
            System.out.println("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+tasks[count-1].toString());
            System.out.println("My memory bank is "+(count)+"/100 full.");
            break;
        case DEADLINE:
            desc = input.split(" ", 2)[1].split("/by")[0].trim();
            String date = input.split("/by")[1].trim();
            tasks[count++] = new Deadline(desc, date);
            System.out.println("Roger. Ground control requests for Deadline: ");
            System.out.println("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+tasks[count-1].toString());
            System.out.println("My memory bank is "+(count)+"/100 full.");
            break;
        case EVENT:
            desc = input.split(" ", 2)[1].split("/from")[0].trim();
            String from = input.split("/from", 2)[1].split("/to")[0].trim();
            String to = input.split("/to ")[1];
            tasks[count++] = new Event(desc, from, to);
            System.out.println("Roger. Ground control requests for Event: ");
            System.out.println("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+tasks[count-1].toString());
            System.out.println("My memory bank is "+(count)+"/100 full.");
            break;
        }

        writeToFile();
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("my-golden-disk.txt");
        String text = "";
        for (Task task: tasks) {
            if (task !=null) text += (task.isDone?'/':'X') + task.toCommand() + System.lineSeparator();
        }
        fw.write(text);
        fw.close();
    }

    public static void initializeFromFile() {
        File f = new File("my-golden-disk.txt");
        if (!f.exists()) return;

        try {
            Scanner s = null;
            s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.isEmpty()) continue;
                process(line.substring(1), false);
                if (line.charAt(0)=='/') mark(true, count-1);
            }
        } catch (Exception e) {
            System.out.println("Error: I can't access my golden phonograph record...");
        }
    }

    private static boolean process(String command, boolean shouldPrint) {
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
            default:
                throw new VoyagerCommandException();
            }
        } catch (VoyagerIndexException e) {
            System.out.println("Error: Number not in list. Write in decimal.");
        } catch (VoyagerCommandException e) {
            System.out.println("Error ...... I cannot process.... this... request ......");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Missing parameters after keyword: "+command.split(" ")[0]);
        } catch (IOException e) {
            System.out.println("Error: I can't access my golden phonograph record...");
        }

        drawLine();
        return true;
    }

    public static void main(String[] args) {
        sayHi();
        initializeFromFile();

        String input;
        do {
            input = listen();
        } while (process(input, true));
    }
}