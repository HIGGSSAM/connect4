import java.util.Scanner;

/**
 * <h1>TerminalDisplay</h1>
 * TerminalDisplay class for everything that is inputted and outputted on the
 * terminal display.
 * <b>Note:</b> this class has been unpacked from other classes so further
 * development of a GUI could be possible.
 * 
 * <b>Methods:</b>
 * - printBoard(Board)
 * - displayPlayerTurn(String, String, String)
 * - displayMessage(String)
 * - errorMessage(String)
 * - endGameMessage(int, String)
 * - playerTurnInput()
 * - playerAnotherGame()
 * 
 * <b>Development Notes:</b>
 * As marks aren't awards for inplementation of a GUI, the termial display was
 * used to visualise the game. If development of a GUI occured then a display
 * super class would be implemented based on this class and would have to
 * subclasses; the terminal display and the GUI both modifying the display and
 * input methods.
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

    /**
     * <h1>printBoard</h1>
     * prints out the current state of the board..
     * <b>Note:</b> accessor method.
     * 
     * @param currentBoard
     */
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

    /**
     * <h1>displayPlayerTurn</h1>
     * prints out a current turn message to the terminal.
     * <b>Note:</b> accessor method.
     * 
     * @param name
     * @param symbol
     * @param colour
     */
    public static void displayPlayerTurn(String name, String symbol, String colour) {
        System.out.println(
                String.format("Current Turn : %s as [%s%s%s]%n", name, colour, symbol,
                        TerminalDisplay.RESET));
    }

    /**
     * <h1>displayMessage</h1>
     * prints out inputted string to the terminal.
     * <b>Note:</b> accessor method.
     * 
     * @param displayMessage
     */
    public static void displayMessage(String displayMessage) {
        System.out.print(displayMessage);
    }

    /**
     * <h1>errorMessage</h1>
     * prints out an error message to the terminal.
     * <b>Note:</b> accessor method.
     * 
     * @param errorMessage
     */
    public static void errorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * <h1>endGameMessage</h1>
     * prints out either a win or draw message to the terminal depending on the
     * endState.
     * <b>Note:</b> accessor method.
     * 
     * @param endState
     * @param name
     */
    public static void endGameMessage(int endState, String name) {
        if (endState == 1) {
            // print winner messge
            System.out.println(String.format("%s is the winner!%n", name));
        } else {
            // print draw message
            System.out.println("The game ended as a draw.\n");
        }
    }

    /**
     * <h1>playerTurnInput</h1>
     * returns an int value from terminal user input.
     * <b>Note:</b> accessor method.
     * 
     * @return int user terminal input.
     */
    public static int playerTurnInput() {
        Character selection;
        while (true) {
            selection = getInput().charAt(0);
            if (Character.isDigit(selection)) {
                return Character.getNumericValue(selection) - 1;
            }
            // not a digit so return -1 to signal error
            return -1;
        }
    }

    /**
     * <h1>playAnotherGames</h1>
     * returns boolean if input string from terminal user input matches Y.
     * <b>Note:</b> accessor method.
     * 
     * @return boolean.
     */
    public static boolean playAnotherGame() {
        System.out.print("Enter Y/y if you want to play another game: ");
        Character selection = getInput().toUpperCase().charAt(0);
        return ('Y' == selection);
    }

    /**
     * <h1>getInput</h1>
     * returns string from terminal user input.
     * <b>Note:</b> accessor method.
     * 
     * @exception
     * @return string user input.
     */
    private static String getInput() {
        while (true) {
            try {
                return input.next();
            } catch (java.util.NoSuchElementException e) {
                // do nothing try input again
            }
        }
    }
}
