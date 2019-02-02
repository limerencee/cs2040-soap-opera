import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

class Main {

    /*
    * preCond: head1 and head2 point to two non-empty linked lists.
    * postCond: return a reference pointing to the first node of the common nodes of the two given lists.
    *           If there is no common nodes, return null
    */
    static <T> Node<T> getMatches(Node<T> head1, Node<T> head2) {
        Stack<Node<T>> one = new Stack<>();
        Stack<Node<T>> two = new Stack<>();

        while (head1 != null) {
            one.push(head1);
            head1 = head1.getNext();
        }

        while (head2 != null) {
            two.push(head2);
            head2 = head2.getNext();
        }

        Node<T> commonStart = null;
        while (one.peek() == two.peek()) { //.equals() work as well.
            commonStart = one.pop();
            two.pop();
        }

        return commonStart;
    }

    public static void main(String[] args) {
        BasicLinkedList<Integer> one = new BasicLinkedList<>();
        BasicLinkedList<Integer> two = new BasicLinkedList<>();
        BasicLinkedList<Integer> common = new BasicLinkedList<>();
        Node<Integer> a = new Node<>(4, Comparator.naturalOrder());
        Node<Integer> b = new Node<>(4, Comparator.naturalOrder());
        Node<Integer> c = new Node<>(2, Comparator.naturalOrder());
        Node<Integer> d = new Node<>(7, Comparator.naturalOrder());
        Node<Integer> e = new Node<>(3, Comparator.naturalOrder());
        Node<Integer> f = new Node<>(8, Comparator.naturalOrder());
        Node<Integer> g = new Node<>(1, Comparator.naturalOrder());
        one.add(a);
        one.add(b);
        one.add(c);
        one.add(d);
        one.add(e);
        two.add(f);
        two.add(g);
        two.add(d);
        System.out.println("BLL one: " + one.toString());
        System.out.println("BLL two: " + two.toString());
        common.add(getMatches(one.getHead(), two.getHead()));
        System.out.println("BLL Common: " + common.toString());
    }

}
