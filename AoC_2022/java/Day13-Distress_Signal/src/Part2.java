import java.util.ArrayList;
import java.util.LinkedList;

public class Part2 extends Part1 implements Runnable {

    public Part2(boolean t) {
        super(t);
    }

    @Override
    public void run() {
        distressSignal();
    }

}
