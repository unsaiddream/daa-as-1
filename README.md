# DAA Assignment 1 — Divide and Conquer

Implementation and analysis of three divide-and-conquer algorithms:

- MergeSort
- QuickSort
- QuickSelect

The project also includes performance metrics, benchmark tests, JUnit 5 tests and experimental results.

## Requirements

- Java 17+
- Maven

Check installed versions:

```bash
java -version
mvn -version
```

## Project Structure

```text
src/
├── main/
│   └── java/
│       ├── MergeSort.java
│       ├── QuickSort.java
│       ├── QuickSelect.java
│       ├── Metrics.java
│       └── Benchmark.java
│
└── test/
    └── java/
        ├── MergeSortTest.java
        ├── QuickSortTest.java
        └── QuickSelectTest.java

plots/
├── time_vs_n.png
├── depth_vs_n.png
└── ratio_vs_n.png

pom.xml
results.csv
REPORT.md
README.md
```

## Build

Compile the project with:

```bash
mvn clean compile
```

## Run Tests

Run all JUnit 5 tests:

```bash
mvn clean test
```

The tests check:

- sorting correctness against `Arrays.sort`;
- random arrays;
- empty arrays;
- one-element arrays;
- arrays with equal elements;
- already sorted arrays;
- QuickSort recursion depth;
- QuickSelect results against `sorted[k]`.

## Run Benchmark

First compile the project:

```bash
mvn clean compile
```

Then run:

```bash
java -cp target/classes Benchmark
```

The benchmark tests input sizes:

```text
1,000
10,000
100,000
1,000,000
```

with three input types:

```text
random
sorted
duplicates
```

Each case is executed 5 times and the median execution time is saved.

The benchmark creates:

```text
results.csv
```

with the columns:

```text
algorithm,input,n,time_ms,comparisons,max_depth
```

## Algorithms

### MergeSort

MergeSort uses one reusable helper array for the whole sorting process.

Subarrays with 15 or fewer elements are sorted using Insertion Sort.

### QuickSort

QuickSort uses:

- random pivot selection;
- 3-way partitioning;
- recursion only on the smaller partition;
- a loop for the larger partition.

This keeps recursion depth small and handles duplicate values efficiently.

### QuickSelect

QuickSelect finds the k-th smallest element, where `k` starts from 0.

After partitioning, it continues only in the part containing position `k`.

## Metrics

The algorithms collect:

- number of comparisons;
- maximum recursion depth;
- execution time.

Execution time is measured using `System.nanoTime()`.

## Results

Benchmark results are stored in:

```text
results.csv
```

The generated plots are:

- `plots/time_vs_n.png`
- `plots/depth_vs_n.png`
- `plots/ratio_vs_n.png`

Full analysis of the results and asymptotic complexity is available in `REPORT.md`.