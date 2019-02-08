import java.util.Comparator;
import java.util.Scanner;

class Main {

    /*
    * preCond: head1 and head2 point to two non-empty linked lists.
    * postCond: return a reference pointing to the first node of the common nodes of the two given lists.
    *           If there is no common nodes, return null
    */
    static <T> Node<T> getMatches(BasicLinkedList<T> one, BasicLinkedList<T> two) {
        int oneCurrentSize = one.getSize(); //for this question, ignore this traversal to get the size
        int twoCurrentSize = two.getSize(); //since it is a given. this method call is for demo purposes only.
        Node<T> oneCurrent = one.getHead();
        Node<T> twoCurrent = two.getHead();

        while (oneCurrentSize > 0 && twoCurrentSize > 0) {
            if (oneCurrentSize > twoCurrentSize) {
                oneCurrent = oneCurrent.getNext();
                oneCurrentSize--;
            } else if (oneCurrentSize < twoCurrentSize) {
                twoCurrent = twoCurrent.getNext();
                twoCurrentSize--;
            } else {
                if (oneCurrent == twoCurrent) return oneCurrent;
                oneCurrent = oneCurrent.getNext();
                twoCurrent = twoCurrent.getNext();
                oneCurrentSize--;
                twoCurrentSize--;
            }
        }

        return null; //no shared nodes
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
        common.add(getMatches(one, two));
        System.out.println("BLL Common: " + common.toString());
    }

}
