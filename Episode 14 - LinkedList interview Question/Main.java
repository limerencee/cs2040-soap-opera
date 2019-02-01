import java.util.Scanner;

class Main {

    /*
    * preCond: head1 and head2 point to two non-empty linked lists with values sorted in ascending order.
    * postCond: return a reference pointing to the first node of the common nodes of the two given lists.
    *           If there is no common nodes, return null
    */
    static <T> Node<T> getMatches(Node<T> head1, Node<T> head2) {

        while(head1 != null && head2 != null) {
            if (head1.compareTo(head2) > 0) { //if one > two
                head2 = head2.getNext();
            } else if (head1.compareTo(head2) < 0) { //if one < two
                head1 = head1.getNext();
            } else { //if one == two
                return head1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        BasicLinkedList<Integer> one = new BasicLinkedList<>();
        BasicLinkedList<Integer> two = new BasicLinkedList<>();
        BasicLinkedList<Integer> common = new BasicLinkedList<>();
        one.add(1);
        one.add(3);
        one.add(5);
        two.add(2);
        two.add(3);
        two.add(5);
        System.out.println("BLL one: " + one.toString());
        System.out.println("BLL two: " + two.toString());
        common.add(getMatches(one.getHead(), two.getHead()));
        System.out.println("BLL Common: " + common.toString());
    }

}
