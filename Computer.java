import java.util.Random;

/**
 * <h1>Computer</h1>
 * Class for all methods related to the Connect Four computer Ai board.
 * 
 * <b>Methods:</b>
 * - ComputerTurn(Board)
 * 
 * <b>Development Notes:</b> As marks aren't awards for complexity of the
 * computer AI, a random number generator is used to select the inputted column
 * of the computer. For a more challenging AI, implement a Minimax Algorithm
 * with alpha-beta pruning or a machine learning algorithm. These would
 * be subclasses to the superclass Computer as they would inherit and then
 * modify the computerTurn method.
 * 
 * @author Sam Higgs
 * @version 1.0.0
 * @since 2021-12-17
 */

public class Computer {

    // ??
    private static Random random = new Random();

    /**
     * <h1>computerTurn</h1>
     * generates a random number between 1 and 7.
     * if {@link Board#checkColumnInput()} is true then return input column int.
     * <b>Note:</b> ... method.
     * 
     * @param board
     * @return int playerSelection.
     */
    public int computerTurn(Board board) {
        while (true) {
            int playerSelection = random.nextInt(6) + 1;
            // check column input is okay and column is not full.
            if (board.checkColumnInput(playerSelection)) {
                return playerSelection;
            }
        }
    }
}
