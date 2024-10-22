import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;

    public Deck(int numCards){
        /*
        * Constructs a new deck based on one of 2 valid inputs:
        *   - 52 = standard deck
        *   - 0 = empty deck
        */
        // Check input validity
        if (numCards != 0 || numCards != 52){
            throw new IllegalArgumentException("numCards can only be 0 or 52");
        }

        // Construct card deck
//        cards = new ArrayList<>();
//        for (int i = 0; i < numCards; i++){
//            Card newCard = new Card();
//            cards.add(newCard);
//            }
        }




}
