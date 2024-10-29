import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck(String type) {
        /*
         * Constructs a new deck based on one of 2 valid types:
         *   - "standard" = standard 52-card deck
         *   - "empty" = empty deck
         */

        // Validate input
        if (!type.equals("standard") && !type.equals("empty")) {
            throw new IllegalArgumentException("Invalid deck type. Allowed types: 'standard' or 'empty'");
        }

        cards = new ArrayList<>();
        String[] arrayOfValues = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] arrayOfSuits = new String[]{"S", "C", "D", "H"}; // S: Spades, C: Clubs, D: Diamonds, H: Hearts

        // Create deck for standard 52-card deck
        if (type.equals("standard")){
            for (String suit : arrayOfSuits){
                for (String value : arrayOfValues){
                    cards.add(new Card(suit, value));
                }
            }
        }

    }

    public void shuffle(){
        // shuffles cards; no return
        Collections.shuffle(this.cards);
    }
    public void add(Card card){
        // adds new card to deck; no return
        cards.add(card);
    }
    public Card getTopCard(){
        // removes top card from deck and returns removed card
        return this.cards.removeFirst();
    }


}




