/*
 * File: Encoder.java
 * Author: Madison Ryan, Abhyuday Singh 
 * Purpose: An implementation of a priority queue which prioritizes node 
 *          elements based on their value from greatest to least.
 */

/**
 * An implementation of a priority queue that queues HuffNode elements based on
 * their frequency value from greatest to least.
 */
public class NodeQueue {
    HuffNode[] queueHeap;
    int capacity = 10;
    int size;
    int back;

    /**
     * Constructor for a priority queue of Huffman nodes.
     */
    public NodeQueue() {
        queueHeap = new HuffNode[capacity];
        size = 0;
        back = 0;
    }

    public HuffNode peek() {
        if(size==0)
            return null;
        return queueHeap[0];
    }

    /**
     * Adds the Huffman node into the priority queue.
     * 
     * @param newNode  the HuffNode to add to the queue
     */
    public void add(HuffNode newNode) {
        if(size==capacity) {
            resize(capacity*2);
        }

        // inserts newNode at the end of the heap
        queueHeap[back] = newNode;

        // swims the newNode up the heap (priority)
        swim(back);

        size++;
        back++;
    }

    /**
     * Removes and returns the frontmost Huffman node from the queue.
     * 
     * @return  the HuffNode that was the highest priority in the queue
     */
    public HuffNode poll() {
        if(size==0) {
            return null;
        }

        HuffNode retNode = queueHeap[0];  // gets frontmost node in queue
        // replaces front w/ last node in queue then sinks to keep heap order
        swap(0, --back);
        sink(0);

        size--;
        // resizes if queue is small enough
        if(size>10 && size<capacity/4) {
            resize(capacity/2);
        }

        return retNode;
    }   

    /**
     * Gets the size of the queue.
     * 
     * @return  the number of nodes in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return  true if the queue is empty, false if not
     */
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * Resizes the array used to store the queue.
     * 
     * @param newCap  the new capacity for the queue
     */
    private void resize(int newCap) {
        HuffNode[] newQueueHeap = new HuffNode[newCap];
		
        // moves elements from old queue array to new queue array
		for (int i = 0; i < size; i++) {	
            newQueueHeap[i] = queueHeap[i];
		}

        // updates all class variables;
		queueHeap = newQueueHeap;
        capacity = newCap;
        back=size;
    }

    /**
     * Swims the node at the given index up the heap.
     * 
     * @param idxCur  the index of the node to swim in the array-heap
     */
    private void swim(int idxCur) {
        int idxPar = (idxCur-1)/2;

        // if current node in the heap has a > value than its parent, swims up
        while(idxCur!=capacity && queueHeap[idxCur].compareTo(queueHeap[idxPar])<0) {
            swap(idxCur, idxPar);
            idxCur = idxPar;
            idxPar = (idxCur-1)/2%capacity;
        }
        
    }

    /**
     * Sinks the node at the given index down the heap.
     * 
     * @param idxCur  the index of the node to sink in the array-heap
     */
    private void sink(int idxCur) {
        int idxLeft = 2*idxCur+1;
        int idxRight= 2*idxCur+2;
        boolean isSinking = (idxLeft<back);
        boolean hasRight = false;

        while(isSinking) {
            // if has right child that is > left child
            hasRight = (idxRight<back);
            if(hasRight && queueHeap[idxCur].compareTo(queueHeap[idxRight])>0 && queueHeap[idxRight].compareTo(queueHeap[idxLeft])<0){
                swap(idxCur, idxRight);  // sink i to the right
                idxCur = idxRight;
            }
            // if only has left child or left's val > right's val
            else if(queueHeap[idxCur].compareTo(queueHeap[idxLeft])>0){
                swap(idxCur, idxLeft);  // sink i to the left
                idxCur = idxLeft;
            }
            // stops sinking if a[i] greater than both its children
            else {isSinking = false;}

            idxLeft = 2*idxCur+1;
            idxRight= 2*idxCur+2;
            isSinking &= (idxLeft<back);
        }
    }

    /**
     * Swaps the nodes at the indices in queueHeap. 
     * 
     * @param idx1  the index of one of the nodes to swap
     * @param idx2  the index of the other node to swap
     */
    private void swap(int idx1, int idx2) {
        HuffNode temp = queueHeap[idx1];
        queueHeap[idx1] = queueHeap[idx2];
        queueHeap[idx2] = temp;
    }

    // for testing purposes only
    public void printCurrentOrder() {
        int i = 0;
        while(i != back) {
            if(i == capacity) {
                i = 0;
            }
            System.out.print(queueHeap[i].getFrequency() + ", ");

            i++;
        }

        System.out.println();
    }
}
