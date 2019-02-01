import java.util.Comparator;

class Node<T> {

    private T _data;
    private Node<T> _nextNode;
    private Comparator _comparator;

    /**
    * Constructor to initalize an instance of Node <T>
    * Forces a comparator input and assumes that it is of the correct type.
    */
    public Node(T data, Comparator comparator) {
        _data = data;
        _nextNode = null;
        _comparator = comparator;
    }

    public T getData() {
        return _data;
    }

    public Node<T> getNext() {
        return _nextNode;
    }

    public void setData(T data) {
        _data = data;
    }

    public void setNext(Node<T> nextNode) {
        _nextNode = nextNode;
    }

    /**
    * Assumes that the comparator supplied is comparing the correct type.
    */
    @SuppressWarnings("unchecked")
    public int compareTo(Node<T> otherNode) {
        return _comparator.compare(this._data, otherNode.getData());
    }

}
