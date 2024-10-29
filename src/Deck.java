import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck(String type) {
        /*
         * Constructs a new deck based on one of 2 valid types:
         *   - "standard" = standard 52-card deck
         *   - "empty" = empty deck
         *   - "placeholders" = deck of placeholder grid values
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
        Collections.shuffle(this.cards);
    }

    public Card getTopCard(){
        return this.cards.remove(0);

    }

    public void add(Card card){
        cards.add(card);
    }
}




