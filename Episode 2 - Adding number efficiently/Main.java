import java.util.Scanner;

class Main {

    static Scanner sc;
    static int N;
    static long[] indexOfSum;

    /**
    * Starts by reading a int N for number of elements.
	* Store each element as long, since it could get very big.
    * Initialize indexOfSum[i] while i < N.
    */
    static void initializeArray() {
        N = sc.nextInt();
        indexOfSum = new long[N];
        indexOfSum[0] = sc.nextLong();
        for (int i = 1; i < N; i++) {
            indexOfSum[i] = sc.nextLong() + indexOfSum[i - 1];
        }
    }

    /**
    * Expects user input for indices i and j.
    * -1 as first input to end program.
    * Otherwise, output sum between the indices i and j.
    */
    static void handleInput() {
        while (true) {
            int i = sc.nextInt();
            if (i == -1) break; //-1 to end program
            int j = sc.nextInt();
            if (i >= N || j >= N) break; //out of bounds
            if (i == 0) {
                System.out.println(indexOfSum[j]);
            } else {
                System.out.println(indexOfSum[j] - indexOfSum[i - 1]);
            }
        }
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        initializeArray();
        handleInput();
    }

}
