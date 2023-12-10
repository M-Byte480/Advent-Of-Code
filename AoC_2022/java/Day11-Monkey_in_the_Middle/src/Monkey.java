import java.math.BigInteger;
import java.util.ArrayList;

public class Monkey {
    private int number;
    private ArrayList<Long> items = new ArrayList<>();
    private String operation;
    private String trueStatement;
    private String falseStatement;
    private String test;
    private long inspect = 0;
    public Monkey(int number) {
        this.number = number;
    }

    public void setFalseStatement(String falseStatement) {
        this.falseStatement = falseStatement;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setTrueStatement(String trueStatement) {
        this.trueStatement = trueStatement;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Long> getItems() {
        return items;
    }

    public String getOperation() {
        return operation;
    }

    public String getTrueStatement() {
        return trueStatement;
    }

    public String getFalseStatement() {
        return falseStatement;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public long getInspect() {
        return inspect;
    }

    public void increaseInspect(){
        this.inspect++;
    }
}
