import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day04 extends AbstractAoc{
    private ArrayList<String> input;

    public Day04(){
        super();
        super.loadInput("day04");
        this.input = new ArrayList<>();
        this.processInput();
    }

    public void processInput() {
        BufferedReader br = new BufferedReader(this.fileReader);
        try {
            while (br.ready()) {
                input.add(br.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String solution01() {
        int count = 0;
        for (int i = 0; i < input.size(); i++){
            for (int j = 0; j < input.get(i).length(); j++){
                count += isValidHorizontally(i, j) ? 1 : 0;
                count += isValidVertically(i, j) ? 1 : 0;
                count += isValidDiagonally(i, j);
            }

        }

        return count + "";
    }

    private int isValidDiagonally(int x, int y){
            String s1 = "";
            String s2 = "";
            if(x + 3 < input.size() && y + 3 < input.get(0).length())
                s2 = getDiagonal(true, true, x, y);

            if(x + 3 < input.size() && y - 3 >= 0)
                s1 = getDiagonal(true, false, x, y);


            int count = 0;
            if(s1.contains("XMAS") || s1.contains("SAMX")){
                count++;
                System.out.printf("Found diagonally backward %s at %d, %d\n", s1, x, y);
            }

            if(s2.contains("XMAS") || s2.contains("SAMX")){
                count++;
                System.out.printf("Found diagonally forward %s at %d, %d\n", s2, x, y);
            }

            return count;
    }

    private String getDiagonal(boolean row, boolean column, int x, int y){
        int rowInt = row ? 1 : -1;
        int columnInt = column ? 1 : -1;
        String returnValue = "";
        for (int i = 0; i < 4; i++) {
            returnValue = returnValue + input.get(x + i * rowInt).charAt(y + i * columnInt) + "";
        }
        return returnValue;
    }

    private boolean isValidVertically(int x, int y){
        if(x + 4 > input.size())
            return false;


        List<String> column = input.stream()
                .map(s -> s.substring(y, y + 1))
                .toList();
        String ss = String.join("", column);
        ss = ss.substring(x, x + 4);
        boolean result =  ss.contains("XMAS") || ss.contains("SAMX");
        if(result)
            System.out.printf("Found vertically %s at %d, %d\n", ss, x, y);

        return result;
    }

    private boolean isValidHorizontally(int x, int y){
        if (y + 4 > input.get(0).length())
            return false;

        String ss = input.get(x).substring(y, y + 4);


        return ss.contains("XMAS") || ss.contains("SAMX");
    }

    public String solution02() {
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if(input.get(i).charAt(j) == 'A'){
                    count += isValidMAS(i, j) ? 1 : 0;
                }
            }
        }
        return count + "";
    }

    private boolean isValidMAS(int x, int y){
        if (x + 1 < input.size() && y + 1 < input.get(0).length()) {
            if(x - 1 >= 0 && y - 1 >= 0){
                String s1 = getDiagonal2(true, true, x - 1, y - 1);
                String s2 = getDiagonal2(true, false, x - 1, y + 1);

                if(s1.contains("MAS") || s1.contains("SAM")){
                    return s2.contains("MAS") || s2.contains("SAM");
                }
            }
        }
        return false;
    }

    private String getDiagonal2(boolean row, boolean column, int x, int y){
        int rowInt = row ? 1 : -1;
        int columnInt = column ? 1 : -1;
        String returnValue = "";
        for (int i = 0; i < 3; i++) {
            returnValue = returnValue + input.get(x + i * rowInt).charAt(y + i * columnInt) + "";
        }
        return returnValue;
    }
}
