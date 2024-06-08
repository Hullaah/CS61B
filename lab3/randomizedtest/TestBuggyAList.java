package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        var correctList = new AListNoResizing<Integer>();
        var buggyList = new BuggyAList<Integer>();
        for (int i = 0; i < 3; i++) {
            correctList.addLast(i);
            buggyList.addLast(i);
        }

        assertEquals(correctList.size(), buggyList.size());

        assertEquals(correctList.removeLast(), buggyList.removeLast());
        assertEquals(correctList.removeLast(), buggyList.removeLast());
        assertEquals(correctList.removeLast(), buggyList.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(L.size(), B.size());
            } else if (operationNumber == 2) {
                assertEquals(L.size(), B.size());
                if (L.size() != 0 && B.size() != 0) {
                    assertEquals(L.removeLast(), B.removeLast());
                }
            } else {
                assertEquals(L.size(), B.size());
                if (L.size() != 0 && B.size() != 0) {
                    assertEquals(L.getLast(), B.getLast());
                }
            }
        }
    }
}
