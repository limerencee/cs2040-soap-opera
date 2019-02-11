import java.util.Scanner;
import java.util.Stack;

class Main {

    static Scanner sc;
    static Stack<Integer> stack;
    static int[] list; //list of integers {1, 2, 3, 4, 5, 6}
    static int listIndex; //index to the list of integer above
    static StringBuilder output;

    /**
    * Method used to check each digit.
    * 1) Look at top of stack, if the digit matches, append "X" to output and pop().
    *    Return true.
    * 2) If no match from 1), check through the digit list {1, 2, 3, 4, 5, 6}
    *    until a match is found, using the current listIndex (0 <= listIndex < 6).
    *    2a) If the current list[listIndex] is not a match, push() and append "S"
    *        to the output.
    *    2b) If a match is found, append "SX" to the output and return true.
    * 3) If there is still no match, the requested permuation is impossible.
    *    Return false.
    *
    * @param check - The digit to check for.
    * @return true if current digit can be reached, false if permutation is impossible.
    */
    static boolean check(int check) {
        if (!stack.empty() && stack.peek() == check) {
            stack.pop();
            output.append("X");
            return true;
        } else {
            while (listIndex < 6) {
                int listElement = list[listIndex++];
                if (listElement == check) {
                    output.append("SX");
                    return true;
                }
                stack.push(listElement);
                output.append("S");
            }
            return false;
        }
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        stack = new Stack<>();
        list = new int[]{1, 2, 3, 4, 5, 6};
        listIndex = 0;
        output = new StringBuilder();

        String[] inputString = sc.nextLine().split("");
        for (int i = 0; i < 6; i++) {
            int currentDigit = Integer.parseInt(inputString[i]);
            if (!check(currentDigit)) break;
        }

        if (stack.empty()) System.out.println(output.toString());
        else System.out.println("Impossible permutation");

    }

}
