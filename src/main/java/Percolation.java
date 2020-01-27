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

    private int count = 0;
    private boolean[][] grid;
    private final int[] DX = {1, 0, 0, -1};
    private final int[] DY = {0, 1, -1, 0};
    private int n;
    private WeightedQuickUnionUF uF1;
    private WeightedQuickUnionUF uF2;

    /*
     c = num of columns
     to store a pair (x,y)or(row,col)--> cx+y = val
     to get the value --> (val/c, val%c)
     */
    public Percolation(int n) {
        grid = new boolean[n][n];
        uF1 = new WeightedQuickUnionUF(n * n + 2);
        uF2 = new WeightedQuickUnionUF(n * n + 1);
        this.n = n;
        for (int i = 1; i <= n; i++) {
            uF1.union(0, i);
            uF2.union(0, i);
        }
        for (int i = n; i <= n * n - n; i++) {
            uF1.union(n * n + 1, i);
        }

        // top virtual site is at 0, bottom n-1
        // union bottom from n to n*n
        // true = open
        // false = blocked
    }

    public void open(int row, int col) {
        if (!inbounds(row, col)) throw new IllegalArgumentException("Invalid numbers were inputed for the open method");
        count++;
        grid[row - 1][col - 1] = true;
        for (int i = 0; i < DX.length; i++) {
            int x = DX[i] + row ;
            int y = DY[i] + col ;
            if (!inboundsgrid(x, y)) continue;
            if (isOpen(x, y)) {
                uF1.union(row * grid.length + col, x * n + y);
                uF2.union(row * grid.length + col, x * n + y);
            }

        }

    }

    public boolean isOpen(int row, int col) {

        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        return uF2.connected(0, row * n + col);
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        if (n == 1) return true;
        return uF1.connected(0, n * n + 1);
    }

    private boolean inboundsgrid(int x, int y) {
        return !(x < 0 || x >= grid[0].length || y < 0 || y >= grid.length);
        // keep in mind the inbound method works within the context of the array
    }

    private boolean inbounds(int x, int y) {
        return !(x <= 0 || x > grid[0].length || y <= 0 || y > grid.length);
        // keep in mind the inbound method works within the context of the array
    }

    public static void main(String[] args) {
        // TODO: test client (optional)
    }
}
