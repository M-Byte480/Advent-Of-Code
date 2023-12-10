import java.util.Collections;

public class Part2 extends Part1 {

    public int run(){
        Part2 aoc = new Part2();
        super.run();

        Collections.sort(elves);
        int numberOfElves = elves.size();
        return elves.get(numberOfElves - 1) +
                elves.get(numberOfElves - 2) +
                elves.get(numberOfElves - 3);
    }
}