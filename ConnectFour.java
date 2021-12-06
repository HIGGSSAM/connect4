import java.util.Scanner;

public class ConnectFour {

    private Board board;
    private Boolean playerTurn = false;
    private String playerColour;
    private String playerSymbol;
    private String compColour;
    private String compSymbol;

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

        // randomise the starting player.

        // for each turn.
        while (true) {

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

            // starting the players turn.
            Board.addCounter((playerTurn(colour, symbol)), colour, symbol);

            // checks if there is a winner.
            if (checkWinState(colour) || checkDrawState()) {
                // winner or draw condition found - end of the game.
                break;
                // get winner colour.
            }
            playerTurn = !playerTurn;
        }
    }

    public int playerTurn(String colour, String symbol) {

        while (true) {
            // getting user input.
            System.out.println("Select a column value between 1 and " + board.getColumns() + ":");
            Scanner input = new Scanner(System.in);
            int selection = input.nextInt() - 1;
            // input.close(); // causes an error.
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

    // accessor - returns olean if the win condition is meant.
    public boolean checkWinState(String colour) {

        Boolean winner = false;
        if (checkHorizontal(colour)) {
            winner = true;
        }
        if (checkVertical(colour)) {
            winner = true;
        }
        if (checkDiagonal()) {
            winner = true;
        }
        return winner;
    }

    // accessor - returns boolean if the draw condition is meant.
    public boolean checkDrawState() {

        Boolean draw = false;
        // add check if the board is full.

        return draw;

    }

    // accessor - returns true if a diagonal of 4 is matched.
    public boolean checkDiagonal() {
        // to do.
        return false;
    }

    // accessor - returns true if 4 concecutive horizontal matches are found.
    public boolean checkHorizontal(String colour) {

        int rows = board.getRows();
        int columns = board.getColumns();
        Counter[][] currentBoard = board.getBoard();

        // iterate through each row on the board.
        for (int row = 0; row < rows; row++) {
            int winningCount = 4;
            // iterate through each column on the board.
            for (int column = 0; column < columns; column++) {
                // if the counters colour is the same as the players.
                // ISSUE ---> null pointer exception
                if (currentBoard[row][column].getColour().equals(colour)) {
                    // remove 1 from the winningCount.
                    winningCount--;
                    // if the winningCount is 0 then win condition met.
                    if (winningCount == 0) {
                        return true;
                    }
                } else {
                    winningCount = 4;
                }
            }
        }
        return false;
    }

    // accessor - returns true if 4 concecutive vertical matches are found.
    public boolean checkVertical(String colour) {

        int rows = board.getRows();
        int columns = board.getColumns();
        Counter[][] currentBoard = board.getBoard();

        // simplify.
        // parse in position

        // iterating through each column in the board.
        for (int column = 0; column < columns; column++) {
            int winningCount = 4; // static variable??
            // iterating through each row of the column.
            for (int row = 0; row < rows; row++) {
                // if the counters colour is the same as the players.
                if (currentBoard[row][column].getColour().equals(colour)) {
                    // remove 1 from the winningCount.
                    winningCount--;
                    // if the winningCount is 0 then win condition met.
                    if (winningCount == 0) {
                        return true;
                    }
                } else {
                    winningCount = 4;
                }
            }
        }
        return false;
    }
}
