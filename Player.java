/**
 * <h1>Player</h1>
 * Class for all methods related to the player turn during a Connect Four game.
 * 
 * <b>Methods:</b>
 * - PlayerTurn(Board)
 * 
 * <b>Development Notes:</b> this class can be expanded to work with other input
 * methods for placing a counter in a selected board column.
 * 
 * @author Sam Higgs
 * @version 1.0.0
 * @since 2021-12-17
 */
public class Player {

    /**
     * <h1>playerTurn</h1>
     * prompts user for terminal input {@link TerminalDisplay#playerTurnInput()} and
     * validates input.
     * if {@link Board#checkColumnInput()} is true then return input column int.
     * <b>Note:</b> accessor method.
     * 
     * @param board
     * @return int playerSelection.
     */
    public int playerTurn(Board board) {

        while (true) {
            // displays input message.
            TerminalDisplay.displayMessage("Select a column value between 1 and " + board.getColumns() + ":");
            // getting user input.
            int playerSelection = TerminalDisplay.playerTurnInput();
            // check column input is okay and column is not full.
            if (board.checkColumnInput(playerSelection)) {
                return playerSelection;
            }
        }
    }
}