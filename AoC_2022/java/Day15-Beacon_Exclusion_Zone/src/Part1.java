import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Part1 implements Runnable {
    String input;
    String path = "Day15-Beacon_Exclusion_Zone/src/beacon.txt";
    String pathTest = "Day15-Beacon_Exclusion_Zone/src/beaconTest.txt";
    String[] lines;
    int xMax;
    int xMin;
    int yMax;
    int yMin;
    String[][] board;
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
        sendSignals();
    }

    public void sendSignals() {
        String[] inputLines = input.split("\n");
        String[][][] array = Arrays.stream(inputLines)
                .map(eachLine ->
                        Arrays.stream(eachLine.substring("Sensor at x=".length()).split(": closest beacon is at x="))
                                .map(eachPosition -> eachPosition.split(", y=")).toArray(String[][]::new))
                .toArray(String[][][]::new);


        ArrayList<ArrayList<ArrayList<String>>> lines = new ArrayList<>();

        for (int j = 0; j < array.length; j++) {
            String[][] strings = array[j];
            lines.add(new ArrayList<>());

            for (int i = 0; i < strings.length; i++) {
                String[] string = strings[i];
                lines.get(j).add(new ArrayList<>());

                for (String s : string) {
                    lines.get(j).get(i).add(s);
                }
            }
        }

        int xMax = Integer.parseInt(lines.get(0).get(0).get(0));
        int yMax = Integer.parseInt(lines.get(0).get(0).get(1));
        int xMin = Integer.parseInt(lines.get(0).get(1).get(0));
        int yMin = Integer.parseInt(lines.get(0).get(1).get(1));

        for (ArrayList<ArrayList<String>> line : lines) {
            for (ArrayList<String> signal : line) {
                for (int i = 0; i < 2; i++) {
                    int testValue = Integer.parseInt(signal.get(i));
                    if (i % 2 == 0) {
                        // x values
                        if (testValue > xMax) xMax = testValue;
                        else if (testValue < xMin) xMin = testValue;
                    } else {
                        // y values
                        if (testValue > yMax) yMax = testValue;
                        else if (testValue < yMin) yMin = testValue;
                    }
                }
            }
        }

        int columnMin = xMin;
        int rowMin = yMin;
        String[][] board = new String[yMax - yMin + 1][xMax - xMin + 1];

        for (int i = 0; i < board.length; i++) {
            for (int i1 = 0; i1 < board[0].length; i1++) {
                board[i][i1] = ".";
            }
        }

        // We have to offset everything to translate it according to our board
        for (int i = 0; i < lines.size(); i++) {
            ArrayList<ArrayList<String>> thisLine = lines.get(i);
            for (int i1 = 0; i1 < thisLine.size(); i1++) {
                ArrayList<String> position = thisLine.get(i1);
                int xCoor = Integer.parseInt(position.get(0)) - xMin;
                int yCoor = Integer.parseInt(position.get(1)) - yMin;
                if (i1 % 2 == 0) {
                    board[yCoor][xCoor] = "S";
                } else {
                    board[yCoor][xCoor] = "B";
                }

            }
        }

        this.board = board;
        this.xMax = xMax;
        this.xMin = xMin;
        this.yMax = yMax;
        this.yMin = yMin;
        // Logic

        for (int k = 0; k < 1; k++) {
            ArrayList<ArrayList<String>> line = lines.get(0);
            // < <Sensor: x, y> , <Beacon x , y> >
            // xCoor == xMin == Column
            // yCoor == yMin == Row
            boolean found = false;

            int signalRadius = 0;

            int sensorColumn = Integer.parseInt(line.get(0).get(0)) - xMin;
            int sensorRow = Integer.parseInt(line.get(0).get(1)) - yMin;

            int beaconColumn = Integer.parseInt(line.get(1).get(0)) - xMin;
            int beaconRow = Integer.parseInt(line.get(1).get(1)) - yMin;


            int testRow = sensorRow;
            int testColumn = sensorColumn;

//            while (!(found)) {
                for (int i = 0; i < 5; i++) {

                signalRadius++;
                testRow = sensorRow + signalRadius; // drop said rows
                testColumn = sensorColumn;

                while (testRow != sensorRow) {
                    testRow--;
                    testColumn--;

                    if (this.actionOnVar(testColumn, testRow, beaconRow, beaconColumn)) {
                        found = true;
                    }
                }
                while (testColumn != sensorColumn) {
                    testRow--;
                    testColumn++;

                    if (this.actionOnVar(testColumn, testRow, beaconRow, beaconColumn)) {
                        found = true;
                    }
                }
                while (testRow != sensorRow) {
                    testRow++;
                    testColumn++;

                    // Within bounds
                    if (this.actionOnVar(testColumn, testRow, beaconRow, beaconColumn)) {
                        found = true;
                    }
                }

                while (testColumn != sensorColumn) {
                    testRow++;
                    testColumn--;

                    // Within bounds
                    if (this.actionOnVar(testColumn, testRow, beaconRow, beaconColumn)) {
                        found = true;
                    }
                }
            }
            // Are we in the board?


        }



        // Text output
        System.out.print("    ");
        for (int i = 0; i < xMax - xMin + 1; i++) {
            if (xMin + i % 5 == 0 && xMin + i > 9) {
                System.out.print((xMin + i) / 10);
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();

        System.out.print("    ");
        for (int i = 0; i < xMax - xMin + 1; i++) {
            if (xMin + i % 5 == 0) {
                System.out.print((xMin + i) % 10);
            } else {
                System.out.print(" ");
            }
        }

        System.out.println();

        for (int i = 0; i < board.length; i++) {
            System.out.printf("%3d ", yMin + i);
            for (int i1 = 0; i1 < board[0].length; i1++) {
                System.out.print(board[i][i1]);
            }
            System.out.println();
        }
    }


    private boolean actionOnVar(int testColumn, int testRow, int beaconRow, int beaconColumn){
        if (testColumn > xMax - xMin + 1) {
            return false;
        } else if (testColumn < xMin) {
            return false;
        }

        if (testRow > yMax - yMin + 1) {
            return false;
        } else if (testRow < yMin) {
            return false;
        }

        if (testRow == beaconRow && testColumn == beaconColumn) {
            return true;
        } else if (board[testRow][testColumn].equals("S")) {
            return false;
        }else if(board[testRow][testColumn].equals("B")) {
            return true;
        }else{
            board[testRow][testColumn] = "#";
        }

        return false;
    }
}
