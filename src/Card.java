public class Card {
    private String label;
    private String value;
    private int points;

    public Card(String placeholder){
        // Constructor for initial cards in placeholder grid
        this.label = placeholder;
        this.value = placeholder;
        this.points = 0;
    }

    public Card(String suit, String value) {
        // Constructor for playing cards
        this.label = value + suit;
        this.value = value;

        /*
         * Set card points
         * - set initial points of Ace to 11
         * - when playing, if row/column sum has A and exceeds 21, -10 from sum
         * */
        if (value.equals("A")){
            this.points = 11;
        } else if (value.equals("J") || value.equals("Q") || value.equals("K")){
            this.points = 10;
        } else {
            this.points = Integer.parseInt(value);
        }
    }

    // Getter methods
    public String getLabel(){
        return this.label;
    }

    public int getPoints(){
        return this.points;
    }

    public String getValue(){
        return this.value;
    }


}
