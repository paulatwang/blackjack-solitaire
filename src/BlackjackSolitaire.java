import java.util.Scanner;

public class BlackjackSolitaire {
    private Deck drawPile;
    private Deck discardPile;
    private Grid grid;

    public BlackjackSolitaire(){
        // Setup drawPile and discardPile
        this.drawPile = new Deck(52);
        this.discardPile = new Deck(0);

        // display initial grid state
        this.grid = new Grid();
        this.grid.printGrid();
    }

    public void play(){
        /*
        * Continue until cardsPlaced = 52
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
        int cardsPlaced = 0;
        int cardsDiscarded = 0;

//        do {
            // Draw new card
            Card topCard = drawPile.getTopCard();
            System.out.println("New card: " + topCard.getCard());

            // Prompt user
            Scanner s = new Scanner(System.in);
            System.out.println("Discards remaining: " + cardsDiscarded);
            System.out.print("Select a position <1-16> or select <discard>: ");
            String placement = s.nextLine();

            // Validate user response
            placement = placement.toLowerCase();
            while (!placement.equals("discard") || Integer.parseInt(placement) < 0 || Integer.parseInt(placement) > 16){
                System.out.println("Invalid selection. Select again: ");
                placement = s.nextLine();
                placement = placement.toLowerCase();
            }

            // Update discard or grid
            if (placement.equals("discard")){
                cardsDiscarded ++;
            } else {
                cardsPlaced ++;
            }
            //
//        } while (cardsPlaced < 52);

    }
}
