import java.util.Scanner;

class Main {

    /**
    * Reads in user input N and check if it's valid (odd),
    * and generates the Magic Square to print.
    * @return Read-able magic square tiles String.
    */
    static String initSquare() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if (N % 2 == 0) return "N must be an even number.";

        Square magicSquare = new Square(N);
        magicSquare.generate();
        return magicSquare.toString();
    }

    public static void main(String[] args) {
        System.out.println(initSquare());
    }

}
