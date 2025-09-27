package divideconquer;

public class Metrics {
    public int comparisons;
    public int maxDepth;
    private int depth;

    public void enter() {
        depth++;
        if (depth > maxDepth) maxDepth = depth;
    }

    public void exit() {
        depth--;
    }

    public void comp() {
        comparisons++;
    }

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
        depth = 0;
    }
}