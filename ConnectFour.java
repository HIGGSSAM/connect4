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

            System.out.println("Select a column value between 1 and " + board.getColumns() + ":");
            // getting user input.
            Scanner input = new Scanner(System.in);
            int selection = input.nextInt() - 1;

            // adding counter to the board.
            Boolean successful = Board.addPiece(selection, colour);

            // if piece added end turn.
            if (Boolean.TRUE.equals(successful)) {
                // checks if there is a winner.
                if (checkWinState()) {
                    // winner found - end of the game.
                    gameRunning = !gameRunning;
                } else if (checkDrawState()) {
                    // draw condition found - end of the game.
                    gameRunning = !gameRunning;
                } else {
                    playerTurn = !playerTurn;
                }
            }
        }
    }

    // accessor - returns boolean if it is players turn or not.
    public boolean checkTurn() {
        return playerTurn;
    }

    // accessor - returns boolean if the win condition is meant.
    public boolean checkWinState() {

        Boolean winner = false;
        // add checks here.
        return winner;

    }

    // accessor - returns boolean if the draw condition is meant.
    public boolean checkDrawState() {

        Boolean draw = false;
        // add checks here.
        return draw;

    }

    // accessor - returns true if a diagonal of 4 is matched.
    public void checkDiagonal() {
        // to do.
    }

    // accessor - returns ture if a horizontal of 4 is matched.
    public void checkHorizontal() {

        // int rows = board.getRows();
        // int columns = board.getColumns();
        // Counter[][] newBoard = board.getBoard();

        // // int count = 0;
        // // iterate through each row in the board.
        // for (int row = 0; row < rows; row++) {
        // // to do.
        // }
    }

    // accessor - returns true if a vertical of 4 is matched.
    public void checkVertical() {

        // //
        // int rows = board.getRows();
        // int columns = board.getColumns();
        // Counter[][] newBoard = board.getBoard();

        // // iterating through each column in the board.
        // for (int column = 0; column < columns; column++) {
        // int count = 0;
        // // iterating through each row of the column.
        // for (int row = 0; row < rows; row++) {
        // // if the counters colour is the same as the players.
        // if (newBoard[row][column].getColour().equals(colour)) {
        // // add 1 to the count.
        // count++;
        // } else if (!newBoard[row][column].getColour().equals(colour)) {
        // count = 0;
        // }
        // }

        // // // check win clause.
        // // if (count >= 4) {
        // // return true;
        // // } else {
        // // return false;
        // // }
        // }
        // return false;
    }
}
