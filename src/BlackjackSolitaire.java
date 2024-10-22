public class BlackjackSolitaire {
    private Deck drawPile;
    private Deck discardPile;
    private Grid grid;

    public BlackjackSolitaire(){
        // Setup empty grid

        // Setup drawPile and discardPile
        this.drawPile = new Deck(52);
        this.discardPile = new Deck(0);

        // display initial grid state
        this.grid = new Grid();
        this.grid.printGrid();
    }

    public void play(){
        /*
        * Continue until full = true
        *   - draw new card
        *   - prompt user to place card on grid OR discard pile
        *       - if grid:
        *           - add card to grid position
        *           - if position invalid, prompt user again
        *       - if discard:
        *           - display new discard pile count
        *           - if count exceeds max (4), prompt user again
        *   - display new grid state
        *   - check grid fullness
        */

    }
}
