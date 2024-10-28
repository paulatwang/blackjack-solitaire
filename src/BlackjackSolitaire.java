import java.util.Scanner;

public class BlackjackSolitaire {
    private static int MAX_DISCARDS = 4;
    private static int MAX_PLAYS = 16;
    public static int GAME_WIDTH = 42;
    private Deck drawPile;
    private Deck discardPile;
    private Card[][] grid = new Card[4][5];

    public BlackjackSolitaire(){
        // Setup drawPile and discardPile
        this.drawPile = new Deck(52);
        this.discardPile = new Deck(0);

        // display initial grid state
        String[][] placeholders = {
                {"1", "2", "3", "4", "5"},
                {"6", "7", "8", "9", "10"},
                {"NA", "11", "12", "13", "NA"},
                {"NA", "14", "15", "16", "NA"}
        };
        for (int i = 0; i < this.grid.length; i++){
            for (int j = 0; j < this.grid[0].length; j++){
                this.grid[i][j] = new Card(placeholders[i][j]);
            }
        }

        // NEW GAME
        System.out.println("#".repeat(GAME_WIDTH));
        System.out.println("#                NEW GAME                #");
        System.out.println("#".repeat(GAME_WIDTH));
        System.out.println();
        // Scores
        System.out.println("                 SCORING                 ");
        System.out.println("|______ HAND _______|_____ POINTS ______|");
        System.out.println("|     Blackjack     |        10         |");
        System.out.println("|        21         |         7         |");
        System.out.println("|        20         |         5         |");
        System.out.println("|        19         |         4         |");
        System.out.println("|        18         |         3         |");
        System.out.println("|        17         |         2         |");
        System.out.println("|       <=16        |         1         |");
        System.out.println("|       Bust        |         0         |");
        System.out.println(" " + "‾".repeat(GAME_WIDTH - 3));
        System.out.println();
        // Grid
        printGrid();
    }

    public void play(){
        /*
        * Continue until cardsPlayed = MAX_PLAYS
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
        int cardsPlayed = 0;
        int cardsDiscarded = 0;

        do {
            // Draw new card
            Card currentCard = drawPile.getTopCard();
            System.out.println(" Cards played: " + cardsPlayed + " ".repeat(7) +
                    "Cards discarded: " + cardsDiscarded);
            System.out.println();
            System.out.println("New card: " + currentCard.getLabel());

            // Prompt user
            Scanner s = new Scanner(System.in);
            System.out.print("Select grid position <1-16> or select <discard>: ");
            String input = s.nextLine();

            // Validate user response
            while (true) {
                input = input.toLowerCase();
                if (input.equals("discard")){
                    break; // Input is valid, exit the loop
                }
                try {
                    int inputNum = Integer.parseInt(input);
                    if (inputNum > 0 && inputNum <= MAX_PLAYS) {
                        break; // Input is valid, exit the loop
                    }
                } catch (NumberFormatException ignored) {
                }
                System.out.print("Invalid selection. Select again: ");
                input = s.nextLine();
            }

            // Update discard or grid
            if (input.equals("discard")){
                discardPile.add(currentCard); // add current card to discard pile
                cardsDiscarded ++;
                System.out.println("Discards remaining: " + (MAX_DISCARDS - cardsDiscarded));
            } else {
                updateGrid(input, currentCard); // update grid with current card
                printGrid();
                cardsPlayed++;
            }
        } while (cardsPlayed < MAX_PLAYS);

        // Calculate total


    }

    public void printGrid(){
        // Print header
        System.out.println(" " + "=".repeat((BlackjackSolitaire.GAME_WIDTH - 8)/2) +
                " GRID " +
                "=".repeat((BlackjackSolitaire.GAME_WIDTH - 8)/2));
        // Print grid
        for (Card[] row : this.grid) {
            System.out.print("|" + " ".repeat(5));
            for (Card card : row) {
                String label = card.getLabel(); // get card label
                if (label.equals("NA")) {
                    System.out.print(" ".repeat(7));
                } else if (label.length() == 1) {
                    System.out.print(label + " ".repeat(6));
                } else {
                    System.out.print(label + " ".repeat(5));
                }
            }
            System.out.println("|");
        }
        // Print footer
        System.out.println("|" + "_".repeat(BlackjackSolitaire.GAME_WIDTH - 2) + "|");
    }

    public void updateGrid(String userInput, Card newCard){
        // Update grid value to <newCard> at the location specified by <userInput>
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                String label = this.grid[i][j].getLabel(); // get label of card in grid
                if (label.equals(userInput)){
                    this.grid[i][j] = newCard; // update current card to new card
                }
            }
        }
    }

    public int calculatePoints(String[] array){
        int totalPoints = 0;
        for (String item : array){
            String suit = item.substring(item.length() - 1);
            String value = item.substring(0, item.length() - 1);
//            totalPoints +=
        }
        return totalPoints;
    }

}
