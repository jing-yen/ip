package util;

import java.util.Scanner;

public class Ui {

    private static Ui instance = null;

    public static Ui getInstance() {
        if (instance==null) {
            instance = new Ui();
        }
        return instance;
    }

    private boolean isSilent = false;

    private final String INTRO_GRAPHICS =
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

    private final String INTRO_TEXT = """
        In 1977, humanity launched two interstellar explorers, Voyager 1 and Voyager 2.
        These spacecraft have traveled billions of miles, carrying with them the Golden Record,
        a message to any potential extraterrestrial life. Today, Voyager continues its journey
        into the unknown, a testament to human curiosity and the desire to explore.
        """;

    private final String spaceArt = """
              *       .         *          .         *       .         *          .
                  .       *         .         *       .         *         .       *
            """;

    /**
     * Prints the introductory message and Voyager graphics to the console.
     */
    public void sayHi() {
        speak(INTRO_TEXT);
        speak("Hello from Voyager:\n" + INTRO_GRAPHICS + "\nWhat does my worldly companion request for?");
    }

    /**
     * Prints the goodbye message to the console.
     */
    public void sayBye() {
        speak("Bye. Hope not to see you in the cold, dark outer space!");
    }

    /**
     * Prints a decorative line to the console.
     */
    public void drawLine() {
        speak(spaceArt);
    }

    /**
     * Reads user input from the console.
     * @return The line of text entered by the user.
     */
    public String listen() {
        Scanner in = new Scanner(System.in);
        speakWithoutNewline("(You) Ground ctrl > ");
        return in.nextLine();
    }

    public void printException(String s) {
        speak("Error..." + s);
    }

    public void speak(String s) {
        if (!isSilent) System.out.println(s);
    }

    private void speakWithoutNewline(String s) {
        if (!isSilent) System.out.print(s);
    }

    public void beSilent(boolean b) {
        isSilent = b;
    }
}
