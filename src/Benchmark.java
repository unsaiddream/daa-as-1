import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static final int[] SIZES = {
            1_000,
            10_000,
            100_000,
            1_000_000
    };

    private static final int RUNS = 5;

    private static final Random RANDOM = new Random();


    public static void main(String[] args) throws IOException {

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter("results.csv"))) {

            // Заголовок CSV
            writer.println(
                    "algorithm,input,n,time_ms,comparisons,max_depth"
            );

            for (int n : SIZES) {

                runSortBenchmark(writer, "mergeSort", "random", n);
                runSortBenchmark(writer, "mergeSort", "sorted", n);
                runSortBenchmark(writer, "mergeSort", "duplicates", n);

                runSortBenchmark(writer, "quickSort", "random", n);
                runSortBenchmark(writer, "quickSort", "sorted", n);
                runSortBenchmark(writer, "quickSort", "duplicates", n);
            }
        }

        System.out.println("benchmark finished");
        System.out.println("results saved to results.csv");
    }


    private static void runSortBenchmark(
            PrintWriter writer,
            String algorithm,
            String inputType,
            int n
    ) {

        long[] times = new long[RUNS];

        long comparisons = 0;
        int maxDepth = 0;

        for (int run = 0; run < RUNS; run++) {

            int[] arr = createInput(n, inputType);

            Metrics metrics = new Metrics();

            long start = System.nanoTime();

            if (algorithm.equals("mergeSort")) {

                MergeSort.sort(arr, metrics);

            } else if (algorithm.equals("quickSort")) {

                QuickSort.sort(arr, metrics);
            }

            long end = System.nanoTime();

            metrics.timeNs = end - start;

            times[run] = metrics.timeNs;

            comparisons = metrics.comparisons;
            maxDepth = metrics.maxDepth;
        }


        Arrays.sort(times);

        long medianTimeNs = times[RUNS / 2];

        double timeMs = medianTimeNs / 1_000_000.0;


        writer.printf(
                "%s,%s,%d,%.3f,%d,%d%n",
                algorithm,
                inputType,
                n,
                timeMs,
                comparisons,
                maxDepth
        );


        System.out.printf(
                "%s | %s | n=%d | %.3f ms%n",
                algorithm,
                inputType,
                n,
                timeMs
        );
    }


    private static int[] createInput(
            int n,
            String inputType
    ) {

        int[] arr = new int[n];

        switch (inputType) {

            case "random":

                for (int i = 0; i < n; i++) {
                    arr[i] = RANDOM.nextInt();
                }

                break;


            case "sorted":

                for (int i = 0; i < n; i++) {
                    arr[i] = i;
                }

                break;


            case "duplicates":

                for (int i = 0; i < n; i++) {
                    arr[i] = RANDOM.nextInt(10);
                }

                break;


            default:
                throw new IllegalArgumentException(
                        "unknown input type: " + inputType
                );
        }

        return arr;
    }
}