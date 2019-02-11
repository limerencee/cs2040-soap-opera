/**
* BasicLinkedList structure that contains a method getHead() to get the
* head in the form of Node<T> instead of T (if using the LinkedList Java
* library).
*/

class BasicLinkedList<T> {

    private Node<T> _head;
    private int _size;

    /**
    * Constructor, initializes BasicLinkedList head Node to null.
    */
    public BasicLinkedList() {
        _head = null;
        _size = 0;
    }

    /**
    * Overloaded method add which takes in a Node<T> type to add to the back of this
    * BasicLinkedList. This implicitly means that if the input Node<T> is
    * pointing to other Node<T>, they will be added as well.
    * @param newNode - input Node<T> to be added to the back of this
    *                  BasicLinkedList.
    */
    public void add(Node<T> newNode) {
        if (_head == null) _head = newNode; //empty list, add newNode to be head.
        else {
            Node<T> currentNode = _head;
            Node<T> nextNode = _head.getNext();
            while (nextNode != null) { //iterate until last node
                currentNode = nextNode;
                nextNode = nextNode.getNext();
            }
            currentNode.setNext(newNode); //add to current last node's next
        }
    }

    /**
    * Returns the size of this BasicLinkedList.
    * @return int size of this BasicLinkedList object.
    */
    public int getSize() {
        _size = _head == null ? 0 : 1;
        Node<T> currentNode = _head;
        Node<T> nextNode = _head.getNext();
        while (nextNode != null) { //iterate until last node
            currentNode = nextNode;
            nextNode = nextNode.getNext();
            _size++;
        }
        return _size;
    }

    /**
    * Returns the head of this BasicLinkedList in Node<T> structure.
    * @return Node<T> reference to the head of this BasicLinkedList instance.
    */
    public Node<T> getHead() {
        return _head;
    }

    /**
    * Removes the head node of this BasicLinkedList.
    */
    public void removeFirst() {
        if (_head != null) {
            _head = _head.getNext();
        }
    }

    /**
    * Returns a read-able String of this BasicLinkedList in the following
    * format:
    * [T, T, ..., T]
    * @return String in the format: [T, T, ..., T] where T is the data
    *         in each Node<T>
    */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> currentNode = _head;
        Node<T> nextNode = _head.getNext();
        while (nextNode != null) {
            sb.append(currentNode.getData() + ", ");
            currentNode = nextNode;
            nextNode = nextNode.getNext();
        }
        sb.append(currentNode.getData() + "]");

        return sb.toString();
    }
}
