import java.util.*;

public class HeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private final PriorityQueue<PriorityNode<T>> pq;

    public HeapMinPQ() {
        pq = new PriorityQueue<>(Comparator.comparingDouble(PriorityNode::priority));
    }

    public void add(T item, double priority) {
        if(!pq.contains(item)) {
            this.pq.add(new PriorityNode<>(item, priority));
        } else {
            throw new IllegalArgumentException("Already contains " + item);
        }
    }

    public boolean contains(T item) {
        for(PriorityNode<T> node : this.pq) {
            if (node.item().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public T peekMin() {
        if(this.pq.isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        return this.pq.peek().item();
    }

    public T removeMin() {
        if(this.pq.isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        return this.pq.poll().item();
    }

    public void changePriority(T item, double priority) {
        if(!this.contains(item)) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        this.pq.remove(new PriorityNode(item, 0));
        this.pq.add(new PriorityNode<>(item, priority));
    }

    public int size() {
        return this.pq.size();
    }
}

