import java.util.Scanner;

class Main {

    /**
    * Time Complexity: O(N)
    *
    * Takes in an input N to indicate the number of elements in the input array.
    * Takes in N integers afterwards and create int[] forward and backward which
    * contains the cumulative sums from the left and right. Check if there is an
    * index i where forward[i] == backward[i]. If it exists means the input array
    * can have a balanced sum left and sum right.
    */
    static boolean run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] forward = new int[N + 1];
        int[] backward = new int[N + 1];

        //Initialize forward[N], starting from index 1.
        //forward[N] is the cumulative sum of all integers on the left of i,
        //where i is the index of current element.
        for (int i = 1; i <= N; i++) {
            int next = sc.nextInt();
            forward[i] = next + forward[i - 1];
        }

        //Initialize backward[N], starting from index 0.
        //backward[N] is the cumulative sum of all integers on the right of i,
        //where i is the index of the current element.
        for (int i = 0; i <= N; i++) {
            backward[i] = forward[N] - forward[i];
        }

        //If forward[i] == backward[i], it means that the input array can be
        //split into 2 balanced sum sides.
        for (int i = 0; i <= N; i++) {
            if (forward[i] == backward[i]) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(run());
    }

}
