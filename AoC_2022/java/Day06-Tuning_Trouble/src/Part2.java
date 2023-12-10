public class Part2 extends Part1 {

    public int run() {
        Part1 p1 = new Part1();
        String inputs = p1.input;
        int lenght = 14;

        for (int i = 0; i < inputs.length(); i++) {
            String setOf4 = inputs.substring(i, i + lenght);
            char[] chars = new char[lenght];

            for (int j = 0; j < lenght; j++) {
                chars[j] = setOf4.charAt(j);
            }
            boolean founds = false;
            for (char character : chars) {
                if (lenght - setOf4.replaceAll(String.valueOf(character), "").length() > 1) {
                    founds = true;
                }
            }

            if(!founds){
                return i + lenght;
            }
        }
        return 0;
    }

}
