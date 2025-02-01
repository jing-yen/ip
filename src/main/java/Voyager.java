import java.util.Scanner;

public class Voyager {
    private static int count = 0;
    private static Task[] tasks = new Task[100];
    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";

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
                    for (int i = 0; i < count; i++) {
                        System.out.println(
                                Integer.toBinaryString( (1 << 8) | i ).substring( 1 )
                                        +". ["+tasks[i].getStatusIcon()+"] "
                                        +tasks[i].description);
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
                default:
                    System.out.println("Roger. Ground control requests for: "+input);
                    tasks[count++] = new Task(input);
            };
        }
    }
}