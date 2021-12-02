
// NOTES
// -----
// [X] adding counters to the board.
// [X] printing board: 6 by 7 array.
// [ ] add comments.
// [ ] add logger to handle outputs.
//
// modifies printBoard() lines 129 - 145.
// modifies placeCounter() Lines 147 - 175.
//  removes player if test from placeCounter. 
//  instead uses Counter class. 

public class Board {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    static Counter[][] newBoard = new Counter[ROWS][COLUMNS];

    // accessor - returns the number of rows on the board.
    public int getRows() {
        return ROWS;
    }

    // accessor - returns the number of columns on the board.
    public int getColumns() {
        return COLUMNS;
    }

    // accessor - returns the crrent state of the board.
    public Counter[][] getBoard() {
        return newBoard;
    }

    //
    public void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            System.out.print("|");
            for (int column = 0; column < COLUMNS; column++) {
                if (newBoard[row][column] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(newBoard[row][column].getColour());
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println(" 1 2 3 4 5 6 7");
        System.out.println();
    }

    public static boolean addPiece(int columnToAdd, String colour) {
        boolean addedpiece = false;
        // check if a correct column is selected.
        if (columnToAdd >= 0 && columnToAdd < COLUMNS) {
            // check if the column selected is full.
            if (newBoard[0][columnToAdd] == null) {
                // iterate through positions in column from bottom to top.
                for (int row = ROWS - 1; row >= 0; row--) {
                    // if possition in column is empty.
                    if (newBoard[row][columnToAdd] == null) {
                        newBoard[row][columnToAdd] = new Counter();
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