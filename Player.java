public class Player {

    // accessor - prompts user for input and returns the selection.
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