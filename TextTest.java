/*
 * File: TextTest.java
 * Author: Atul Triplicane
 * Purpose: Testing the process of encoding and decoding a text file.
 */

import java.io.IOException;

public class TextTest {
	
	public static void main(String[] args) {
		
		// initializing instances of the HashTables and textProcessor
		HashTable<Character, String> singleEncodedHashMap = null;
		HashTable<Character, String> fileEncodedHashMap = null;
		Text textProcessor = new Text();

		// Test case 1: Encode a single line and write it to a file
		System.out.println("Test Case 1:");
        try {
        	singleEncodedHashMap = textProcessor.singleEncoder("Hello, World!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        
        // Test case 2: Encode a file and write the encoded lines to a new file
        System.out.println("Test Case 2:");
        try {
        	fileEncodedHashMap = textProcessor.fileEncoder("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println();

        // Test case 3: Decode a file and write the decoded lines to a new file
        System.out.println("Test Case 3:");
        try {
            textProcessor.fileDecoder("test2.txt", fileEncodedHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println();
        
        // Test case 4: Encode and then decode a single line
        System.out.println("Test Case 4:");
        String originalText = "Testing encode and decode.";
		System.out.println("Original Text: " + originalText);

		// Encode the text
		Encoder encoder = new Encoder();
		String encodedText = encoder.encode(originalText);
		singleEncodedHashMap = encoder.getKey();
		System.out.println("Encoded Text: " + encodedText);

		// Decode the text
		Decoder decoder = new Decoder(singleEncodedHashMap);
		String decodedText = decoder.decode(encodedText);
		System.out.println("Decoded Text: " + decodedText);
    }
}
