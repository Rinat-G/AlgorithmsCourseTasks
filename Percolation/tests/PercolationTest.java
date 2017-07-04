import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by R0cky on 04.07.2017.
 */
public class PercolationTest {
    private int dim = 20;
    int randRow = 1;

    Percolation percolation = new Percolation(dim);

    @Test
    public void testConstructor() {


        assertEquals(percolation.isOpen(dim / 2, dim / 2), false);
    }

    @Test
    public void testPercolates() {
        for (int i = 1; i <= dim; i++) {
            percolation.open(i, randRow);
        }

        assertEquals(percolation.percolates(), true);

    }

    @Test
    public void testNumberOfOpenSites(){
        for (int i = 1; i <= dim; i++) {
            percolation.open(i, randRow);
        }

        assertEquals(percolation.numberOfOpenSites(), dim);
    }
}
