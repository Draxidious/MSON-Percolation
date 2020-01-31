import org.junit.Test;


public class PercolationStatsTest {
    PercolationStats stats;

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTrials() {
        stats = new PercolationStats(100,-200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidN() {
        stats = new PercolationStats(-100,10);
    }

}
