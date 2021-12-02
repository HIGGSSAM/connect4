import java.util.Scanner;

public class ConnectFour {

    private Board board;
    private Boolean playerTurn = false;
    private String playerColour;
    private String compColour;

    // constructor -
    public ConnectFour(String playerColour, String compColour) {
        // adds a new player.
        this.playerColour = playerColour;
        // adds a new computer.
        this.compColour = compColour;
        // creates a new board.
        this.board = new Board();
        // changes player turn.
        this.playerTurn = !this.playerTurn;
    }

    // - game logic
    public void playGame() {

        Boolean gameRunning = true;

        // for each turn.
        while (Boolean.TRUE.equals(gameRunning)) {

            String colour;

            // checking the turn.
            if (Boolean.TRUE.equals(playerTurn)) {
                colour = playerColour;
                System.out.println("Current Turn : Player\n");

            } else {
                colour = compColour;
                System.out.println("Current Turn : Computer\n");
            }

            // print the board.
            board.printBoard();

            // getting user input.
            System.out.println("Select a column value between 1 and " + board.getColumns() + ":");
            Scanner input = new Scanner(System.in);
            int selection = input.nextInt() - 1;

            // adding counter to the board.
            Boolean successful = Board.addPiece(selection, colour);

            // if piece added end turn.
            if (Boolean.TRUE.equals(successful)) {
                // checks if there is a winner.
                if (checkWinState(colour)) {
                    // winner found - end of the game.
                    gameRunning = !gameRunning;
                    // get winner colour.
                } else if (checkDrawState()) {
                    // draw condition found - end of the game.
                    gameRunning = !gameRunning;
                }

                playerTurn = !playerTurn;

            }
        }
    }

    // accessor - returns boolean if it is players turn or not.
    public boolean checkTurn() {
        return playerTurn;
    }

    // accessor - returns boolean if the win condition is meant.
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

        // iterating through each column in the board.
        for (int column = 0; column < columns; column++) {
            int winningCount = 4;
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
