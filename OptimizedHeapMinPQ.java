import java.util.*;

public class OptimizedHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private List<PriorityNode<T>> items;
    //helps with runtime at changePriority() and contains()
    private Map<T, Integer> itemToIndex;
    private int size;
    public static final int minIndex = 1;

    public OptimizedHeapMinPQ() {
        items = new ArrayList<>();
        itemToIndex = new HashMap<>();
        //This helps simplify the operations for Min Heap, null is fine, and so is any priority < 0
        items.add(new PriorityNode(null, -1));
    }

    //Convert List into Min Heap 
    //Make item as key and index as value
    //Runtime Analysis: Logarithmic
    public void add(T item, double priority) {
        if (this.contains(item)) {
            throw new IllegalArgumentException("Already contains " + item);
        }
        size++;
        itemToIndex.put(item, size);
        //first element is added at index 1, in this manner: [null, item1]
        items.add(new PriorityNode(item, priority));
        percolateUp(size);
    }

    private void percolateUp(int position) {
        while(position > 1 && comparePriority(getParentIndex(position), position)) {
            swap(position, getParentIndex(position));
            position = getParentIndex(position);
        }
    }

    private void percolateDown(int position) {
        while (getLeftChildIndex(position) <= size) {
            int leftChildIndex = getLeftChildIndex(position);
            if (leftChildIndex < size && comparePriority(leftChildIndex, leftChildIndex + 1)) {
                leftChildIndex++;
            }
            if (!comparePriority(position, leftChildIndex)) {
                break;
            }
            swap(position, leftChildIndex);
            position = leftChildIndex;
        }
    }

    
    //swap the parentNodeItem and the latestNodeItem in items
    private void swap(int parentNodeIndex, int latestNodeIndex) {
        PriorityNode<T> parentNode = items.get(parentNodeIndex);
        PriorityNode<T> latestNode = items.get(latestNodeIndex);
        //after using set the latestNode moves to parentNodeIndex
        items.set(parentNodeIndex, latestNode); 
        //after using set the parentNode moves to latestNodeIndex
        items.set(latestNodeIndex, parentNode); 
        //remove the former key and value for parentNode and latestNode
        itemToIndex.remove(parentNode);
        itemToIndex.remove(latestNode);
        //indexs and items are swapped in the itemToIndex Map as well
        itemToIndex.put(latestNode.item(), parentNodeIndex);
        itemToIndex.put(parentNode.item(), latestNodeIndex);
    }

    //helps keep the Min Heap invariant by comparing the priorities of nodes
    private boolean comparePriority(int parentNodeIndex, int latestNodeIndex) {
        return items.get(parentNodeIndex).priority() > items.get(latestNodeIndex).priority();
    }

    //return the value of the smallest item
    public T peekMin() {
        checkIfPQExists();
        //return items.get(1).item();
        return items.get(minIndex).item();
    }

    //remove and return the value of the smallest item
    public T removeMin() {
        checkIfPQExists();
        T min = peekMin();
        swap(minIndex, size);
        itemToIndex.remove(min);
        items.remove(size);
        size--;
        percolateDown(minIndex);
        return min;
    }

    //If size == 0 throws a NoSuchElementException, else does nothing
    private void checkIfPQExists(){
        if(size == 0) {
            throw new NoSuchElementException("No Priority Queue Exists");
        }
    }

    //changes the specified item's priority with the specified priority
    public void changePriority(T item, double priority) {
        if(!contains(item)){
            throw new NoSuchElementException("PQ does not contain:" + item);
        }
        PriorityNode<T> select = items.get(itemToIndex.get(item));
        select.setPriority(priority);
        //depending on priority we percolateUp if the priority is less (parentNode is larger the currentNode)
        int indexOfItem = itemToIndex.get(item);
        int parentIndex = getParentIndex(indexOfItem);
        if(comparePriority(parentIndex, indexOfItem)){
            percolateUp(indexOfItem);
        }
        else{
            percolateDown(indexOfItem);
        }
    }

    //check if T item is a key in itemToIndex Map
    public boolean contains(T item) {
        return itemToIndex.containsKey(item);
    }

    //getter method for size
    public int size() {
        return size;
    }
    
    //A bunch of private helper methods to quickly locate the appropriate index
    private int getLeftChildIndex(int parentIndex) { 
        return 2 * parentIndex;    
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getParentIndex(int childIndex) {
        //return childIndex / 2;
        return childIndex / 2;
    }

}
