/**
 * simple abstract class that each MergeableHeap should extend, the type of this class is generic,
 * because MergeableHeap may be in use for different type, sinse the basic data-type is DoubleLinkedList, getter method had added
 * @param <T>
 */
public abstract class MergeableHeap<T> {
    abstract public DoubleLinkedList getLinkedList();
    abstract public void insert(T value);
    abstract public MergeableHeap<T> union( MergeableHeap<T> value);
    abstract public T minimum();
    abstract public T extractMinimum();



}
