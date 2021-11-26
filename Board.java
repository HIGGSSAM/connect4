
// NOTES
// -----
// [ ] adding pieces to the board.
// [X] printing board: 6 by 7 array.
// [ ] add comments.
// [ ] add logger to handle outputs.

public class Board {

    private static int rows = 6;
    private static int columns = 7;

    Piece[][] newBoard = new Piece[rows][columns];

    public void getBoard() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                newBoard[row][column] = null;
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < rows; row++) {
            System.out.print("|");
            for (int column = 0; column < columns; column++) {
                if (newBoard[row][column] == null) {
                    System.out.print("\t");
                } else {
                    System.out.print(newBoard[row][column].getColour());
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("    1\t    2\t    3\t    4\t    5\t    6\t    7");
        System.out.println("");
    }

    public boolean addPiece(int columnToAdd, String colour) {

        boolean addedpiece = false;

        // check if correct column is selected.
        if (columnToAdd >= 0 && columnToAdd < columns) {
            // check if column selected is full.
            if (newBoard[0][columnToAdd] == null) {
                for (int row = rows - 1; row >= 0; row--) {
                    // check if
                    if (newBoard[row][columnToAdd] == null) {
                        newBoard[row][columnToAdd] = new Piece();
                        newBoard[row][columnToAdd].setColour(colour);
                        addedpiece = true;
                        break;
                    }
                }
                return addedpiece;
            } else {
                int col = columnToAdd + 1;
                System.out.println("Error: column " + col + " is full.");
                return addedpiece;
            }
        } else {
            System.out.println("Error: select a column number between 1 and 7.");
            return addedpiece;
        }
    }
}