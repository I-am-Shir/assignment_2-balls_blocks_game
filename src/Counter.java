/**
 * a simple class that is used for counting things.
 */
public class Counter {
    private int counter;

    public Counter(int blockNum){
        this.counter = blockNum;
    }

    // add number to current count.
    void increase(int number) {
        this.counter += number;
    }

    // subtract number from current count.
    void decrease(int number) {
        this.counter -= number;
    }

    // get current count.
    int getValue() {
        return counter;
    }

}
