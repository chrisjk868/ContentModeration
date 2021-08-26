import java.util.*;

public class UnsortedArrayMinPQ<T> implements ExtrinsicMinPQ<T> {
    private List<PriorityNode<T>> items;

    public UnsortedArrayMinPQ() {
        items = new ArrayList<>();
    }

    // Insert an item with the given priority value
    public void add(T item, double priority) {
        if(!items.contains(item)) {
            this.item.add(new PriorityNode<> (item, priority));
        } else {
            throw new IllegalArgumentException("Already contains " + item);
        }

    }
    
    // Check if array contains items
    // return true if found 
    // return false if not found
    public boolean contains(T item) {
        if (items.contains(item)){
            return true 
        } else {
            return false;
        }
    }

    // Return the node with the smallest priority value
    // But does not remove it
    public T peekMin() {
        // check the size of array 
        if (this.size == 0) {
            throw new NoSuchElementException("Array is empty");
        }

        PriorityNode<T> temp;
        double minPriority;
        // check this part 
        for (i = 1; i < items.size(); i++){
            if (temp.get(i).priority() < minPriority){
                minPriority = temp.priority;
            }
        }
        return minPriority;
    }

    // Remove the node witht the smallest prioirty value 
    public T removeMin() {
        // pre: check the size of array 
        if (this.size == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        
        T min = peekMin();
        // this.items.remove(new PriorityNode(item, 0));
        for (int i = 0; i < items.size(); i++){
            items.get(i);
            if (items.get(i).item.equals(min)){
                items.remove(i);
            }
        }
    }

    /**
    * Change the node in the array with the given item  
    * to have the given priority
    **/
    public void changePriority(T item, double priority) {
        if(!this.contains(item)) {
            throw new NoSuchElementException("Array does not contain " + item);
        }

        this.items.remove(new PriorityNode(item, 0));
        this.items.add(new PriorityNode<>(item, priority));

    }

    // return the size of array 
    public int size() {
        return this.items.size();
    }
}
