public class Card {
    private String suit;
    private String value;
    private int points;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;

        // calculate points
        if (this.value.equals("A")){
            this.points = 11;
        } else if (this.value.equals("J") || this.value.equals("Q") || this.value.equals("K")){
            this.points = 10;
        } else {
            this.points = Integer.parseInt(this.value);
        }
    }

    public String getCard(){
        return this.suit + this.value;
    }

    public int getPoints(){
        return this.points;
    }


}
