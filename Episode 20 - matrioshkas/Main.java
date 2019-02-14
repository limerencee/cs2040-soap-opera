import java.util.Scanner;
import java.util.Stack;

class Main {

    static Stack<Integer> stack;
    static Stack<Integer> limit;

    /**
    * Method to push input into the stack. Only called when the input is negative signed.
    * Also saves the current input into the limit stack, for tracking when the doll will overflow.
    * @param input - negative integer to push onto the stack, signalling the opening of the input-sized doll.
    * @return true if added to stack successfully, false if input exceeds current open doll size.
    */
    static boolean pushToStack(int input) {
        if (!stack.empty() && stack.peek() > input) return false; //attempting to add a larger element than the current stack top
        stack.push(input);
        limit.push(Math.negateExact(input));
        return true;
    }

    /**
    * Method to pop from the stack. Only called when the input is positive signed.
    * Checks that the current top of stack matches the input size. If it does not match, return
    * false immediately as there is a nesting problem.
    * Otherwise, move on to update the top of the limit stack.
    * New limit top = Old Limit top - completed doll size.
    * @param input - positive integer to check against the top of the stack.
    * @return true if doll is closed successfully, false if there is a nesting problem.
    */
    static boolean popFromStack(int input) {
        int topElement = stack.pop();
        int match = Math.negateExact(input);
        if (match != topElement) return false; //nesting problem

        int currentSize = limit.pop();
        if (!limit.empty()) limit.push(limit.pop() - input); //update current-level doll capacity remaining
        return true;
    }

    /**
    * Method to iterate through a test case, supplied with an input String[] of integers.
    * Calls the pushToStack() and popToStack() methods to check for violation of incorrect size
    * and incorrect nesting. If both are successful, check the limit stack to see if the current
    * doll has been overfilled.
    * @param testWords - String[] of integers from the test case.
    * @return true if test case is valid, false otherwise (any of the 3 errors above).
    */
    static boolean performTestCase(String[] testWords) {
        for (int i = 0; i < testWords.length; i++) {
            int input = Integer.parseInt(testWords[i]);
            if (input < 0 && !pushToStack(input)) return false;
            else if (input > 0 && !popFromStack(input)) return false;

            //after each input, check if the dolls have been overfilled.
            //i.e the n1+n2+...+nr < m property.
            //if the new limit top is <= 0, the property has been violated.
            if (!limit.empty() && limit.peek() <= 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) { //loop through each test case
            stack = new Stack<>();
            limit = new Stack<>();
            String[] testWords = sc.nextLine().split(" ");
            if (performTestCase(testWords)) System.out.println(":-) Matrioshka!");
            else System.out.println(":-( Try again.");
        }
    }

}
