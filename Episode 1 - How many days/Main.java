import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Date d1 = new Date(sc.nextLine());
        Date d2 = new Date(sc.nextLine());
        System.out.println(d1.daysAway(d2));
    }

}
