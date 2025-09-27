
## Algorithms
1. **MergeSort** - O(n log n)
2. **QuickSort** - O(n log n) average
3. **Deterministic Select** - O(n) worst-case
4. **Closest Pair** - O(n log n)

## How to Run
```bash
# Compile
javac src/*.java

# Run tests
java -cp src AlgorithmTest

# Benchmark
java -cp src Benchmark mergesort 1000
java -cp src Benchmark quicksort 1000
java -cp src Benchmark select 1000
java -cp src Benchmark closest 1000
