import java.util.HashSet;
import java.util.Set;


/**
 * MergeableHeapSortedInput class
 * added Set for checking in o(1) if a certain key is already added into the heap
 * since the input are in sorted order, no validation is needed.
 * Insert o(1)- add element to tail o(1), check if exist o(1)
 * union - create in the same way as merge function  in merge-sort algorithem, earch iteration moving one pointer forward.
 * the complexity is o(n)
 * extract-minimum - extract the head of the list o(1). switching head o(1)
 * minimum - get head o(1)
 */
public class MergeableHeapSortedInput extends MergeableHeap<Integer> {


    //set for verify if a key already exist in linkedlist, set is implemented using hashset.
    private Set<Integer> keysInHeap = new HashSet<Integer>();

    // double linkedlist, contains the element in sorted way.
    private DoubleLinkedList<Integer> linkedList = new DoubleLinkedList();


    public DoubleLinkedList<Integer> getLinkedList() {
        return linkedList;
    }


    /**
     * complexiy o(1) - values arrived in sorted order.
     * @param value
     */
    @Override
    public void insert(Integer value) {
        if(keysInHeap.contains(value)){
            return;
        }
        this.keysInHeap.add(value);
        this.linkedList.insertTail(value);



    }

    /**
     * merge two linkedlist into single linkedlist, implemented similar  to the "merge" method in merge sort, o(n) complexity
     * @param linkedList2
     * @return
     */
    @Override
    public MergeableHeap<Integer> union(MergeableHeap<Integer> linkedList2) {
        MergeableHeapSortedInput mergeableHeapSortedToUnion = (MergeableHeapSortedInput) linkedList2;
        Node<Integer> pointer1 = this.linkedList.getHead();
        Node<Integer> pointer2 = mergeableHeapSortedToUnion.getLinkedList().getHead();
        MergeableHeapSortedInput unionResult = new MergeableHeapSortedInput();
        while (pointer1 != null && pointer2 != null) {
            if (pointer1.getValue() < pointer2.getValue()) {
                unionResult.insert(pointer1.getValue());
                pointer1 = pointer1.getNext();
            } else {
                unionResult.insert(pointer2.getValue());
                pointer2 = pointer2.getNext();
            }
        }
        // add all the element that left, only one of them will be different that nulll
        while (pointer1!= null){
            unionResult.insert(pointer1.getValue());
            pointer1 = pointer1.getNext();
            }
        while (pointer2!= null){
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
        if(linkedList.getHead() != null){
            return linkedList.getHead().getValue();
        }
        return null;
    }


    /**
     * exreact the head since the list is sorted, the min value is in the head.
     * @return
     */
    @Override
    public Integer extractMinimum() {
        if(linkedList.getHead() != null){
            Node<Integer> tmp =linkedList.getHead();
            linkedList.delete(tmp);
            keysInHeap.remove(tmp.getValue());
            return tmp.getValue();
        }
        return null;
    }
}
