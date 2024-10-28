import java.util.Arrays;
import java.util.Scanner;

public class BlackjackSolitaire {
    private static int MAX_DISCARDS = 4;
    private static int MAX_PLAYS = 16;
    private Deck drawPile;
    private Deck discardPile;
    private String[][] grid;

    public BlackjackSolitaire(){
        // Setup drawPile and discardPile
        this.drawPile = new Deck(52);
        this.discardPile = new Deck(0);

        // display initial grid state
        this.grid = new String[][]{
                {"1", "2", "3", "4", "5"},
                {"6", "7", "8", "9", "10"},
                {"NA", "11", "12", "13", "NA"},
                {"NA", "14", "15", "16", "NA"}
        };
        printGrid();
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
        int cardsPlayed = 0;
        int cardsDiscarded = 0;

//        do {
            // Draw new card
            Card topCard = drawPile.getTopCard();
            System.out.println("New card: " + topCard.getCard());

            // Prompt user
            Scanner s = new Scanner(System.in);
            System.out.println("Discards remaining: " + (4 - cardsDiscarded));
            System.out.print("Select a position <1-16> or select <discard>: ");
            String input = s.nextLine();

            // Validate user response
            input = input.toLowerCase();
            while ((!input.equals("discard")) &&
                    Integer.parseInt(input) < 0 &&
                    Integer.parseInt(input) > MAX_PLAYS)
            {
                System.out.print("Invalid selection. Select again: ");
                input = s.nextLine();
                input = input.toLowerCase();
            }

            // Update discard or grid
            if (input.equals("discard")){
                cardsDiscarded ++;
                System.out.println("Discards remaining: " + (MAX_DISCARDS - cardsDiscarded));
            } else {
                System.out.println("Input: " + input);
                int[] position = getGridPosition(input);
                System.out.println("Position: " + Arrays.toString(position));
                updateGrid(position, topCard.getCard());
                printGrid();
                cardsPlayed ++;
            }
            //
//        } while (cardsPlayed < MAX_PLAYS);

    }

    public void printGrid(){
        for (String[] row : this.grid) {
            for (int j = 0; j < this.grid[0].length; j++) {
                if (row[j].equals("NA")) {
                    System.out.print("       ");
                } else if (row[j].length() == 1) {
                    System.out.print(row[j] + "      ");
                } else {
                    System.out.print(row[j] + "     ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[] getGridPosition(String input){
        int[] position = new int[2];
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                if (this.grid[i][j].equals(input)){
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        return position;
    }

    public void updateGrid(int[] position, String newValue){
        int row = position[0];
        int col = position[1];
        this.grid[row][col] = newValue;
    }
}
