import java.util.Random;

/**
 * <h1>ConnectFour</h1>
 * Class for all methods related to playing a game of Connect Four.
 * 
 * <b>Methods:</b>
 * - ConnectFour(String, String, String, String, String, String)
 * - playGame()
 * - checkTurn()
 * - checkWinner(int, int, String, String)
 * - drawCondition(int)
 * - countCountersInLine(int, int, int, int, String)
 * - checkDiagonal(int, int, String, String)
 * - checkHorizontal(int, int, String, String)
 * - checkVertical(int, int, String, String)
 * 
 * @author Sam Higgs
 * @version 1.0.0
 * @since 2021-12-17
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
    // field - Initialising end of game state -1 playing, 0 = draw, 1 = winner.
    private int endState;
    // field - Initialising currentRow as an int.
    private int currentRow;
    // field - Initialising playerSelection as Player object.
    private Player playerSelection;
    // field - Initialising compSelection as Computer object.
    private Computer compSelection;

    /**
     * <h1>ConnectFour</h1>
     * creates a new instances of player name, colour and symbol at the start of
     * each game.
     * creates a new instances of computer name, colour and symbol at the start of
     * each game.
     * creates a new instances of the board at the start of each game.
     * creates a new instances of currentRow at the start of each game set to -1.
     * creates a new instances of playerTurn at the start of each game.
     * creates a new instances of playerSelection at the start of each game.
     * creates a new instances of computerSelection at the start of each game.
     * 
     * <b>Note:</b> constructor method.
     * 
     * @param playerName
     * @param playerColour
     * @param playerSymbol
     * @param compName
     * @param compColour
     * @param compSymbol
     */
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

    /**
     * <h1>playGame</h1>
     * contains all the game logic for Connect Four.
     * <b>Note:</b> ... method.
     */
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
                columnSelection = compSelection.computerTurn(board);
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

    /**
     * <h1>checkTurn</h1>
     * returns boolean as to whether it is the player's turn or not.
     * <b>Note:</b> accessor method.
     * 
     * @return boolean playerTurn.
     */
    public boolean checkTurn() {
        return playerTurn;
    }

    /**
     * <h1>checkWinner</h1>
     * returns boolean as to whether the win condition is meant.
     * <b>Note:</b> accessor method.
     * 
     * @param columnPosition
     * @param rowPosition
     * @param colour
     * @param symbol
     * @return boolean winner.
     */
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

    /**
     * <h1>drawCondition</h1>
     * returns boolean as to whether the draw condition is meant.
     * <b>Note:</b> accessor method.
     * 
     * @param turnCounter
     * @return boolean draw.
     */
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

    /**
     * <h1>countCountersInLine</h1>
     * checking for a sequence of the same counters at a column/ row position and
     * return the number found.
     * <b>Note:</b> accessor method.
     * 
     * @param columnPosition
     * @param rowPosition
     * @param stepColumn
     * @param stepRow
     * @param colour
     * @returni int countersInLine
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

    /**
     * <h1>checkDiagonal</h1>
     * returns true if 4 consecutive diagonal counters are found.
     * if any instance of {@link ConnectFour#countCountersInLine()} returns >=4
     * <b>Note:</b> accessor method.
     * 
     * @param columnPosition
     * @param rowPosition
     * @param colour
     * @param symbol
     * @return boolean winCondition.
     */
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

    /**
     * <h1>checkHorizontal</h1>
     * returns true if 4 consecutive horizontal counters are found.
     * if any instance of {@link ConnectFour#countCountersInLine()} returns >=4
     * <b>Note:</b> accessor method.
     * 
     * @param columnPosition
     * @param rowPosition
     * @param colour
     * @param symbol
     * @return boolean winCondition.
     */
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

    /**
     * <h1>checkHorizontal</h1>
     * returns true if 4 consecutive vertical counters are found.
     * if any instance of {@link ConnectFour#countCountersInLine()} returns >=4
     * <b>Note:</b> accessor method.
     * 
     * @param columnPosition
     * @param rowPosition
     * @param colour
     * @param symbol
     * @return boolean winCondition.
     */
    public boolean checkVertical(int columnPosition, int rowPosition, String colour, String symbol) {
        Boolean winCondition = false;

        if (countCountersInLine(columnPosition, rowPosition, 0, -1, colour) >= 4) {
            winCondition = true;
        }
        return winCondition;
    }
}
