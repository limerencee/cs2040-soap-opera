import java.util.Scanner;

class Main {

    static int N;
    static int[] occurrences;
    static int[] arr;
    static int[] digits;

    /**
    * Reads input, first takes in an int as N.
    * Then read N amount of ints to initialize arr[N] and occurrences[N].
    * Terminate program the moment the input contains 0 or is not single digit.
    * @return true if valid user input, false otherwise.
    */
    static boolean readInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        occurrences = new int[N];
        arr = new int[N];
        digits = new int[10];
        for (int i = 0; i < N; i++) {
            int input = sc.nextInt();
            if (input == 0) return false;
            if (input / 10 != 0) return false;
            arr[i] = input;
            digits[input]++;
        }

        if (isValid()) {
            return checkOccurrences();
        }

        return false;
    }

    /**
    * Checks if user input number are valid.
    * - No repeated digits
    * @return true if input number is valid, false otherwise.
    */
    static boolean isValid() {
        for (int i = 0; i < 10; i++) {
            if (digits[i] > 1) return false;
        }
        return true;
    }


    /**
    * Loops through arr[] N times, starting from index 0. Increment occurrences[i]
    * each time where i = (currentIndex + arr[currentIndex]) % N, for wrap-around.
    * Time complexity: O(N)
    * @return true if input is RunAround number, false otherwise.
    */
    static boolean checkOccurrences() {

        int currentIndex = 0;
        int currentValue = arr[0];
        for (int i = 0; i < N; i++) {
            int nextIndex = (currentIndex + currentValue) % N;
            occurrences[nextIndex]++;
            currentIndex = nextIndex;
            currentValue = arr[nextIndex];
        }

        for (int i : occurrences) {
            if (i != 1) return false;
        }

        return true;
    }


    public static void main(String[] args) {
        System.out.println(readInput());
    }
}
