import java.util.Scanner;

public class Voyager {
    private static int count = 0;
    private static Task[] tasks = new Task[100];
    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    public static void sayHi() {
        String backDrop =
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

        String intro = """
                In 1977, humanity launched two interstellar explorers, Voyager 1 and Voyager 2.
                These spacecraft have traveled billions of miles, carrying with them the Golden Record,
                a message to any potential extraterrestrial life. Today, Voyager continues its journey
                into the unknown, a testament to human curiosity and the desire to explore.
                """;

        System.out.println(intro);
        System.out.println("Hello from Voyager:\n" + backDrop + "\nWhat does my worldly companion request for?");
    }

    public static void sayBye() {
        System.out.println("Bye. Hope not to see you in the cold, dark outer space!");
    }

    public static void separator() {
        String spaceArt =
                "  *       .         *          .         *       .         *          .\n" +
                        "      .       *         .         *       .         *         .       *\n";
        System.out.println(spaceArt);
    }

    public static String listen() {
        Scanner in = new Scanner(System.in);
        System.out.print("(You) Ground ctrl > ");
        return in.nextLine();
    }

    public static void main(String[] args) {
        sayHi();
        while (true) {
            String input = listen();
            boolean mark = false;
            switch(input.split(" ")[0]) {
                case LIST:
                    System.out.println("Accessing my Digital Tape Recorder (DTR)...");
                    if (count==0) System.out.println("My memory is empty.");
                    for (int i = 0; i < count; i++) {
                        System.out.println(
                                Integer.toBinaryString( (1 << 8) | i ).substring( 1 )
                                        +". "+tasks[i].toString());
                    }
                    break;
                case BYE:
                    sayBye();
                    return;
                case MARK:
                    mark = true;
                case UNMARK:
                    int idx = Integer.parseInt(input.split(" ")[1]);
                    if (idx>=count) System.out.println("Operation invalid. Number not in list. Write in decimal.");
                    else if (mark) {
                        tasks[idx].setIsDone(true);
                        System.out.println("Beep-boop. Marked task "+Integer.toBinaryString((1<<8) | idx).substring(1)+" as done.");
                    } else {
                        tasks[idx].setIsDone(false);
                        System.out.println("Beeeeeeep. Unmarked task "+Integer.toBinaryString((1<<8) | idx).substring(1)+".");
                    }
                    break;
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
                    desc = input.split(" ", 2)[1].split("/by")[0].trim();
                    String from = input.split("/from", 2)[1].split("/to")[0].trim();
                    String to = input.split("/to")[1];
                    tasks[count++] = new Event(desc, from, to);
                    System.out.println("Roger. Ground control requests for Event: "+desc+" from "+from+" to "+to);
                    System.out.println("  "+Integer.toBinaryString((1<<8) | count-1).substring(1)+". "+tasks[count-1].toString());
                    System.out.println("My memory bank is "+(count)+"/100 full.");
                    break;
                default:
                    System.out.println("...... I cannot process.... this... request ......");
            };
            separator();
        }
    }
}