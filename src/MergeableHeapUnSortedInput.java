import java.util.HashSet;
import java.util.Set;

/**
 * same code for MergeableHeapUnSortedDisjointInput
 * MergeableHeapUnSortedDisjointInput class
 * added Set for checking in o(1) if a certain key is already added into the heap
 * since the input are in sorted order, no validation is needed.
 * Insert o(n)- add element so sorted linkedlist, full scan of the list in the worse case.
 * union - create in the same way as merge function  in merge-sort algorithem, earch iteration moving one pointer forward.
 * the complexity is o(n)
 * extract-minimum - extract the head of the list o(1). switching head o(1)
 * minimum - get head o(1)
 */
public class MergeableHeapUnSortedInput  extends MergeableHeap<Integer> {

    //set for verify if a key already exist in linkedlist, set is implemented using hashset.
    private Set<Integer> keysInHeap = new HashSet<Integer>();

    // double linkedlist, contains the element in sorted way.
    private DoubleLinkedList<Integer> linkedList = new DoubleLinkedList();

    public DoubleLinkedList<Integer> getLinkedList() {
        return linkedList;
    }

    /**
     * insert value to linkedlsit in sorted order,
     * iterate throw the list until the value is smaller then the current element, and change pointers.
     * complexity  o(n)
     * @param value
     */
    @Override
    public void insert(Integer value) {
        //value already exist
        if (keysInHeap.contains(value)) {
            return;
        }
        //add element so set
        this.keysInHeap.add(value);
        //list is empty
        if(this.linkedList.getHead() == null){
            this.linkedList.insertHead(value);
        }
        else {
            Node<Integer> tmp =this.linkedList.getHead();
            while ( tmp != null && value > tmp.getValue()){
                tmp = tmp.getNext();
            }
            //shoudl be added to tail
            if(tmp == null){
                this.linkedList.insertTail(value);
            }
            //this is the smallest element - shoudl be added to the head
            else if(tmp == this.linkedList.getHead()){
                this.linkedList.insertHead(value);
            }
            // add element and change pointers
            else {
                Node<Integer> insrtNode = new Node<Integer>(tmp, tmp.getPrev(), value);
                tmp.getPrev().setNext(insrtNode);
                tmp.setPrev(insrtNode);
            }

        }
    }
    /**
     * merge two linkedlist into single linkedlist, implemented similar  to the "merge" method in merge sort, o(n) complexity
     * @param linkedList2
     * @return
     */
    @Override
    public MergeableHeap<Integer> union(MergeableHeap<Integer> linkedList2) {
        MergeableHeapUnSortedInput mergeableHeapSortedToUnion = (MergeableHeapUnSortedInput) linkedList2;
        Node<Integer> pointer1 = this.linkedList.getHead();
        Node<Integer> pointer2 = mergeableHeapSortedToUnion.getLinkedList().getHead();
        MergeableHeapUnSortedInput unionResult = new MergeableHeapUnSortedInput();
        while (pointer1 != null && pointer2 != null) {
            if (pointer1.getValue() < pointer2.getValue()) {
                unionResult.insert(pointer1.getValue());
                pointer1 = pointer1.getNext();
            } else {
                unionResult.insert(pointer2.getValue());
                pointer2 = pointer2.getNext();
            }
        }
        while (pointer1 != null) {
            unionResult.insert(pointer1.getValue());
            pointer1 = pointer1.getNext();
        }
        while (pointer2 != null) {
            unionResult.insert(pointer2.getValue());
            pointer2 = pointer2.getNext();
        }

        return unionResult;
    }
    /**
     * get the minimum with out removing the element o(1) complexity.
     * the list is sorted so the min value located in the head
     * @return
     */
    @Override
    public Integer minimum() {
        if (linkedList.getHead() != null) {
            return linkedList.getHead().getValue();
        }
        return null;
    }
    /**
     * extract the head since the list is sorted, the min value is in the head.
     * @return
     */
    @Override
    public Integer extractMinimum() {
        if (linkedList.getHead() != null) {
            Node<Integer> tmp = linkedList.getHead();
            linkedList.delete(tmp);
            keysInHeap.remove(tmp.getValue());
            return tmp.getValue();
        }
        return null;
    }
}