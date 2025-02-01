import java.util.Scanner;

public class Voyager {

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

    public static void echo() {
        Scanner in = new Scanner(System.in);
        System.out.print("(You) Ground ctrl > ");
        String input = in.nextLine();
        if (input.equals("bye")) return;
        System.out.println(input);
        echo();
    }

    public static void main(String[] args) {
        sayHi();
        echo();
        sayBye();
    }
}