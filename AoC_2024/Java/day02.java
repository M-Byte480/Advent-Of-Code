import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.exit;

enum Classification{
    INCREASING,
    DECREASING
}


public class day02 extends AbstractAoc {
    private BufferedReader bufferedReader;
    private final ArrayList<ArrayList<Integer>> input;
    public day02(){
        super();
        super.loadInput("day02");
        this.input = new ArrayList<>();
        this.processInput();
    }

    @Override
    public void processInput() {
        this.bufferedReader = new BufferedReader(this.fileReader);
        try {
            while(bufferedReader.ready()){
                String line = bufferedReader.readLine();
                ArrayList<Integer> lines = Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toCollection(ArrayList<Integer>::new));
                this.input.add(lines);
            }
        } catch (IOException e){
            e.printStackTrace();
            exit(1);
        }
    }

    @Override
    public String solution01() {
        int safeLines = 0;

        for(ArrayList<Integer> numbers : this.input){
            Classification classification = classify(numbers);
            if(isSafe(classification, numbers)) {
                safeLines++;
            }
        }

        return safeLines + "";
    }

    @Override
    public String solution02() {
        int safeLines = 0;

        for(ArrayList<Integer> numbers : this.input){
            Classification classification = classify(numbers);
            if(isSafeWithTolerance(classification, numbers)) {
                safeLines++;
            }
        }

        return safeLines + "";
    }

    private boolean isSafeWithTolerance(Classification classification, ArrayList<Integer> numbers){
        int p1 = 0;
        int p2 = 1;
        boolean tolerance = false;
        while (p2 < numbers.size() && p1 < numbers.size()){
            int first = numbers.get(p1);
            int second = numbers.get(p2);


            if(classification == Classification.INCREASING){
                if(first >= second && tolerance){
                    return false;
                } else if (first >= second){
                    tolerance = true;
                    if(first > second){
                        numbers.remove(p2);
                    } else {
                        numbers.remove(p1);
                    }                    p1--;
                    p2--;
                } else if(!isWithinRange(first, second) && tolerance){
                    return false;
                } else if(!isWithinRange(first, second)){
                    tolerance = true;
                    numbers.remove(p2);
                    p1--;
                    p2--;
                }
            } else if(classification == Classification.DECREASING){
                if(first <= second && tolerance){
                    return false;
                } else if(first <= second){
                    tolerance = true;
                    if(first < second){
                        numbers.remove(p2);
                    } else {
                        numbers.remove(p1);
                    }
                    p1--;
                    p2--;
                } else if(!isWithinRange(first, second) && tolerance){
                    return false;
                } else if(!isWithinRange(first, second)){
                    tolerance = true;
                    numbers.remove(p2);
                    p1--;
                    p2--;
                }
            }

            p1++;
            p2++;
        }
        return true;
    }

    private boolean isWithinRange(int first, int second){
        return (1 <= Math.abs(first - second)) && (Math.abs(first - second) <= 3);
    }

    private boolean isSafe(Classification classification, ArrayList<Integer> numbers){
        int p1 = 0;
        int p2 = 1;

        while (p2 != numbers.size()){
            int first = numbers.get(p1);
            int second = numbers.get(p2);
            int difference = Math.abs(first - second);

            if(classification == Classification.INCREASING){
                if(first >= second || !((1 <= difference) && (difference <= 3))){
                    return false;
                }
            } else if(classification == Classification.DECREASING){
                if(first <= second|| !((1 <= difference) && (difference <= 3))){
                    return false;
                }
            }
            p1++;
            p2++;
        }
        return true;
    }

    private Classification classify(ArrayList<Integer> numbers){
        int first = numbers.get(0);
        int second = numbers.get(1);
        int third = numbers.get(2);
        int last = numbers.getLast();

        // Winner takes all
        int increasing = 0;
        int decreasing = 0;

        if(first < last){
            increasing++;
        } else{
            decreasing++;
        }

        if(second < last){
            increasing++;
        } else{
            decreasing++;
        }

        if(third < last){
            increasing++;
        } else{
            decreasing++;
        }

        return increasing > decreasing ? Classification.INCREASING : Classification.DECREASING;
    }

}
