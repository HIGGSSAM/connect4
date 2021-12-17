import java.util.Scanner;

/**
 * NOTES
 * -----
 * TerminalDisplay class for everything that is inputted and outputted on the
 * terminal display.
 * 
 * NOTE this class has been unpacked from other classes so further development
 * of a GUI could be possible.
 * 
 * 
 */

public class TerminalDisplay {

    private TerminalDisplay() {
        throw new IllegalStateException("Terminal Uitility class");
    }

    //
    private static Scanner input = new Scanner(System.in);

    // cursor control and screen actions
    public static final String CLEAR_SCREEN = "\u001B[H\u001B[2J"; // "\033[H\033[2J"
    public static final String PREVIOUS_LINE = "\u001B[F"; // "\033[F"
    public static final String CLEAR_LINE = "";
    public static final String CLEAR_END_LINE = "";

    // reset terminal counter colour.
    public static final String RESET = "\033[0m"; // Text Reset

    // terminal counter colours.
    public static final String BLACK = "\033[0;30m"; // BLACK
    public static final String RED = "\033[0;31m"; // RED
    public static final String GREEN = "\033[0;32m"; // GREEN
    public static final String YELLOW = "\033[0;33m"; // YELLOW
    public static final String BLUE = "\033[0;34m"; // BLUE
    public static final String PURPLE = "\033[0;35m"; // PURPLE
    public static final String CYAN = "\033[0;36m"; // CYAN
    public static final String WHITE = "\033[0;37m"; // WHITE

    // accessor - prints out the current state of the board.
    public static void printBoard(Board currentBoard) {
        for (int row = currentBoard.getRows() - 1; row >= 0; row--) {
            System.out.print("|");
            for (int column = 0; column < currentBoard.getColumns(); column++) {
                if (currentBoard.getCounter(row, column) == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(String.format("%s%s%s", currentBoard.getBoard()[row][column].getColour(),
                            currentBoard.getBoard()[row][column].getSymbol(), TerminalDisplay.RESET));
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println(" 1 2 3 4 5 6 7");
        System.out.println();
    }

    public static void displayPlayerTurn(String name, String symbol, String colour) {
        System.out.println(
                String.format("Current Turn : %s as [%s%s%s]%n", name, colour, symbol,
                        TerminalDisplay.RESET));
    }

    public static void displayMessage(String displayMessage) {
        System.out.print(displayMessage);
    }

    public static void errorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void endGameMessage(int endState, String name) {
        if (endState == 1) {
            // print winner messge
            System.out.println(String.format("%s is the winner!%n", name));
        } else {
            // print draw message
            System.out.println("The game ended as a draw.\n");
        }
    }

    public static int playerTurnInput() {
        return input.nextInt() - 1;
    }

    public static boolean playAnotherGame() {
        System.out.print("Enter Y/y if you want to play another game: ");
        String selection = input.next().toUpperCase();
        return ("Y".equals(selection));
    }
}
