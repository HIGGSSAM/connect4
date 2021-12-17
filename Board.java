/**
 * <h1>Board</h1>
 * Class for all methods related to the connect four Board.
 *
 * @author Sam Higgs
 * @version 0.1.0
 * @since 2021-12-17
 */

public class Board {

    // field - Initialising the boards height.
    private static final int ROWS = 6;
    // field - Initialising the boards width.
    private static final int COLUMNS = 7;
    // field - creating a new board object.
    private Counter[][] newBoard;
    // field - creating a new column head position object.
    private int[] columnHead;

    public Board() {
        this.newBoard = new Counter[ROWS][COLUMNS];
        this.columnHead = new int[COLUMNS];
    }

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

    // accessor - returns Counter object of the row and column position.
    public Counter getCounter(int rowPos, int colPos) {
        return newBoard[rowPos][colPos];
    }

    /**
     * <h1>getColumnHead</h1>
     * returns the column head value for the inputted column.
     * <b>Note:</b> accessor method.
     * 
     * @param columnToAdd is the column number when counter is added to.
     * @return int column head value.
     */
    public int getColumnHead(int columnToAdd) {
        return columnHead[columnToAdd];
    }

    /**
     * <h1>checkColumnInput</h1>
     * checks if counter can be added to the selected column.
     * <b>Note:</b> static accessor method.
     * 
     * @param columnToAdd is the column number when counter is added to.
     * @return boolean if the counter can be added of not.
     */
    public boolean checkColumnInput(int columnToAdd) {
        if (columnToAdd >= 0 && columnToAdd <= COLUMNS - 1) {
            if (columnHead[columnToAdd] <= ROWS - 1) {
                return true;
            }
            TerminalDisplay.errorMessage("Error: column " + (columnToAdd + 1) + " is full.");
        } else {
            TerminalDisplay.errorMessage("Error: select a column number between 1 and 7.");
        }
        return false;
    }

    /**
     * <h1>addCounter</h1>
     * adds a counter to the board at the inputted column.
     * <b>Note:</b> accessor method.
     * 
     * @param columnToAdd is the column number when counter is added to.
     * @param colour      is the players counter colour.
     * @param symbol      is the players counter symbol.
     * @return int the postion of the counter on the board.
     */
    public int addCounter(int columnToAdd, String colour, String symbol) {
        // columnHead[columnToadd] is the next position in the column.
        newBoard[columnHead[columnToAdd]][columnToAdd] = new Counter();
        newBoard[columnHead[columnToAdd]][columnToAdd].setCounter(colour, symbol);
        columnHead[columnToAdd]++;
        return columnHead[columnToAdd] - 1;
    }
}