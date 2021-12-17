/**
 * <h1>Main</h1>
 * Initialises and starts the ConnectFour game.
 * 
 * @author Sam Higgs
 * @version 0.1.0
 * @since 2021-12-17
 */

public class Main {

    public static void main(String[] args) {

        while (true) {
            // initialising the connect four game.
            ConnectFour game = new ConnectFour("PLAYER", TerminalDisplay.RED, "X", "COMPUTER", TerminalDisplay.YELLOW,
                    "O");

            // starting the connect four game.
            game.playGame();

            // test to play another game.
            if (!TerminalDisplay.playAnotherGame()) {
                break;
            }
        }
    }

}
