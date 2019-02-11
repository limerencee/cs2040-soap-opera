import java.util.Comparator;
import java.util.Scanner;

class Main {

    /*
    * preCond: head1 point to two non-empty linked lists.
    * postCond: return a reference pointing to the middle node of the supplied list
    */
    static <T> Node<T> getMiddle(BasicLinkedList<T> one) {
        Node<T> twoN = one.getHead();
        Node<T> n = one.getHead();

        //Maintain 2 pointers, 1 points to N-th node and 1 points to the 2N-th node.
        //When 2N-th node reaches the end, return the first pointer (N-th node).
        while (twoN != null) {
            twoN = twoN.getNext();
            if (twoN == null) break;

            twoN = twoN.getNext();
            n = n.getNext();
        }

        return n;
    }

    public static void main(String[] args) {
        BasicLinkedList<Integer> one = new BasicLinkedList<>();
        Node<Integer> a = new Node<>(4, Comparator.naturalOrder());
        Node<Integer> b = new Node<>(4, Comparator.naturalOrder());
        Node<Integer> c = new Node<>(2, Comparator.naturalOrder());
        Node<Integer> d = new Node<>(7, Comparator.naturalOrder());
        Node<Integer> e = new Node<>(3, Comparator.naturalOrder());
        one.add(a);
        one.add(b);
        one.add(c);
        one.add(d);
        one.add(e);
        System.out.println("BLL one: " + one.toString());
        System.out.println("Middle: " + getMiddle(one).getData());
    }

}
