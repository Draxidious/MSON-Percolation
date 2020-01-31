import edu.princeton.cs.algs4.WeightedQuickUnionUF;

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
public class Percolation {
    /**
     * The grid for checking if sites are open.
     */
    private boolean[] grid;
    /**
     * The size of the grid = n*n.
     */
    private int n;
    /**
     * Union Find for percolation.
     */
    private WeightedQuickUnionUF uF1;
    /**
     * Union Find to deal with backwash.
     */
    private WeightedQuickUnionUF uF2; // without bottom
    /**
     * Top virtual site.
     */
    private int top;
    /**
     * Bottom virtual site.
     */
    private int bottom;
    /**
     * Count how many are open.
     */
    private int count = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be bigger than 0");
        }
        this.n = n;
        uF1 = new WeightedQuickUnionUF(n * n + 2);
        uF2 = new WeightedQuickUnionUF(n * n + 1);
        grid = new boolean[n * n + 2];   // 0 top virtual site N*N+1 bottom virtual site
        top = 0;
        bottom = n * n + 1;

    }

    public void open(int row, int col) {
        if (!inbounds(row, col)) throw new IllegalArgumentException("Index not between 1 and n");
        if (!isOpen(row, col)) {
            int index = xyTo1D(row, col);
            count++;
            grid[index] = true;

            if (row == 1) {
                uF1.union(index, top);
                uF2.union(index, top);
            }
            if (!percolates()) {
                if (row == n) {
                    uF1.union(index, bottom);
                }
            }
            if (row < n && grid[index + n]) {
                uF1.union(index, index + n);
                uF2.union(index, index + n);
            }
            if (row > 1 && grid[index - n]) {
                uF1.union(index, index - n);
                uF2.union(index, index - n);
            }
            if (col < n && grid[index + 1]) {
                uF1.union(index, index + 1);
                uF2.union(index, index + 1);
            }
            if (col > 1 && grid[index - 1]) {
                uF1.union(index, index - 1);
                uF2.union(index, index - 1);
            }
        }
    }

    private int xyTo1D(int x, int y) {
        if (!inbounds(x, y)) throw new IllegalArgumentException("Index not between 1 and n");
        return y + (x - 1) * n;
    }

    private boolean inbounds(int x, int y) {
        return (x >= 1 && x <= n && y >= 1 && y <= n);
    }

    public boolean isOpen(int row, int col) {
        if (!inbounds(row, col)) throw new IllegalArgumentException("Index not between 1 and n");
        return grid[xyTo1D(row, col)];
    }


    public boolean isFull(int row, int col) {
        if (!inbounds(row, col)) throw new IllegalArgumentException("Index not between 1 and n");
        return uF2.connected(top, xyTo1D(row, col));
    }

    public boolean percolates() {
        return uF1.connected(top, bottom);
    }

    public int numberOfOpenSites() {
        return count;
    }

    public static void main(String[] args) {
    }
}
