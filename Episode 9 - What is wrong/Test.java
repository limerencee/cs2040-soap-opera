public class Test {

    //Add static keyword to vars a b and method add() since it is being accessed
    //from static main() directly.
    private static int a = 0, b = 0;

    private static int add() {
        return a + b;
    }

     public static void main(String[] args) {
        a = 2;
        b = 3;
        System.out.println(add());
    }

}
