import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;




/******************************************************************************
 *  Name:    Kevin Wayne
 *  Login:   wayne
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner Login:   N/A
 *  Partner Precept: N/A
 *
 *  Compilation:  javac-algs4 Percolation.java
 *  Execution:    java-algs4 Percolation
 *  Dependencies: StdIn.java StdRandom.java WeightedQuickUnionUF.java
 *
 *  Description:  Modeling Percolation like a boss. woot. woot.
 ******************************************************************************/
public class PercolationStats {
    /**
     * Threshold values in array for calculation.
     */
    private double[] thresholds;
    /**
     * Number of trials.
     */
    private double t;
    /**
     * Mean value of all thresholds.
     */
    private double mean;
    /**
     * Standard deviation of threshold values.
     */
    private double dev;
    /**
     * Low confidence interval.
     */
    private double lo;
    /**
     * High confidence interval.
     */
    private double hi;
    /**
     * Constant value for calculation.
     */
    private final double constant = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and t must be bigger than 0");
        }
        // Perform trials independent experiments on an n-by-n grid

        thresholds = new double[trials];
        t = trials;

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                int newx = StdRandom.uniform(1, n + 1);
                int newy = StdRandom.uniform(1, n + 1);
                if (!perc.isOpen(newx, newy)) {
                    perc.open(newx, newy);
                }
            }
            thresholds[i] = (double) perc.numberOfOpenSites() / (double) (n * n);
        }
        mean = StdStats.mean(thresholds);

        dev = StdStats.stddev(thresholds);
        lo = mean - ((constant * dev) / Math.sqrt(t));
        hi = mean + ((constant * dev) / Math.sqrt(t));
    }

    public double mean() {
        // Calculate sample mean of percolation threshold
        return mean;
    }

    public double stddev() {
        // Calculate sample standard deviation of percolation threshold.
        return dev;
    }

    public double confidenceLo() {
        // Return low  endpoint of 95% confidence interval
        return lo;
    }

    public double confidenceHi() {
        // Return high endpoint of 95% confidence interval
        return hi;
    }

    public static void main(String[] args) {
    }
}