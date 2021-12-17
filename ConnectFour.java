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
    // field - Initailising player name as a string.
    private String playerName;
    // field - Initialising player colour as a string.
    private String playerColour;
    // field - Initialising player symbol as a string.
    private String playerSymbol;
    // field - Initailising computer name as a string.
    private String compName;
    // field - Initialising computer colour as a string.
    private String compColour;
    // field - Initialising computer symbol as a string.
    private String compSymbol;
    // field - Initialising random variable to radomise starting player.
    private Random random = new Random();
    // field - Initialising end of game state -1 playing, 0 = draw, 1 = winner.i
    private int endState;
    //
    private int currentRow;
    //
    private Player playerSelection;
    //
    private Computer compSelection;

    // constructor - sets up the connect four game.
    public ConnectFour(String playerName, String playerColour, String playerSymbol, String compName, String compColour,
            String compSymbol) {
        // adds a new player name.
        this.playerName = playerName;
        // adds a new player colour.
        this.playerColour = playerColour;
        // adds a new player symbol.
        this.playerSymbol = playerSymbol;
        // adds a new computer name.
        this.compName = compName;
        // adds a new computer colour.
        this.compColour = compColour;
        // adds a new computer symbol.
        this.compSymbol = compSymbol;
        // creates a new board.
        this.board = new Board();
        //
        this.currentRow = -1;
        // randomises the starting players turn.
        this.playerTurn = random.nextBoolean();
        //
        this.playerSelection = new Player();
        //
        this.compSelection = new Computer();
    }

    // - game logic.
    public void playGame() {

        int turnCounter = 1;

        // for each turn.
        while (true) {

            // clears the terminal screen.
            TerminalDisplay.displayMessage(TerminalDisplay.CLEAR_SCREEN);

            String colour;
            String symbol;
            String name;
            int columnSelection;

            // checking the turn.
            if (Boolean.TRUE.equals(playerTurn)) {
                colour = playerColour;
                symbol = playerSymbol;
                name = playerName;
            } else {
                colour = compColour;
                symbol = compSymbol;
                name = compName;
            }

            // prints as current turn message.
            TerminalDisplay.displayPlayerTurn(name, symbol, colour);

            // prints the current state of the board.
            TerminalDisplay.printBoard(board);

            // current player input selection.
            if (Boolean.TRUE.equals(playerTurn)) {
                columnSelection = playerSelection.playerTurn(board);
            } else {
                columnSelection = compSelection.computerTurnRandom(board);
            }

            // starting the players turn.
            currentRow = board.addCounter(columnSelection, colour, symbol);

            // checks if there is a winner or draw condition.
            if (checkWinner(columnSelection, currentRow, colour, symbol)
                    || drawCondition(turnCounter)) {

                // clears the terminal screen.
                TerminalDisplay.displayMessage(TerminalDisplay.CLEAR_SCREEN);

                // print current player turn.
                TerminalDisplay.displayPlayerTurn(name, symbol, colour);

                // print the board.
                TerminalDisplay.printBoard(board);

                // win or draw message.
                TerminalDisplay.endGameMessage(endState, name);

                break; // winner or draw condition found - end of the game.
            }
            // alternate the players turn once the current player have finished their turn.
            playerTurn = !playerTurn;
            turnCounter++;
        }
    }

    // accessor - returns boolean if it is players turn or not.
    public boolean checkTurn() {
        return playerTurn;
    }

    // accessor - returns boolean if the win condition is meant.
    public boolean checkWinner(int columnPosition, int rowPosition, String colour, String symbol) {

        Boolean winner = false;
        if (checkHorizontal(columnPosition, rowPosition, colour, symbol)
                || checkVertical(columnPosition, rowPosition, colour, symbol)
                || checkDiagonal(columnPosition, rowPosition, colour, symbol)) {
            winner = true;
            endState = 1;
        }
        return winner;
    }

    // accessor - returns boolean if the draw condition is meant.
    public boolean drawCondition(int turnCounter) {

        Boolean draw = false;
        int boardSize = (board.getColumns() * board.getRows());
        // checks if turnCounter is equal to the size of the board.
        if (turnCounter >= boardSize) {
            draw = true;
            endState = 0;
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
        int column = columnPosition;
        int row = rowPosition;

        while (true) {

            column += stepColumn;
            row += stepRow;

            /*
             * checks if currently outside the board and or no counter or counter colour is
             * different.
             */
            if ((column < 0 || column > board.getColumns() - 1)
                    || (row < 0 || row > board.getRows() - 1)
                    || currentBoard[row][column] == null
                    || !currentBoard[row][column].getColour().equals(colour)) {
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
        int rightCounters = countCountersInLine(columnPosition, rowPosition, 1, 0, colour);

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
