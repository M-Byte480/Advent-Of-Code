public class Main {
    public static void main(String[] args) {
        Part1 p1 = new Part1(false);
        Thread part1 = new Thread(p1);
        part1.start();

//        Part2 p2 = new Part2(true);
//        Thread part2 = new Thread(p2);
//        part2.start();
    }
}
