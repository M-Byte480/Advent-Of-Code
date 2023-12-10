public class Part2 extends Part1 implements Runnable {

    public Part2() {
        super();
    }


    @Override
    public void run() {
        Part2 aoc = new Part2();

        lines = aoc.getInput().split("\n");
        int[][] forest = new int[lines.length][lines[0].length()];

        for (int row = 0; row < forest.length; row++) {
            for (int i = 0; i < forest[0].length; i++) {
                forest[row][i] = Integer.parseInt(String.valueOf(lines[row].charAt(i)));
            }
        }

        System.out.println("Solution to part 2: " + treesThatCantSee(forest));

    }

    public int treesThatCantSee(int[][] forest) {
        int max = 0;
        for (int row = 1; row < forest.length - 1; row++) {
            for (int column = 1; column < forest[0].length - 1; column++) {
                int value = treesBeSeen(row, column, forest);
                if(max <= value){
                    max = value;
                }
            }
        }

        return max;
    }

    // We know the tree itself is not higher than itself. We just need to know if the trees anywhere before can be seen.

    public int treesBeSeen(int row, int column, int[][] forest) {
        // Vertical Check
        int heightOfTree = forest[row][column];

        // Assume we can see all trees
        int upperBound = 0;
        int lowerBound = 0;
        int leftBound = 0;
        int rightBound = 0;

        // This checks if the tree in a vertical set, can be seen in the forest of said state
        for (int i = row - 1; i >= 0; i--) {
            // Upper Bound
            if (forest[i][column] < heightOfTree && i < row) {
                upperBound ++ ;
            } else if (forest[i][column] >= heightOfTree) {
                upperBound++;
                break;
            }
        }

        // This checks if the tree in a vertical set, can be seen in the forest of said state
        for (int i = row + 1; i < forest.length; i++) {
            // Upper Bound
            if (forest[i][column] < heightOfTree) {
                lowerBound ++ ;
            } else if (forest[i][column] >= heightOfTree) {
                lowerBound ++;
                break;
            }
        }


        // If it cant be seen vertically

        // Horizontal Check
        for (int i = column - 1; i >= 0; i--) {
            if ( forest[row][i] < heightOfTree) {
                leftBound++;
            } else if (forest[row][i] >= heightOfTree) {
                leftBound++;
                break;
            }
        }

        for (int i = column + 1; i < forest.length ; i++) {
            if ( forest[row][i] < heightOfTree) {
                rightBound++;
            } else if (forest[row][i] >= heightOfTree) {
                rightBound++;
                break;
            }
        }

        return upperBound * lowerBound * leftBound * rightBound;
    }
}