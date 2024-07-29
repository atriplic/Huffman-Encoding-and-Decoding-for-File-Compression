/*
 * File Name: HashTableTestGetKey.java
 * Author: Atul Triplicane
 * Description: This class is used to test the HashTable getKey method
 */

public class HashTableTestGetKey {	
	public static void main(String[] args) {
		HashTable<String,Integer> hash = new HashTable<>();
		// Adding mappings to HashMap
		// using put() method
		hash.put("How", 54);
		hash.put("Are", 80);
		hash.put("You", 82);
		System.out.println(hash.getKey(0));
		System.out.println(hash.getKey(1));
		System.out.println(hash.getKey(2));
		System.out.println(hash.getKey(3));
		hash.printTable();	
	}
}
