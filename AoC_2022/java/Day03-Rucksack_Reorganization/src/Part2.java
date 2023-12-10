import java.util.Arrays;

public class Part2 extends Part1 {

    public int run() {
        Part1 p1 = new Part1();
        lines  = input.split("\n");
        String[][] groups = new String[100][3];

        int i = 0;
        for (String[] group : groups) {
            group[0] = lines[i * 3];
            group[1] = lines[i * 3 + 1];
            group[2] = lines[i * 3 + 2];
            i++;
        }

        int total = 0;
        char c = 'a';

        for (String[] group : groups) {
            c = overLapping(group);
            if (97 <= c && c <= 122) c -= 96;
            else if(65 <= c && c <= 90) c -= 38;
            total += c;
        }
        return total;
    }

    public char overLapping(String[] set){
        char[] set1 = set[0].toCharArray();
        String set2 = set[1];
        String set3 = set[2];

        for (char c : set1) {
            if(set2.contains(String.valueOf(c))){
                if(set3.contains(String.valueOf(c))){
                    return c;
                }
            }
        }

        return 0;
    }
}
