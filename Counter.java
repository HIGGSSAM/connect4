/**
 * <h1>Counter</h1>
 * class for all methods related to Counters.
 * 
 * <b>Methods:</b>
 * - setCounter(string, string)
 * - getColour()
 * - getSymbol()
 * 
 * @author Sam Higgs
 * @version 1.0.0
 * @since 2021-12-17
 */

public class Counter {

    // field - Initialise the colour and symbol of the counter.
    private String colour;
    private String symbol;

    /**
     * <h1>setCounter</h1>
     * creates a new instances of counter colour.
     * creates a new instances of counter symbol.
     * <b>Note:</b> constructor method.
     * 
     * @param colour
     * @param symbol
     */
    public void setCounter(String colour, String symbol) {
        this.colour = colour;
        this.symbol = symbol;
    }

    /**
     * <h1>getColour</h1>
     * returns the colour of a counter.
     * <b>Note:</b> accessor method.
     * 
     * @return string colour.
     */
    public String getColour() {
        return colour;
    }

    /**
     * <h1>getSymbol</h1>
     * returns the symbol of a counter.
     * <b>Note:</b> accessor method.
     * 
     * @return
     */
    public String getSymbol() {
        return symbol;
    }
}