import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Random;

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
    private int t;
    /**
     * Mean value of all thresholds.
     */
    private double mean;
    /**
     * Standard deviation of threshold values.
     */
    private double dev;
    /**
     * Constant value for calculation
     */
    private final double constant = 1.96;

    public PercolationStats(int n, int trials) {
        // TODO: perform trials independent experiments on an n-by-n grid

        thresholds = new double[trials];
        t = trials;
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int newx = StdRandom.uniform(1, n);
                int newy = StdRandom.uniform(1, n);
                perc.open(newx, newy);
            }
            thresholds[i] = perc.numberOfOpenSites() / n;
            mean = StdStats.mean(thresholds);
            dev = StdStats.mean(thresholds);
        }
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
        return mean - ((constant * dev) / Math.sqrt(t));
    }

    public double confidenceHi() {
        // Return high endpoint of 95% confidence interval
        return mean + ((constant * dev) / Math.sqrt(t));
    }

    public static void main(String[] args) {
        // test client (described at http://coursera.cs.princeton.edu/algs4/assignments/percolation.html)
    }
}