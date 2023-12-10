import java.util.ArrayList;
import java.util.Arrays;

public class Part2 extends Part1 implements Runnable {

    public Part2(boolean t) {
        super(t);
    }


    @Override
    public void run() {
        System.out.println(screenDisplay());
    }

    public String screenDisplay(){
        String[] lines = input.split("\n");
        StringBuilder solution = new StringBuilder();

        int xRegister = 1;

        ArrayList<String> linesUpdated = new ArrayList<>(Arrays.stream(lines).toList());
        int element = 0;
        boolean running = true;

        while (running) {
            if(!linesUpdated.get(element).equals("noop")){
                linesUpdated.add(element, "noop");
                element++;
            }

            element++;

            if(element == linesUpdated.size()){
                running = false;
            }

        }

        int number;

        for (int i = 0; i < linesUpdated.size(); i ++) {
            String[] instruction = linesUpdated.get(i).split(" ");

            if(instruction.length == 1){ // noop
                number = 0;
            }else {
                number = Integer.parseInt(instruction[1]);
            }

            if(i % 40 == 0){
                solution.append("\n");
            }

            if(xRegister - 1 <= (i % 40) &&  (i % 40) <= xRegister + 1){
                solution.append("#");
            }else{
                solution.append(".");
            }

            xRegister += number;
        }
        return solution.toString();
    }
}