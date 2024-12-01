import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class day01 extends AbstractAoc{
    private BufferedReader bufferedReader;
    private final ArrayList[] input;
    public day01(){
        super();
        super.loadInput("day01");
        this.input = new ArrayList[2];
        this.processInput();
    }


    public void processInput() {
        this.bufferedReader = new BufferedReader(this.fileReader);
        try {
            ArrayList<Integer> firstList = new ArrayList<>();
            ArrayList<Integer> secondList = new ArrayList<>();
            this.input[0] = firstList;
            this.input[1] = secondList;
            while (this.bufferedReader.ready()) {
                String line = this.bufferedReader.readLine();
                String[] values = line.split(" {3}");

                firstList.add(Integer.valueOf(values[0]));
                secondList.add(Integer.valueOf(values[1]));

                }

            System.out.println("Finished reading");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public String solution01() {
        ArrayList<Integer> firstList = this.input[0];
        ArrayList<Integer> secondList = this.input[1];

        firstList.sort(Integer::compareTo);
        secondList.sort(Integer::compareTo);

        Integer[] result = new Integer[firstList.size()];
        for (int i = 0; i < firstList.size(); i++) {
            int first = firstList.get(i);
            int second = secondList.get(i);
            result[i] = Math.abs(first - second);
        }

        int sum = Arrays.stream(result)
                .reduce(0, Integer::sum);
        return String.valueOf(sum);
    }

    public String solution02() {
        ArrayList<Integer> firstList = this.input[0];
        ArrayList<Integer> secondList = this.input[1];

        firstList.sort(Integer::compareTo);
        secondList.sort(Integer::compareTo);

        HashMap<Integer, Integer> occurences = new HashMap<>();

        for (int i = 0; i < secondList.size(); i++) {
            if(occurences.containsKey(secondList.get(i))) {
                occurences.put(secondList.get(i), occurences.get(secondList.get(i)) + 1);
            } else {
                occurences.put(secondList.get(i), 1);
            }
        }
        
        int sum = 0;
        for (int i = 0; i < firstList.size(); i++) {
            Integer numberWeLookingFor = firstList.get(i);
            if(occurences.containsKey(numberWeLookingFor)){
                sum = sum + (numberWeLookingFor * occurences.get(numberWeLookingFor));
            }
        }

        return String.valueOf(sum);
    }
}