/**
 * NOTES
 * -----
 * 
 * 
 */

public class Counter {

    // field - Initialise the colour and symbol of the counter.
    private String colour;
    private String symbol;

    // constructor - setting the colour of a counter.
    public void setCounter(String colour, String symbol) {
        this.colour = colour;
        this.symbol = symbol;
    }

    // accessor - getting the colour of a counter.
    public String getColour() {
        return colour;
    }

    // accessor - getting the symbol of a counter.
    public String getSymbol() {
        return symbol;
    }
}