import java.util.Arrays;
import java.util.Scanner;

class Main {

    static int N;
    static String[] arr;

    /**
    * Reads an int N for number of user entered ints.
    * Initializes String arr[N] and store each user input in the order it was
    * entered. The array is then sorted by comparing elements lexicographically by:
    * (concat) a + b > (concat) b + a.
    * Time complexity: O(NlogN)
    * @return String containing the concatenated arr[].
    */
    static String run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.next();
        }

        Arrays.sort(arr, (x, y) -> (y+x).compareTo(x+y));

        return Arrays.toString(arr).replace("[", "").replace("]", "").replaceAll(", ", "");
    }


    public static void main(String[] args) {
        System.out.println(run());
    }

}
