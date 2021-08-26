// javac heapTest.java && java heapTest; rm *.class
public class heapTest {
    public static void main(String[] args) {
        System.out.println("This is a small testing file");
        ExtrinsicMinPQ<String> pq = new OptimizedHeapMinPQ<>();

        // Build up a basic heap
        pq.add("1", 1.0);
        pq.add("2", 2.0);
        pq.add("3", 3.0);

        // Call methods to test
        pq.changePriority("3", 0.0);

        // See what your implementation does
        System.out.println("peekMin: " + pq.peekMin());
    }
}
