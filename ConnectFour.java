import java.util.Random;
import java.util.Scanner;

/**
 * NOTES
 * -----
 * 
 * 
 */

public class ConnectFour {

    // field - Initialising a board object.
    private Board board;
    // field - Initialising player turn boolean.
    private Boolean playerTurn = false;
    // field - Initialising player colour as a string.
    private String playerColour;
    // field - Initialising player symbol as a string.
    private String playerSymbol;
    // field - Initialising Computer colour as a string.
    private String compColour;
    // field - Initialising Computer symbol as a string.
    private String compSymbol;
    // field - Initialising random variable to radomise starting player.
    private Random random = new Random();

    // constructor - sets up the connect four game.
    public ConnectFour(String playerColour, String playerSymbol, String compColour, String compSymbol) {
        // adds a new player colour.
        this.playerColour = playerColour;
        // adds a new player symbol.
        this.playerSymbol = playerSymbol;
        // adds a new computer colour.
        this.compColour = compColour;
        // adds a new computer symbol.
        this.compSymbol = compSymbol;
        // creates a new board.
        this.board = new Board();
        // changes player turn.
        this.playerTurn = !this.playerTurn;
    }

    // - game logic
    public void playGame() {

        int turnCounter = 1;

        // randomise the starting player.
        // for each turn.
        while (random.nextBoolean()) {

            String colour;
            String symbol;

            // checking the turn.
            if (Boolean.TRUE.equals(playerTurn)) {
                colour = playerColour;
                symbol = playerSymbol;
                System.out.println("Current Turn : Player\n");

            } else {
                colour = compColour;
                symbol = compSymbol;
                System.out.println("Current Turn : Computer\n");
            }

            // print the board.
            board.printBoard();

            int columnSelection = playerTurn();

            // starting the players turn.
            Board.addCounter(columnSelection, colour, symbol);

            // checks if there is a winner.
            if (checkWinState(columnSelection, board.getColumnHead(columnSelection), colour, symbol)
                    || !checkWinState(columnSelection, board.getColumnHead(columnSelection), colour, symbol)
                            && checkDrawState(turnCounter)) {
                // winner or draw condition found - end of the game.
                break;
                // get winner colour.
            }
            playerTurn = !playerTurn;
        }
    }

    // accessor - prompts user for input and returns the selection.
    public int playerTurn() {

        while (true) {
            // getting user input.
            System.out.println("Select a column value between 1 and " + board.getColumns() + ":");
            Scanner input = new Scanner(System.in);
            int selection = input.nextInt() - 1;
            // input.close(); // causes an error at run time.
            // check column input is okay and column is not full.
            if (Board.checkColumnInput(selection + 1) && !Board.checkColumnFull(selection)) {
                return selection;
            }
        }
    }

    // accessor - returns boolean if it is players turn or not.
    public boolean checkTurn() {
        return playerTurn;
    }

    // accessor - returns boolean if the win condition is meant.
    public boolean checkWinState(int columnPosition, int rowPosition, String colour, String symbol) {

        Boolean winner = false;
        if (checkHorizontal(columnPosition, rowPosition, colour, symbol)) {
            winner = true;
        }
        if (checkVertical(columnPosition, rowPosition, colour, symbol)) {
            winner = true;
        }
        if (checkDiagonal(columnPosition, rowPosition, colour, symbol)) {
            winner = true;
        }
        return winner;
    }

    // accessor - returns boolean if the draw condition is meant.
    public boolean checkDrawState(int turnCounter) {

        Boolean draw = false;
        int boardSize = (board.getColumns() * board.getRows());
        // checks if turnCounter is equal to the size of the board.
        if (turnCounter >= boardSize) {
            draw = true;
        }
        return draw;
    }

    /*
     * accessor - checking for a sequence of the same counters at a column/ row
     * position and return the number found.
     */
    public int countCountersInLine(int columnPosition, int rowPosition, int stepColumn, int stepRow, String colour) {

        int countersInLine = 1;
        Counter[][] currentBoard = board.getBoard();

        while (true) {

            int column = columnPosition + stepColumn;
            int row = rowPosition + stepRow;

            // checks if currently outside the board and or counter colour is different.
            if ((column < 1 || column > board.getColumns())
                    || (row < 1 || row > board.getRows()) || !currentBoard[row][column].getColour().equals(colour)) {
                break;
            }
            countersInLine++;
        }
        return countersInLine;
    }

    // accessor - returns true if 4 consecutive diagonal counters are found.
    public boolean checkDiagonal(int columnPosition, int rowPosition, String colour, String symbol) {
        Boolean winCondition = false;

        // check counters sloping to the left i.e. / (bottom left -> top right).
        int leftSlopUpCounters = countCountersInLine(columnPosition, rowPosition, 1, 1, colour);
        int leftSlopDownCounters = countCountersInLine(columnPosition, rowPosition, -1, -1, colour);

        if (((leftSlopUpCounters - 1) + (leftSlopDownCounters - 1) + 1) >= 4) {
            winCondition = true;
        }

        // check counters sloping to the right i.e \ (top left -> bottom right).
        int rightSlopUpCounters = countCountersInLine(columnPosition, rowPosition, -1, 1, colour);
        int rightSlopDownCounters = countCountersInLine(columnPosition, rowPosition, 1, -1, colour);

        if (((rightSlopUpCounters - 1) + (rightSlopDownCounters - 1) + 1) >= 4) {
            winCondition = true;
        }
        return winCondition;
    }

    // accessor - returns true if 4 consecutive horizontal counters are found.
    public boolean checkHorizontal(int columnPosition, int rowPosition, String colour, String symbol) {
        Boolean winCondition = false;

        // check counters to the left.
        int leftCounters = countCountersInLine(columnPosition, rowPosition, -1, 0, colour);

        // check counters to the right.
        int rightCounters = countCountersInLine(columnPosition, rowPosition, -1, 0, colour);

        if (((leftCounters - 1) + (rightCounters - 1) + 1) >= 4) {
            winCondition = true;
        }
        return winCondition;
    }

    // accessor - returns true if 4 consecttive vertical counters are found.
    public boolean checkVertical(int columnPosition, int rowPosition, String colour, String symbol) {
        Boolean winCondition = false;

        if (countCountersInLine(columnPosition, rowPosition, 0, -1, colour) >= 4) {
            winCondition = true;
        }
        return winCondition;
    }
}
