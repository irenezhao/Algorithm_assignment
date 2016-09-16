import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    /**
     * The size of the queue
     */
	private int N;
	/**
	 * Use Array s to store items
	 */
	private Item[] s;
	/**
	 * construct an empty randomized queue
	 */
	public RandomizedQueue() {
	    N = 0;
	}
	/**
	 * is the queue empty?
	 * @return true if size of queue is zero
	 */
	public boolean isEmpty() {
	    return N == 0;
	}
	/**
	 * return the number of items on the queue
	 * @return size
	 */
	public int size() {
	    return N;
	}
	/**
	 * add the item
	 * @param item new item to be add in the queue
	 */
	public void enqueue(Item item) throws NullPointerException {
	    if (item == null) {
            throw new NullPointerException("Add a null item!");
        }
	    if (isEmpty()) {
	        fixedCapacity(2);
	    }
	    s[N++] = item;
	    if (N == s.length) {
	        resize(2 * s.length);
	    }
	    
	}
	@SuppressWarnings("unchecked")
    private void fixedCapacity(int capacity) {
	    s = (Item[]) new Object[capacity];
	}
	/**
	 * remove and return a random item
	 * @return
	 */
	public Item dequeue() throws NoSuchElementException {
	    if (isEmpty()) {
            throw new NoSuchElementException("Remove an item from an empty deque!");
        }
	    int temp = StdRandom.uniform(N);
	    Item res = s[temp];
	    if (N - 1 == temp) {
	        s[temp] = null;
	    }else {
	        s[temp] = s[N - 1];
	        s[N - 1] = null;
	    }
	    N = N - 1;
	    if (N > 0 && N == s.length / 4) {
	        resize(s.length / 2);
	    }
	    return res;
	}
	
	@SuppressWarnings("unchecked")
    private void resize(int capacity) {
	    Item[] copy = (Item[]) new Object[capacity];
	    for (int i = 0; i < N; i++) {
	        copy[i] = s[i];
	    }
	    s = copy;
	}
	/**
	 * return (but do not remove) a random item
	 * @return
	 */
	public Item sample() throws NoSuchElementException {
	    if (isEmpty()) {
	        throw new NoSuchElementException("Remove an item from an empty deque!");
	    }
	    return s[StdRandom.uniform(N)];
	    
	}
	/**
	 * return an independent iterator over items in random order
	 */
	public Iterator<Item> iterator() {
	    return new RandomizedQueueIterator();
	    
	}
	private class RandomizedQueueIterator implements Iterator<Item> {
	    private int i = 0;
	    private int[] indices;
	    public RandomizedQueueIterator() {
	        indices = new int[N];
            for(int j = 0; j < indices.length; j++)
            {
                indices[j] = j;
            }
            StdRandom.shuffle(indices);
	    }
	    public boolean hasNext() {
	        return i < N;
	    }
	    public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Can't do remove!");
        }
	    public Item next() throws NoSuchElementException {
	        if (!hasNext()) {
                throw new NoSuchElementException("No more items to return!");
            }
	        return s[indices[i++]];
	    }
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	    RandomizedQueue<Integer> test = new RandomizedQueue<>();
	    test.enqueue(3);
	    test.enqueue(4);
	    test.enqueue(8);
	    test.enqueue(9);
	    System.out.println(test.sample());
	    test.dequeue();
	    for (int i:test) {
            System.out.println(i);
        }
	    
	}
}
