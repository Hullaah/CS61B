package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        var Ns = new AList<Integer>();
        var times = new AList<Double>();
        var opCounts = new AList<Integer>();
        for (int i = 1000; i <= 128_000 ; i *= 2) {
            var testingList = new AList<Integer>();
            long startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                testingList.addLast(j);
            }
            double timeTaken = (System.nanoTime() - startTime) / 1e9;
            Ns.addLast(i);
            times.addLast(timeTaken);
            opCounts.addLast(i);
        }
        printTimingTable(Ns, times, opCounts);
    }
}
