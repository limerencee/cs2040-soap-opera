import java.util.Arrays;
import java.util.LinkedList;

class Main {

    static void sort(LinkedList<Integer> list, boolean isOddIndex) {
        int temp = 0;
        if (list.isEmpty()) return; //base case
        else if (isOddIndex) { //add odd index to front
            temp = list.removeFirst();
            sort(list, !isOddIndex);
            list.addFirst(temp);
        } else { //add even index to back
            temp = list.removeFirst();
            sort(list, !isOddIndex);
            list.addLast(temp);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(9);
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(7);
        list.add(7);
        System.out.println("Before sort: " + Arrays.toString(list.toArray()));
        sort(list, true);
        System.out.println("After sort: " + Arrays.toString(list.toArray()));
    }

}
