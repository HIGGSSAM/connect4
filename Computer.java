import java.util.Random;

/**
 * 
 * As marks aren't awards for complexity of the computer AI, a random number
 * generator is used.
 * 
 * For a more challenging AI, implement a Minimax Algorithm with alpha-beta
 * pruning.
 */

public class Computer {

    private static Random random = new Random();

    public int computerTurnRandom(Board board) {
        while (true) {
            int playerSelection = random.nextInt(6) + 1;
            // check column input is okay and column is not full.
            if (board.checkColumnInput(playerSelection)) {
                return playerSelection;
            }
        }
    }
}
