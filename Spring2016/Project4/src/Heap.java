// Greg Violan, 011706641
/**
 * imported necessary libs
 */
import java.util.ArrayList;
/**
 * Heap class is a heap database. It uses the ArrayList but acts like a heap. This is a generic heap therefore it can be used
 * for any types. It also extends Comparable for sorting the heap. It has a get size and empty methods as well as necessary
 * methods for a heap such as getting the parent location and its children. It has a get node method as well as add and remove method.
 * And ofcourse the print method.
 * @author Greg Violan, 011706641
 * @param <T>, any generic type
 */
public class Heap <T extends Comparable<T>>{
	/**
	 * Heap constructor, just uses an ArrayList
	 */
	public Heap(){
		heap = new ArrayList<T>();
	}
	/**
	 * Gets the size of the Heap
	 * @return size
	 */
	public int getSize(){
		return heap.size();
	}
	/**
	 * Checks if Heap is empty
	 * @return true if its empty, otherwise false if its not
	 */
	public boolean isEmpty(){
		return heap.isEmpty();
	}
	/**
	 * Gets the location of the parent of the parameter
	 * @param i, the element in the heap
	 * @return the parent of the argument
	 */
	public int getPLoc(int i){
		return ((i - 1) / 2) ;
	}
	/**
	 * Gets the location of the left child of the parameter
	 * @param i, the element in the heap
	 * @return the left child of the argument
	 */
	public int getLCLoc(int i){
		return ((2 * i) + 1);
	}
	/**
	 * Gets the location of the right child of the parameter
	 * @param i, the element in the heap
	 * @return the right child of the argument
	 */
	public int getRCLoc(int i){
		return ((2 * i) + 2);
	}
	/**
	 * Gets the node at the argument's element
	 * @param i, the element in the heap
	 * @return the node of the heap's element
	 */
	public T getNodeAt(int i){
		T node = null;
		if(heap.get(i) == null){
			System.out.println("Item does not exist");
		}
		else{
			node = heap.get(i);
		}
		return node;
	}
	/**
	 * Adds a node in the heap. It puts it in a heap order, the left side is less than the right side of the heap
	 * @param n, the generic object type
	 */
	public void addNode(T n){
		heap.add(null);
		int index = heap.size() - 1;
		
		//checks if the child is greater than the parent
		while(index > 0 && getNodeAt(getPLoc(index)).compareTo(n) > 0){ // TEST either > 0 or < 0
			//swaps the parent and the child 
			heap.set(index, getNodeAt(getPLoc(index))); //set the parent to the child's position 
			index = getPLoc(index); // set child to parents position
		}//if not just set the child into the last index of the heap
		heap.set(index, n);
		
	}
	/**
	 * Removes the minimum of the heap.
	 * @return min, the minimum of the heap. 
	 */
	public T removeMin(){
		T min = heap.get(0);
		int index = heap.size() - 1;
		T last = heap.remove(index);
		
		if(index > 0 ){
			heap.set(0, last);
			T root = heap.get(0);
			int end = heap.size() - 1;
			index = 0;
			
			boolean done = false;
			while(!done){
				if(getLCLoc(index) <= end){ // left exists
					T child = getNodeAt(getLCLoc(index));
					
					int childLoc = getLCLoc(index);
					if(getRCLoc(index) <= end){ // right exists
						if(getNodeAt(getRCLoc(index)).compareTo(child) < 0){ // TEST 
							child = getNodeAt(getRCLoc(index));
							childLoc = getRCLoc(index);
						}
					}
					if(child.compareTo(root) < 0){ // TEST
						heap.set(index, child);
						index = childLoc;
					}else done = true;
				}else done = true; // no children
			}
			heap.set(index, root);
		}
		return min;
	}
	/**
	 * Prints the heap for displaying
	 */
	public void printHeap(){
		for(int i = 0; i < heap.size(); ++i){
			System.out.println(heap.get(i).toString() + ".");
		}
		System.out.println();
	}
	/**
	 * Generic array list for the heap
	 */
	private ArrayList<T> heap;
}
