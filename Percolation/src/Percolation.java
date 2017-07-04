import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by R0cky on 04.07.2017.
 */
public class Percolation {
    private boolean[] siteFullness;
    private boolean[] siteOpenness;
    private WeightedQuickUnionUF uf;
    private int gridSize;
    private int gridDimension;
    private int openSites;
    private int virtualTopSite;
    private int virtualBottomSite;

    public Percolation(int n) {     // create n-by-n grid, with all sites blocked
        constructorArgValidate(n);
        gridDimension = n;
        gridSize = n * n;
        siteFullness = new boolean[gridSize];
        siteOpenness = new boolean[gridSize];
        uf = new WeightedQuickUnionUF(gridSize + 2);
        virtualTopSite = gridSize;
        virtualBottomSite = gridSize + 1;
        openSites = 0;

    }

    public void open(int row, int col) {    // open site (row, col) if it is not open already
        validateArgs(row, col);

        if (!isOpen(row, col)) {
            int index = rowColumnToIndex(row, col);
            siteOpenness[index] = true;
            openSites += 1;
            unionAdjacentSites(row, col, index);
        }

    }

    public boolean isOpen(int row, int col) {   // is site (row, col) open?
        validateArgs(row, col);
        int index = rowColumnToIndex(row, col);
        return siteOpenness[index];
    }

    public boolean isFull(int row, int col) {   // is site (row, col) full?
        validateArgs(row, col);
        int index = rowColumnToIndex(row, col);
        return uf.connected(index, virtualTopSite);
    }

    public int numberOfOpenSites() {     // number of open sites
        return openSites;
    }

    public boolean percolates() {   // does the system percolate?
        return uf.connected(virtualTopSite, virtualBottomSite);

    }

    public static void main(String[] args) {

    }

    private void constructorArgValidate(int p) {
        if (p <= 0) {
            throw new IllegalArgumentException("Grid size is <= 0");
        }
    }

    private int rowColumnToIndex(int row, int col) {
        return (row - 1) * gridDimension + col - 1;
    }

    private void validateArgs(int p, int q) {
        if (p < 1 || p > gridDimension || q < 1 || q > gridDimension) {
            throw new IllegalArgumentException("Argument " + p + "is out of range");
        }
    }

    private int top(int index) {
        return index - gridDimension;
    }

    private int bottom(int index) {
        return index + gridDimension;
    }

    private int right(int index) {
        return index + 1;
    }

    private int left(int index) {
        return index - 1;
    }

    private void unionAdjacentSites(int row, int col, int index) {
        if (row == 1) {
            uf.union(virtualTopSite, index);
            if (isOpen(row + 1, col)) uf.union(index, bottom(index));
            return;
        }

        if (row == gridDimension) {
            uf.union(virtualBottomSite, index);
            if (isOpen(row - 1, col)) uf.union(index, top(index));
            return;
        }

        if (col == 1) {
            if (isOpen(row - 1, col)) uf.union(index, top(index));
            if (isOpen(row + 1, col)) uf.union(index, bottom(index));
            if (isOpen(row, col + 1)) uf.union(index, right(index));
            return;
        }

        if (col == gridDimension) {
            if (isOpen(row - 1, col)) uf.union(index, top(index));
            if (isOpen(row + 1, col)) uf.union(index, bottom(index));
            if (isOpen(row, col - 1)) uf.union(index, left(index));
            return;
        }

        if (isOpen(row - 1, col)) uf.union(index, top(index));
        if (isOpen(row + 1, col)) uf.union(index, bottom(index));
        if (isOpen(row, col + 1)) uf.union(index, right(index));
        if (isOpen(row, col - 1)) uf.union(index, left(index));


    }
}
