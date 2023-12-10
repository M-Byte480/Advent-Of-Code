import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 extends Part1 implements Runnable {

    public Part2(boolean t) {
        super(t);
    }

    @Override
    public void simulateSandFall(){
        String[] pairs = input.split("\n");
        String[][][] lines = Arrays.stream(pairs)
                .map(line ->
                        Arrays.stream(line.split(" -> "))
                                .map(section -> section.split(","))
                                .toArray(String[][]::new))
                .toArray(String[][][]::new);

        int smallestX = Integer.parseInt(lines[0][0][0]);
        int largestX = Integer.parseInt(lines[0][0][0]);

        int smallestY = Integer.parseInt(lines[0][0][1]);
        int largestY = Integer.parseInt(lines[0][0][1]);
        for (String[][] line : lines) {
            for (String[] connection : line) {

                int testValueX = Integer.parseInt(connection[0]);
                if(testValueX < smallestX){
                    smallestX = testValueX;
                } else if (testValueX > largestX) {
                    largestX = testValueX;
                }

                int testValueY = Integer.parseInt(connection[1]);
                if(testValueY < smallestY){
                    smallestY = testValueY;
                } else if (testValueY > largestY) {
                    largestY = testValueY;
                }
            }
        }

        ArrayList<ArrayList<String>> board = new ArrayList<>();

        // Populate the board
        for (int i = 0; i < largestY + 1 + 1; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < largestX - smallestX + 1; j++) {
                board.get(i).add(".");
            }
        }

            board.add(new ArrayList<>());
            for (int j = 0; j < largestX - smallestX + 1; j++) {
                board.get(board.size() - 1).add("#");
            }


        for (int set = 0; set < lines.length ; set ++) {
            String[][] line = lines[set];
            for (int element = 0; element < line.length - 1; element++) {

                int startCoorX = Integer.parseInt(line[element][0]);
                int finishCoorX = Integer.parseInt(line[element + 1][0]);

                int startCoorY = Integer.parseInt(line[element][1]);
                int finishCoorY = Integer.parseInt(line[element + 1][1]);

                // Vertical connection
                if (startCoorX == finishCoorX){
                    int start = (Math.min(startCoorY, finishCoorY));
                    for (int row = 0; row < Math.abs(startCoorY - finishCoorY) + 1; row++) {
                        board.get(start + row).set(startCoorX - smallestX, "#");
                    }
                }

                // Horizontal connection
                if(startCoorY == finishCoorY) {
                    int start = (Math.min(finishCoorX, startCoorX));
                    for (int column = 0; column < Math.abs(startCoorX - finishCoorX) + 1; column++) {
                        board.get(startCoorY).set(start - smallestX + column, "#");
                    }
                }
            }

        }

        int sandDrops = 0;
        int increase = 20;
        int count = 0;
        // Drop sand
        board.get(0).set(500 - smallestX, "+");
        boolean fall = false;
        int var = 500;
        int column = 500 - smallestX;
        boolean left = true;
        String sand = "O";
        int displacement = 0;
//        for (int t = 0; t < var; t++) {
        while(!fall) {

            column = 500 - smallestX + displacement;
            int row = 0;
            boolean stop = false;
            // path
            while (!stop) {
                try{
                    if(board.get(0).get(column).equals("O")){stop = true; fall = true; break;}
                    if (!board.get(row + 1).get(column).equals("#") && !board.get(row + 1).get(column).equals("O")) {
                        row++;
                    } else if (!board.get(row + 1).get(column - 1).equals("#") && !board.get(row + 1).get(column - 1).equals("O")) {
                        column--;
                        row++;
                        left= true;
                    } else if (!board.get(row + 1).get(column + 1).equals("#") && !board.get(row + 1).get(column + 1).equals("O")) {
                        column++;
                        row++;
                        left=  false;
                    } else {
                        board.get(row).set(column, sand);
                        stop = true;
                        sandDrops++;
                        left = false;
                    }

                }catch (IndexOutOfBoundsException e){
                    if(left){
                        for (int i = 0; i < board.size(); i++) {
                            ArrayList<String> strings = board.get(i);
                            strings.add(0, ".");
                            if(i == board.size() - 1) strings.set(0, "#");
                        }
                        displacement ++;
                    }else{
                        for (int i = 0; i < board.size(); i++) {
                            ArrayList<String> strings = board.get(i);
                            strings.add(".");
                            if  (i == board.size() - 1) strings.set(strings.size() - 1, "#");
                        }
//                        displacement --;
                    }
                    stop = true;
                }
            }
        }

        for (ArrayList<String> strings : board) {
            for (String string : strings) {
                System.out.printf(string);
            }
            System.out.println();
        }
        System.out.println(sandDrops);
    }
}
