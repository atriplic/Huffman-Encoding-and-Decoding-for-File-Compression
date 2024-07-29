
/*
 * File Name: HashTable.java
 * Author: Atul Triplicane
 * Description: This program has a multiple classes 
 * that have various methods which perform specific tasks.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

/**
 * This class is a representation of a Hash Table. The hash table is created with an 
 * ArrayList of LinkedLists, and they size of the ArrayList is 8. There are many methods
 * in this class that perform various tasks such as putting, getting, and editing the 
 * Hash Table.
 * @param <K>
 * @param <V>
 */
public class HashTable<K,V> {
	
	// the number of index values and the hashTable is initialized
	static int hashTableSize = 96;
	ArrayList<LinkedList< Node<K,V>>> hashTable = new ArrayList<>(hashTableSize);
	
	/**
	 * The constructor is used to create the hash table and add the nodes to it, based on
	 * the numBuckets. A for loop is used to place the nodes in each of the array index's.
	 */
	public HashTable() {
		// the constructor is initialized with the use of a for loop to add all the nodes
		for (int i = 0; i < hashTableSize; i++) {
			hashTable.add(new LinkedList<Node<K,V>>());
		}
	}
	
	/**
	 * This is the method that returns the hashed value
	 * @param key
	 * @return
	 */
	private int hash(K key) {
		// hash code of the keys is found
		int hashCode = key.hashCode();
		// to find out which index the key is placed in, this formula is used
		int index = hashCode % hashTableSize;
		return Math.abs(index);
	}
	
	/**
	 * All of the mappings from the hash map are removed.
	 */
	public void clear() {
		// for loop to iterate through each index in the hashTable
		for (LinkedList< Node<K,V>> arrayIndex : hashTable) {
			// the in-built clear method is used
			arrayIndex.clear();
		}
	}
	
	/**
	 * Returns true if this map contains 
	 * a mapping for the specified key, and
	 * false otherwise.
	 * @param key
	 * @return True or False is returned
	 */
	public boolean containsKey(K key) {
		// getting the index by hashing and then iterating through the nodes in that index
		int hashIndex = hash(key);
		for (Node<K,V> nodes : hashTable.get(hashIndex)) {
			// true or false is returned based on if the key is in the hash table or not
			if (key.equals(nodes.key)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if this map maps one or 
	 * more keys to the specified value, and 
	 * false otherwise.
	 * @param val
	 * @return True or False is returned
	 */
	public boolean containsValue(V val) {
		// 2 for loops are used to go through each of the nodes in the hash table
		for (LinkedList< Node<K,V>> arrayIndex : hashTable) {
			for (Node<K,V> nodes : arrayIndex) {
				// true or false is returned based on if the value is in the hash table or not
				if (val.equals(nodes.value)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * The key is returned based on the index
	 * @param index
	 * @return The key is returned or null if there is no key
	 */
	public ArrayList<K> getKey(int index) {
	    // checking if the index is not greater or less than the size of the hash table
	    if (index >= 0 && index < hashTableSize) {
	        LinkedList<Node<K, V>> entry = hashTable.get(index);        
	        if (entry != null) {
	            ArrayList<K> key = new ArrayList<>();     
	            // going through all the nodes in that index and adding them to the array
	            for (Node<K, V> node : entry) {
	            	key.add(node.key);
	            }
	            // returning the first index of the key array
	            if (key.size() > 0) {
	                return key;
	            }
	            // returning null if the key size is 0
	            else {
	            	return null;
	            }
	        }
	        // returning null if the entry variable is null
	        else {
	        	return null;
	        }
	    }
	    // returning null if the index is out of range
	    else {
	    	return null;
	    }
	}
	
	/**
	 * The value to which the specified key is mapped is returned.
	 * If this map contains no mapping for the key, null is returned.
	 * @param key
	 * @return The value is returned, or null
	 */
	public V getValue(K key) {
		// getting the index by hashing and then iterating through the nodes in that index
		int hashIndex = hash(key);
		for (Node<K,V> nodes : hashTable.get(hashIndex)) {
			// the value of the key is returned if the keys match
			if (key.equals(nodes.key)) {
				return nodes.value;
			}
		}
		return null;
	}
	
	/**
	 * Returns true if this map contains no key-value mappings, 
	 * and false otherwise.
	 * @return True or False is returned
	 */
	public boolean isEmpty() {
		// for loop to iterate through each of the index's in the hash table
		for (LinkedList< Node<K,V>> arrayIndex : hashTable) {
			// the isEmpty in-built method is used to check if the index is empty or not
			if (!arrayIndex.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * A Set view of the keys contained in this map is returned.
	 * @return A set of the keys is returned
	 */
	public java.util.Set<K> keySet() {
		// a set is created
		Set<K> hashKeySet = new HashSet<K>();
		// multiple for loops are used to go through each of the nodes in the hash table
		for (LinkedList< Node<K,V>> arrayIndex : hashTable) {
			// each key is added to the set
			for (Node<K,V> nodes : arrayIndex) {
				hashKeySet.add(nodes.key);
			}
		}
		return hashKeySet;
	}
	
	/**
	 * Gives a good idea on how the Hash Table looks for the user.
	 */
	public void printTable() {
		// all the variables are initialized
		ArrayList<K> keys = new ArrayList<>();
		int conflictsTotal = 0;
		int numConflicts = 0;
		int count = 0;
		// multiple for loops are used to go through each node
		for (LinkedList< Node<K,V>> arrayIndex : hashTable) {
			for (Node<K,V> nodes : arrayIndex) {
				// all the keys are added to the ArrayList
				keys.add(nodes.key);
				// the number of conflicts is incremented
				numConflicts = keys.size() - 1;
			}
			// a comma and space is added to the last element in the ArrayList
			// no comma is added if the size of the array is 0
			if (keys.size() > 0) {
				K last = keys.get(keys.size() - 1);
				last = (K) (last + ", ");
				keys.set(keys.size() - 1, last);
			}
			// the information is printed and the variables for the next index is reset
			System.out.println("Index " + count + ": " + keys);
			keys.clear();
			conflictsTotal += numConflicts;
			numConflicts = 0;
			count += 1;
		}
		// the total number of conflicts is printed
		System.out.println("Total # of conflicts: " + conflictsTotal);
	}
	
	/**
	 * Places the specific value with the specific key in this map.
	 * @param key
	 * @param value
	 * @return The value is returned
	 */
	public V put(K key, V value) {
		// getting the hash code of the key
		int hashIndex = hash(key);
		// creating a new node
		Node<K, V> newNode = new Node<>(key, value);
		// checking if the key is already in the hash map or not
		if (!this.containsKey(key)) {
			// the node is added if it's not in the hash map
			hashTable.get(hashIndex).addFirst(newNode);
			return null;
		}
		else {
			// the value is updated if the node is already in the hash map
			for (Node<K,V> node : hashTable.get(hashIndex)) {
				if (key.equals(node.key)) {
					V valueOld = node.value;
					node.value = value;
					return valueOld;
				}
			}
		}
		return null;
	    
	}

	/**
	 * This method iterates through the right of the hash table
	 * until it finds the next empty index.
	 * @param curIndex
	 * @return The index is returned or -1
	 */
	public int getEmptyIndex(int curIndex) {
		// initializing the empty index
	    int emptyIndex = curIndex;
	    // iterate to find the next available index
	    while (hashTable.get(emptyIndex).isEmpty() == false) {
	    	// going through each of the index's
	    	emptyIndex += 1;
	    	emptyIndex %= hashTableSize;
	        // if i comes back to the original index, then return -1
	        if (emptyIndex == curIndex) {
	            return -1;
	        }
	        // otherwise continue
	        else {
	        	continue;
	        }
	    }
	    // return the empty index
	    return emptyIndex;
	}
	
	/**
	 * The mapping for the specified key from 
	 * this map is returned, if present.
	 * @param key
	 * @return The value of the key thats removed is returned
	 */
	public V remove(K key) {
		// getting the hash code of the key
		int hashKey = this.hash(key);
		LinkedList<Node<K,V>> newNode = hashTable.get(hashKey);
		// creating the "valueOld" variable that will be returned later
		V valueOld = null;
		// null will be returned if newNode is null
		if (newNode == null) {
			return null;
		}
		// we need to remove that specific node if the key is present
		if (this.containsKey(key)) {
			LinkedList<Node<K,V>> linkedList = hashTable.get(hashKey);
			valueOld = this.getValue(key);
			// using ListIterator to go through the linked list easily
			ListIterator<Node<K,V>> iteratorList = linkedList.listIterator();
			// a while loop is used to iterate through each of the nodes
			while (iteratorList.hasNext()) {
				// the next 3 lines is used to remove the specific node based on the key
				Node<K,V> nodeNext = iteratorList.next();
				if (nodeNext.key.equals(key)) {
					linkedList.remove(nodeNext);
				}
			}
		}
		return valueOld;
	}
	
	/**
	 * The number of key value mappings in this map is returned.
	 * @return The size of the hash table is returned
	 */
	public int size() {
		int countSize = 0;
		// multiple for loops are used to go through each of the nodes in the hash table
		for (LinkedList< Node<K,V>> arrayIndex : hashTable) {
			for (Node<K,V> nodes : arrayIndex) {
				// the counter is incremented every time a node is seen
				countSize += 1;
			}
		}
		return countSize;
	}
	
	/**
	 * This method takes the hash table, doubles the size, and rehashes the nodes
	 */
	public void resize() {
	    // doubling the hashtable's size
		hashTableSize = hashTableSize * 2;
	    // the temporary HashTable with the doubled size is initialized
	    ArrayList<LinkedList<Node<K, V>>> tempHashTable = new ArrayList<>(hashTableSize);
	    // for loop to add a new linked list to the index's
	    for (int i = 0; i < hashTableSize; i++) {
	    	tempHashTable.add(new LinkedList<>());
	    }    
	    // rehashing all of the nodes in this temporary HashTable
	    for (LinkedList<Node<K, V>> index : hashTable) {
	        for (Node<K, V> hashNode : index) {
	            int indexNew = hashNode.key.hashCode() % hashTableSize;
	            tempHashTable.get(indexNew).addFirst(hashNode);
	        }
	    } 
	    // pointing the original HashTable to the temporary HashTable
	    hashTable = tempHashTable;
	}
}

class Node<K,V> {
	/*     This class is a representation of a node. The node consists of a key and a value.
	 * 	   This is where <K, V> is used. This is generic's. K represents the key and V
	 *     represents the value. These generic variables will be used in the whole program. 

       The constructor doesn't do much. The only thing that the constructor does is initialize
       the key and the value based on what the user inputed.
	 * */
	
	// the given key and value are initialized
	K key;
	V value;
	
	public Node(K key, V value) {
		// the key and value is initialized
		this.key = key;
		this.value = value;
	}
}
