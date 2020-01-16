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
    private int[][] grid;
   public Percolation(int n)  {
       int[][] grid = new int[n][n];
       //0 = blocked 1 = unblocked 2 = filled
       //1-->n instead of 0-->n-1
   }

   public void open(int row, int col) {
       count++;
       grid[row-1][col-1] = 1;
       //union all adjacent sites
   }
   public boolean isOpen(int row, int col) {

     return grid[row-1][col-1] == 1;
   }
   public boolean isFull(int row, int col) {

       return grid[row-1][col-1] == 2;
   }
   public int numberOfOpenSites() {

     return count;
   }
   
   public boolean percolates() {

     // TODO: does the system percolate?
     return false;
   }

   public static void main(String[] args) {
     // TODO: test client (optional)
   }
}
