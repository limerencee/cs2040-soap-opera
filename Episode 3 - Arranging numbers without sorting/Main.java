import java.util.Scanner;

class Main {

    static int[] digits; //[10], stores the count of each digit that appeared from input

    /**
    * Initializes digits[10], takes in 1 line of input which is assumed to be a 4-digit int.
    * Increment digits[n] each time n appears in the input.
    * Check if input is valid (i.e no 4 same digits) and call outputMinMax() to resolve output.
    */
    static void initializeDigits() {
        digits = new int[10];
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        for (int i = 0; i < 4; i++) {
            int n = line.charAt(i) - '0';
            digits[n]++;
        }

        if (isValid()) {
            outputMinMax();
        } else {
            System.out.println("Not valid.");
            return;
        }
    }


    /**
    * Check if there are any digits that occured 4 times.
    * @return false if any digit has a count of 4, true otherwise.
    */
    static boolean isValid() {
        for (int i : digits) {
            if (i == 4) return false;
        }
        return true;
    }


    /**
    * To find the largest integer, just iterate through the count array from
    * right to left. Decrement the count each time the digit is used.
    * To find the smallest integer, just reverse the largest integer above.
    */
    static void outputMinMax() {
        StringBuilder max = new StringBuilder(4);
        for (int i = 9; i >= 0; i--) {
            while (digits[i] > 0) {
                max.append(i);
                digits[i]--;
            }
        }

        System.out.println("Maximum: " + max.toString());
        System.out.println("Minimum: " + max.reverse().toString());
    }

    public static void main(String[] args) {
        initializeDigits();
    }

}
