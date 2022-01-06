import javax.print.DocFlavor;

/**
 * Implement double linkedlist, each node contains pointer to the next and prev value.
 * @param <T>
 */
public class DoubleLinkedList<T> {

    //the begining of the list
    private Node<T> head;
    //the end of the list
    private Node<T> tail;


    /**
     * consutructor for empty DoubleLinkedList;
     */
    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }


    /**
     * insert value to add. inc case the list is empty(head and tail null), we should also change the tail
     * @param value
     */
    public void insertHead(T value){
        Node<T> node = new Node(this.head, null, value);
        this.head = node;

        if(this.tail == null){
            this.tail = node;
        }
        else {
            this.head.getNext().setPrev(this.head);

        }

    }
    /**
     * insert value to add. inc case the list is empty(head and tail null), we should also change the head
     * @param value
     */
    public void insertTail(T value){
        Node<T> node = new Node(null, this.tail, value);
        this.tail = node;
        if(this.head == null){
            this.head = this.tail;
        }
        else {
            this.tail.getPrev().setNext(node);
        }

    }
    /**
     * delete node. we should change the pointer of prev.next() and next.prev
     * special cases if case the node is head or tail.
     * @param value
     */
    public void delete(Node<T> nodeToDelete){
        if(nodeToDelete == this.head && nodeToDelete == this.tail){
            this.head = null;
            this.tail = null;
        }
        else if(nodeToDelete == this.head){
            this.head = head.getNext();
            this.head.setPrev(null);

        }
        else if(nodeToDelete == this.tail){
            this.tail = tail.getPrev();
            this.tail.setNext(null);
        }
        else {
            nodeToDelete.getPrev().setNext(nodeToDelete.getNext());
        }
        return;
    }

    /**
     * ged head of list
     * @return
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * get head of list.
     * @return
     */
    public Node<T> getTail() {
        return tail;
    }

    /**
     * simple to string
     * @return
     */
    @Override
    public String toString() {
        Node<T> tmp = this.head;
        String str = "** current Linkedlist ** \n";
        while (tmp != null){

            str += tmp.getValue() + " - >";
            tmp = tmp.getNext();
        }
        return str;
    }
    /**
     * simple to string, from tail to head, created for making sure the pointer are good
     * @return
     */
    public String toStringPrev() {
        Node<T> tmp = this.tail;
        String str = "** current Linkedlist ** \n";
        while (tmp != null){

            str += tmp.getValue() + " - >";
            tmp = tmp.getPrev();
        }
        return str;
    }

}
