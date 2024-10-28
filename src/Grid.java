public class Grid {
    String[][] grid;

    public Grid() {
        this.grid = new String[][]{
                {"1", "2", "3", "4", "5"},
                {"6", "7", "8", "9", "10"},
                {"NA", "11", "12", "13", "NA"},
                {"NA", "14", "15", "16", "NA"}
        };
    }
    public Grid(String[][] grid){
        this.grid = grid;
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
                if (grid[i][j].equals(input)){
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        return position;
    }
}
