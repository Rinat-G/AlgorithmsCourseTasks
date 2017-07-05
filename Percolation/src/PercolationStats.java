import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] trialsResults;
    private double mean = 0.0;
    private double stddev = 0.0;

    public PercolationStats(int n, int trials) {  // perform trials independent experiments on an n-by-n grid
        constructorArgValidate(n, trials);
        trialsResults = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            //System.out.println("Threshold: " + (percolation.numberOfOpenSites() / (double)(n*n)));
            trialsResults[i] = percolation.numberOfOpenSites() / (double) (n * n);

        }

    }

    public double mean() {                    // sample mean of percolation threshold
        mean = StdStats.mean(trialsResults);
        return mean;
    }

    public double stddev() {                       // sample standard deviation of percolation threshold
        stddev = StdStats.stddev(trialsResults);
        return stddev;

    }

    public double confidenceLo() {                // low  endpoint of 95% confidence interval
        if (mean == 0.0) mean = mean();
        if (stddev == 0.0) stddev = stddev();
        return mean - (1.96 * stddev) / Math.sqrt(trialsResults.length);

    }

    public double confidenceHi() {
        if (mean == 0.0) mean = mean();
        if (stddev == 0.0) stddev = stddev();
        return mean + (1.96 * stddev) / Math.sqrt(trialsResults.length);

    }

    public static void main(String[] args) {       // test client (described below)
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.printf("mean                    = %.16f%n", percolationStats.mean());
        System.out.printf("stddev                  = %.16f%n", percolationStats.stddev());
        System.out.printf("95%% confidence interval = [%.16f, %.16f]%n", percolationStats.confidenceLo(), percolationStats.confidenceHi());

    }

    private void constructorArgValidate(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Arguments can not be less or equals 0");
    }

}

