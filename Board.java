/**
 * NOTES
 * -----
 * modifies printBoard() lines 129 - 145.
 * modifies placeCounter() Lines 147 - 175.
 * removes player if test from placeCounter.
 * instead uses Counter class.
 */

public class Board {

    // field - Initialising the boards height.
    private static final int ROWS = 6;
    // field - Initialising the boards width.
    private static final int COLUMNS = 7;

    // field - creating a new board object.
    static Counter[][] newBoard = new Counter[ROWS][COLUMNS];
    // field - creating a new column head position object.
    static int[] columnHead = new int[COLUMNS];

    // accessor - returns the number of rows on the board.
    public int getRows() {
        return ROWS;
    }

    // accessor - returns the number of columns on the board.
    public int getColumns() {
        return COLUMNS;
    }

    // accessor - returns the current state of the board.
    public Counter[][] getBoard() {
        return newBoard;
    }

    // acccessor - returns the column head value for the inputted column.
    public int getColumnHead(int columnToAdd) {
        return columnHead[columnToAdd];
    }

    // accessor - checks if a correct column input has been selected.
    public static boolean checkColumnInput(int columnToAdd) {
        if (columnToAdd >= 0 && columnToAdd < COLUMNS) {
            return true;
        } else {
            System.out.println("Error: select a column number between 1 and 7.");
            return false;
        }
    }

    // accessor - checks if a column is full (true) or not (false).
    public static boolean checkColumnFull(int columnToAdd) {
        if (columnHead[columnToAdd] <= ROWS) {
            return false;
        } else {
            int col = columnToAdd + 1;
            System.out.println("Error: column " + col + " is full.");
            return true;
        }
    }

    // accessor - prints out the current state of the board.
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

    // accessor - adds a counter to the board and returns true, if it can't return
    // false.
    public static void addCounter(int columnToAdd, String colour, String symbol) {
        // columnHead[columnToadd] is the next position in the column.
        newBoard[columnHead[columnToAdd]][columnToAdd] = new Counter();
        newBoard[columnHead[columnToAdd]][columnToAdd].setCounter(colour, symbol);
        columnHead[columnToAdd]++;
    }
}