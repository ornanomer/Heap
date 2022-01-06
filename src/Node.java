/**
 * simple node, pointer to prev and next
 * @param <T>
 */
public class Node<T> {

    private Node next;
    private Node prev;
    private T value;

    public Node(Node next, Node prev, T value) {
        this.next = next;
        this.prev = prev;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
