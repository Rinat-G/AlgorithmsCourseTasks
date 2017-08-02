import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private boolean[] siteOpenness;
    private boolean[] siteFullness;
    private final WeightedQuickUnionUF uf;
    private final int gridDimension;
    private int gridSize;
    private int openSites;
    private final int virtualTopSite;
    private final int virtualBottomSite;

    public Percolation(int n) {     // create n-by-n grid, with all sites blocked
        constructorArgValidate(n);
        gridDimension = n;
        int gridSize = n * n;
        siteOpenness = new boolean[gridSize];
        siteFullness = new boolean[gridSize];
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

        if (siteFullness[index]) return true;
        else if (siteOpenness[index]) {
            siteFullness[index] = uf.connected(index, virtualTopSite);
            return siteFullness[index];
        }
        return false;
    }

    public int numberOfOpenSites() {     // number of open sites
        return openSites;
    }

    public boolean percolates() {   // does the system percolate?
        for (int i = siteFullness.length - gridDimension; i < siteFullness.length; i++) {
            if(siteFullness[i]) return true;
        }
        return false;
//        return uf.connected(virtualTopSite, virtualBottomSite);

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
            throw new IllegalArgumentException("Argument " + p + " is out of range");
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
        if (gridDimension == 1) {
            uf.union(virtualTopSite, index);
            uf.union(virtualBottomSite, index);
            return;
        }
        if (row == 1) {
            uf.union(virtualTopSite, index);
            if (isOpen(row + 1, col)) uf.union(index, bottom(index));
            return;
        }

        if (row == gridDimension) {
            if (isOpen(row - 1, col)) uf.union(index, top(index));
            if (col != gridDimension) if (isOpen(row, col + 1)) uf.union(index, right(index));
            if (col != 1) if (isOpen(row, col - 1)) uf.union(index, left(index));
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
