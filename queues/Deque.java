import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first, last;
	private int size;
	
	private class Node {
		Item item;
		Node next;
		Node prev;
	}
	/** construct an empty dequeue */
    public Deque() {
	   first = null;
	   last = null;
	   size = 0;
    }
    
    /** is the dequeue empty? */
    public boolean isEmpty() {
    	return size == 0;
    }
    
    /** return the number of items on the dequeue*/
    public int size() {
    	return size;
    }
    
    /** add the item to the front
     * @param item
     * @throws NullPointerException
     */
    public void addFirst(Item item) throws NullPointerException {
    	if (item == null) {
    		throw new NullPointerException("Add a null item!");
    	}
    	if (isEmpty()) {
    		first = new Node();
    		first.item = item;
    		last = first;
    	}else {
    		first.prev = new Node();
    		first.prev.item = item;
    		first.prev.next = first;
    		first = first.prev;
    	}
    	size++;
    }
    
    /**add the item to the end
     * @param item
     * @throws NullPointerException
     */
    public void addLast(Item item) throws NullPointerException {
    	if (item == null) {
    		throw new NullPointerException("Add a null item!");
    	}
    	if (isEmpty()) {
    		first = new Node();
    		first.item = item;
    		last = first;
    	}else {
    		last.next = new Node();
    		last.next.item = item;
    		last.next.prev = last;
    		last = last.next;
    	}
    	size++;
    }
    /**remove and return the item from the front
     * @return the removed item
     * @throws NoSuchElementException
     */
    public Item removeFirst() throws NoSuchElementException {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Remove an item from an empty deque!");
    	}
    	
    	Item temp = first.item;
    	if (size() == 1) {
    		first = null;
    		last = null;
    	}else {
    		first = first.next;
    		first.prev = null;
    	}
    	size--;
    	return temp;
    }
    /**remove and return the item from the end
     * @return the removed item
     * @throws NoSuchElementException
     */
    public Item removeLast() throws NoSuchElementException {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Remove an item from an empty deque!");
    	}
    	Item temp = last.item;
    	if (size() == 1) {
    		first = null;
    		last = null;
    	}else {
    		last = last.prev;
    		last.next = null;
    	}
    	size--;
    	return temp; 
    }

    /**return an iterator over items in order from front to end */
    public Iterator<Item> iterator() {
    	return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
    	private Node current = first;
    	
    	public boolean hasNext() {
    		return current != null;
    	}
    	public void remove() throws UnsupportedOperationException {
    		throw new UnsupportedOperationException("Can't do remove!");
    	}
    	public Item next() throws NoSuchElementException {
    		if (!hasNext()) {
    			throw new NoSuchElementException("No more items to return!");
    		}
    		Item item = current.item;
    		current = current.next;
    		return item;
    	}
    }
    public static void main(String[] args) {
    	Deque<Integer> test = new Deque<>();
    	test.addFirst(1);
    	test.addFirst(2);
    	test.addLast(3);
    	for (int i:test) {
    		System.out.println(i);
    	}
    	test.removeFirst();
    	test.removeFirst();
    	test.removeFirst();
    	for (int i:test) {
    		System.out.println(i);
    	}
    	
    }
}
