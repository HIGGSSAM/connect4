/**
 * <h1>Board</h1>
 * Class for all methods related to the Connect Four board.
 * 
 * <b>Methods:</b>
 * - Board()
 * - getRows()
 * - getColumns()
 * - getBoard()
 * - getCounter(int, int)
 * - checkColumnHead(int)
 * - checkColumnInput(int)
 * - addCounter(int, string, string)
 * 
 * @author Sam Higgs
 * @version 1.0.0
 * @since 2021-12-17
 */

public class Board {

    // field - Initialising the board height constant.
    private static final int ROWS = 6;
    // field - Initialising the board width constant.
    private static final int COLUMNS = 7;
    // field - Initailisng a new board object.
    private Counter[][] newBoard;
    // field - Initailising a new column head position object.
    private int[] columnHead;

    /**
     * <h1>Board</h1>
     * creates a new instances of Board at the start of each game.
     * creates a new instances of column head at the start of each game.
     * <b>Note:</b> constructor method.
     */
    public Board() {
        this.newBoard = new Counter[ROWS][COLUMNS];
        this.columnHead = new int[COLUMNS];
    }

    /**
     * <h1>getROWS</h1>
     * returns returns the number of rows on the board.
     * <b>Note:</b> accessor method.
     * <b>Note:</b> board rows are indexed 0-5, and but ROWS = 6.
     * 
     * @return int ROWS.
     */
    public int getRows() {
        return ROWS;
    }

    /**
     * <h1>getColumn</h1>
     * returns the number of columns in the board.
     * <b>Note:</b> accessor method.
     * <b>Note:</b> board columns are indexed 0-6, and but COLUMNS = 7.
     * 
     * @return int COLUMNS.
     */
    public int getColumns() {
        return COLUMNS;
    }

    /**
     * <h1>getBoard</h1>
     * returns the current state of the board.
     * <b>Note:</b> accessor method.
     * 
     * @return Counter newBoard.
     */
    public Counter[][] getBoard() {
        return newBoard;
    }

    /**
     * <h1>getCounter</h1>
     * returns the counter from the inputted position on the board.
     * <b>Note:</b> accessor method.
     * 
     * @param rowPos the row position.
     * @param colPos the column position.
     * @return Counter newBoard[rowPos][colPos].
     */
    public Counter getCounter(int rowPos, int colPos) {
        return newBoard[rowPos][colPos];
    }

    /**
     * <h1>getColumnHead</h1>
     * returns the column head value for the inputted column.
     * <b>Note:</b> accessor method.
     * 
     * @param columnToAdd is the column number when counter is added to.
     * @return int columnHead[columnToAdd].
     */
    public int getColumnHead(int columnToAdd) {
        return columnHead[columnToAdd];
    }

    /**
     * <h1>checkColumnInput</h1>
     * checks if counter can be added to the selected column.
     * <b>Note:</b> accessor method.
     * 
     * @param columnToAdd is the column number when counter is added to.
     * @return boolean.
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
     * <b>Note:</b> mutator method.
     * 
     * @param columnToAdd is the column number when counter is added to.
     * @param colour      is the players counter colour.
     * @param symbol      is the players counter symbol.
     * @return int columnHead[columnToAdd] - 1.
     */
    public int addCounter(int columnToAdd, String colour, String symbol) {
        // columnHead[columnToadd] is the next position in the column.
        newBoard[columnHead[columnToAdd]][columnToAdd] = new Counter();
        newBoard[columnHead[columnToAdd]][columnToAdd].setCounter(colour, symbol);
        columnHead[columnToAdd]++;
        return columnHead[columnToAdd] - 1;
    }
}