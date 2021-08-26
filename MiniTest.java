import java.io.*;
import java.util.*;

public class ModeratorMultiTest2 {
    public static void main(String[] args){
        smallTest2();
    }
    public static void smallTest(){
        String[] keys = {"a", "b", "c", "d", "e"};
        Double[] weights = {1.0, 2.0, 3.0, 4.0, 5.0};
        ExtrinsicMinPQ<String> referencePQ = new DoubleMapMinPQ<String>();
        ExtrinsicMinPQ<String> ourPQ = new OptimizedHeapMinPQ<String>();
        for (int i = 0; i < keys.length; i++) {
            referencePQ.add(keys[i], weights[i]);
            ourPQ.add(keys[i], weights[i]);
        }
    }

    public static void smallTest2(){
    String[] keys = {"a", "b", "c", "d", "e"};
    Double[] weights = {1.0, 2.0, 3.0, 4.0, 5.0};
    ExtrinsicMinPQ<String> referencePQ = new DoubleMapMinPQ<String>();
    ExtrinsicMinPQ<String> ourPQ = new OptimizedHeapMinPQ<String>();
    for (int i = 0; i < keys.length; i++) {
        referencePQ.add(keys[i], weights[i]);
        ourPQ.add(keys[i], weights[i]);
    }
    // if testing change priority:
    // referencePQ.changePriority("e", 0.0);
    // ourPQ.changePriority("e", 0.0);
    for (int i = 0; i < keys.length; i++) {
        String refResult = referencePQ.removeMin();
        String ourResult = ourPQ.removeMin();
        System.out.println("ref = " + refResult + " ours = " + ourResult);
    }
}
}
