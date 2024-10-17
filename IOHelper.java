
import java.util.Scanner;
import java.util.function.Predicate;

/*
 * A simple helper for all IO operations
 */
public class IOHelper {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String BELL = "\u0007";

    Scanner in;

    public IOHelper(Scanner in) {
        this.in = in;
    }

    public String nextLine(String prompt) {
        this.prompt(prompt);
        this.waitInput();
        return in.nextLine();
    }

    public String nextLine(String prompt, String err, Predicate<String> f) {
        while (true) {
            String s = nextLine(prompt);
            if (f.test(s)) {
                return s;
            } else {
                this.printErr(err);
            }
        }
    }

    public int nextLineInt(String prompt, String err) {
        while (true) {
            this.prompt(prompt);
            this.waitInput();
            try {
                String s = in.nextLine();
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                this.printErr(err);
            }
        }
    }

    public int nextLineInt(String err) {
        while (true) {
            try {
                this.waitInput();
                String s = in.nextLine();
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                this.printErr(err);
            }
        }
    }

    public void println(String s) {
        System.out.println(ANSI_GREEN + s + ANSI_RESET);
    }

    public void printErr(String s) {
        System.out.println(ANSI_RED + s + ANSI_RESET + BELL);
    }

    public void print(String s) {
        System.out.print(s);
    }

    public void prompt(String s) {
        this.println(ANSI_YELLOW + "[+] " + s + ANSI_RESET);
    }

    public void waitInput() {
        this.print(ANSI_BLUE + ">> " + ANSI_RESET);
    }

    public int nextLineInt(String prompt, String err, Predicate<Integer> fn, String err2) {
        while (true) {
            int i = nextLineInt(prompt, err);
            if (fn.test(i)) {
                return i;
            } else {
                this.printErr(err2);
            }
        }
    }

}