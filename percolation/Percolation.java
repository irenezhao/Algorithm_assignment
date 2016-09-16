import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int top;
    private int bottom;
    private boolean[] ifOpen;
    private WeightedQuickUnionUF p;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) throws IllegalArgumentException {
        if (N <= 0) {
            throw new IllegalArgumentException("Given N <= 0!");   
        }
        this.N = N;
        this.top = N*N;
        this.bottom = N*N+1;
        ifOpen = new boolean[N*N];   
        p = new WeightedQuickUnionUF(N*N+2);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        
        if (i <= 0 || i > N || j <= 0 || j > N) {
            throw new IndexOutOfBoundsException();
        }
        
        if (isOpen(i, j)) return;
     
        ifOpen[indexOf(i, j)] = true;
        int index = indexOf(i, j);
        
        if (i == 1) {
            p.union(index, top);
        }
        if (i == N) {
            p.union(index, bottom);
        }
        if (i > 1 && isOpen(i-1, j)) {
            p.union(index, indexOf(i-1, j));
        }
        if (i < N && isOpen(i+1, j)) {
            p.union(index, indexOf(i+1, j));
        }
        if (j > 1 && isOpen(i, j-1)) {
            p.union(index, indexOf(i, j-1));    
        }
        if (j < N && isOpen(i, j+1)) {
            p.union(index, indexOf(i, j+1));
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (0 < i && i <= N && 0 < j && j <= N) {
            return ifOpen[indexOf(i, j)];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if (0 < i && i <= N && 0 < j && j <= N) {
            return p.connected(indexOf(i, j), top);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    
    // does the system percolate?
    public boolean percolates() {
        return (p.connected(top, bottom));
    }
    //the index of (i,j) in WeightedQuickUnionUF
    private int indexOf(int i, int j) {
        return N*(i-1)+j-1;
    }
    public static void main(String[] args) {
        Percolation p = new Percolation(0);
    }
   
}