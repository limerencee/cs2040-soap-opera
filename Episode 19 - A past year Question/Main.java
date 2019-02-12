import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

class Main {

    static Integer[] messy;
    static Queue<Integer> queue;
    static Stack<Integer> stack;

    /**
    * Main idea: store reference to first element, poll() the first element to
    * decide if add() to back of the queue or push() onto temp holding stack.
    * Iterate the queue until the first element is back at the front.
    * add() the holding element back to the back and it's done.
    *
    * Deciding if add() to queue or push() to stack for current holding element:
    *   a) if current holding element is bigger than next element in queue,
    *      push() to stack and move to next element in queue with poll().
    *   B) otherwise, current element is in order. add() to back of the queue.
    *       bi) if the stack is not empty, pop() it and assign current to the
    *           popped element instead of poll() for the next current element.
    *
    */
    static void iterateList() {
        int first = queue.peek(); //store reference to starting element
        int current = queue.poll(); //poll first element in queue to start checking with the next

        while (!queue.isEmpty() && queue.peek() != first) {
            if (current > queue.peek()) stack.push(current);
            else {
                queue.add(current);
                if (!stack.empty()) {
                    current = stack.pop();
                    continue;
                }
            }
            current = queue.poll();
        }

        queue.add(current); //add back holding element
        System.out.println("Fixed Queue: " + Arrays.toString(queue.toArray()));
    }


    public static void main(String[] args) {
        messy = new Integer[]{1, 4, 3, 2, 5, 7, 6, 9, 8, 10};
        queue = new ArrayDeque<>(Arrays.asList(messy));
        stack = new Stack<>();
        System.out.println("Messy Queue: " + Arrays.toString(queue.toArray()));
        iterateList();
    }

}
