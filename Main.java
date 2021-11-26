public class Main {

    public static void main(String[] args) {

        // creating new board object
        Board boardGame = new Board();
        boardGame.printBoard();

        // adding pieces to the board
        boardGame.addPiece(1, "   X   ");
        boardGame.addPiece(1, "   X   ");
        boardGame.addPiece(1, "   X   ");
        boardGame.addPiece(1, "   X   ");
        boardGame.addPiece(1, "   X   ");
        boardGame.addPiece(1, "   X   ");
        boardGame.addPiece(1, "   X   ");
        boardGame.addPiece(7, "   O   ");
        boardGame.printBoard();
    }

}
