import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class Part1 implements Runnable {
    String input;
    String path = "Day14-Regolith_Reservior/src/sand.txt";
    String pathTest = "Day14-Regolith_Reservior/src/sandTest.txt";
    String[] lines;

    public Part1(boolean t) {
        if (t) {
            getInputFromFile(path);
        } else {
            getInputFromFile(pathTest);
        }
    }

    public void getInputFromFile(String path) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.input = contentBuilder.toString();
        lines = input.split("\n");
    }

    @Override
    public void run() {
        simulateSandFall();
    }

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

        String[][] board = new String[largestY + 1][largestX - smallestX + 1];

        // Populate the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ".";
            }
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
                        board[start + row][startCoorX - smallestX] = "#";
                    }
                }

                // Horizontal connection
                if(startCoorY == finishCoorY) {
                    int start = (Math.min(finishCoorX, startCoorX));
                    for (int column = 0; column < Math.abs(startCoorX - finishCoorX) + 1; column++) {
                        board[startCoorY][start - smallestX + column] = "#";
                    }
                }
            }

        }
        int sandDrops = 0;
        // Drop sand
        board[0][500 - smallestX] = "+";
        boolean fall = false;
        while(!fall) {

            int drop = 500 - smallestX;
            String sand = "O";
            int row = 0;
            boolean stop = false;
            while (!stop) {
                try{
                    if (!board[row + 1][drop].equals("#") && !board[row + 1][drop].equals("O")) {
                        row++;
                    } else if (!board[row + 1][drop - 1].equals("#") && !board[row + 1][drop - 1].equals("O")) {
                        drop--;
                        row++;
                    } else if (!board[row + 1][drop + 1].equals("#") && !board[row + 1][drop + 1].equals("O")) {
                        drop++;
                        row++;
                    } else {
                        board[row][drop] = sand;
                        stop = true;
                        sandDrops++;
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    stop = true;
                    fall = true;
                }
            }
        }

        for (String[] strings : board) {
            for (String string : strings) {
                System.out.printf(string);
            }
            System.out.println();
        }
        System.out.println(sandDrops);
    }

}
