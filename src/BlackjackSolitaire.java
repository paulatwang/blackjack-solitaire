import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackSolitaire {
    public static int GAME_WIDTH = 42;
    private Deck drawPile;
    private Deck discardPile;
    private Card[][] grid = new Card[4][5];

    public BlackjackSolitaire(){
        // Setup drawPile and discardPile
        this.drawPile = new Deck("standard");
        this.discardPile = new Deck("empty");

        // Shuffle drawPile
        this.drawPile.shuffle();

        // Setup grid with placeholder cards
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

        // NEW GAME TITLE
        System.out.println("#".repeat(GAME_WIDTH));
        System.out.println("#                NEW GAME                #");
        System.out.println("#".repeat(GAME_WIDTH));
        System.out.println();
        // Scoring reference
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
        int MAX_DISCARDS = 4;
        int MAX_PLAYS = 16;
        int cardsPlayed = 0;
        int cardsDiscarded = 0;
        ArrayList<Integer> previousPlays = new ArrayList<>();

        // Begin while loop
        do {
            // Draw new card
            Card currentCard = drawPile.getTopCard();
            System.out.println("( Cards played: " + cardsPlayed + " ".repeat(7) +
                    "Cards discarded: " + cardsDiscarded + " )");
            System.out.println();
            System.out.println("New card: " + currentCard.getLabel());

            // Prompt user
            Scanner s = new Scanner(System.in);
            System.out.print("Select grid position <1-16> or select <discard>: ");
            String input = s.nextLine();

            // Validate user response
            while (true) {
                input = input.replaceAll("[^a-zA-Z0-9]", ""); // remove all non-alphanumeric
                input = input.toLowerCase(); // convert to lowercase
                if (input.equals("discard") && cardsDiscarded < MAX_DISCARDS){
                    break; // Input is valid, exit the loop
                }
                try {
                    int inputNum = Integer.parseInt(input);
                    if (inputNum > 0 && inputNum <= MAX_PLAYS && !previousPlays.contains(inputNum)) {
                        previousPlays.add(inputNum); // add valid input to previousPlays ArrayList
                        break; // Input is valid, exit the loop
                    }
                } catch (NumberFormatException ignored) { // ignore NumberFormatException
                }
                System.out.print("Invalid selection. Select again: ");
                input = s.nextLine();
            }

            // Update discard or grid
            if (input.equals("discard")){
                discardPile.add(currentCard); // add current card to discard pile
                cardsDiscarded ++;
                System.out.println("( Discards remaining: " + (MAX_DISCARDS - cardsDiscarded) + " )");
            } else {
                updateGrid(input, currentCard); // update grid with current card
                printGrid();
                cardsPlayed++;
            }
        } while (cardsPlayed < MAX_PLAYS);

        System.out.println("Grid complete. Scoring in progress...");

        // Calculate total
        int totalPoints = 0;

        // Row totals
        for (Card[] row : grid){
            int rowPoints = calculatePoints(row);
            if (rowPoints > 21){
                totalPoints += 0; // row points exceed 21
            } else if (rowPoints == 21){
                totalPoints += 7; // row points = 21
            } else if (rowPoints == 20){
                totalPoints += 5; // row points = 20
            } else if (rowPoints == 19){
                totalPoints += 4; // row points = 19
            } else if (rowPoints == 18){
                totalPoints += 3; // row points = 18
            } else if (rowPoints == 17){
                totalPoints += 2; // row points = 17
            } else {
                totalPoints += 1; // row points <= 16
            }
        }
        // Column totals
        for (int i = 0; i < grid[0].length; i++){
            Card[] col = new Card[grid.length];
            for (int j = 0; j < grid.length; j++){
                col[j] = grid[j][i]; // add card to col
            }
            int colPoints = calculatePoints(col);
            if (colPoints > 21){
                totalPoints += 0;
            } else if ((i == 0 || i == 4) && colPoints == 21){
                totalPoints += 10; // col = 21 for blackjack columns
            } else if (colPoints == 21) {
                totalPoints += 7; // col points = 21
            } else if (colPoints == 20){
                totalPoints += 5; // col points = 20
            } else if (colPoints == 19){
                totalPoints += 4; // col points = 19
            } else if (colPoints == 18){
                totalPoints += 3; // col points = 18
            } else if (colPoints == 17){
                totalPoints += 2; // col points = 17
            } else {
                totalPoints += 1; // col points <= 16
            }
        }
        System.out.println("Scoring complete!");
        System.out.printf("Game over! You scored %d points.", totalPoints);
    }

    public void printGrid(){
        /*
        * Prints out grid using GAME_WIDTH specifications
        * */

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
                } else if (label.length() == 2) {
                    System.out.print(label + " ".repeat(5));
                } else {
                    System.out.print(label + " ".repeat(4));
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

    public int calculatePoints(Card[] array){
        // calculate card points for a single array
        int totalPoints = 0;
        int aceCount = 0;

        // For each card in array
        for (Card card : array){
            totalPoints += card.getPoints(); // add card points to total
            if (card.getValue().equals("A")){
                aceCount += 1; // increment aceCount
            }
        }

        // Adjust points for Ace cards
        while (aceCount > 0){
            if (totalPoints <= 21){
                break; // points within range, exit loop
            }
            totalPoints -= 10;
            aceCount -= 1;
        }
        return totalPoints;
    }

}
