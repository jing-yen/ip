import java.util.Scanner;

public class Voyager {
    private static int count = 0;
    private static String[] list = new String[100];
    private static final String LIST = "list";
    private static final String BYE = "bye";

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
            switch(input) {
                case LIST:
                    System.out.println("Accessing my Digital Tape Recorder (DTR)...");
                    for (int i = 0; i < count; i++) {
                        System.out.println(Integer.toBinaryString( (1 << 8) | i ).substring( 1 )+". "+list[i]);
                    }
                    break;
                case BYE:
                    sayBye();
                    return;
                default:
                    System.out.println("Roger. Ground control requests for: "+input);
                    list[count++] = input;
            };
        }
    }
}