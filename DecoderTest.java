/*
 * Name: Abhyuday Singh
 * File: DecoderTest.java
 * Description: This class is used to test the Decoder class.
 */

public class DecoderTest {

    public static void main(String[] args) {
        // Test case 1: Decode a single line
    	HashTable<Character, String> decodingHashTable = new HashTable<>();
        decodingHashTable.put('e', "010");
        decodingHashTable.put('t', "1100");
        decodingHashTable.put('r', "0111");
        decodingHashTable.put('a', "0110");
        decodingHashTable.put('o', "1001");
        decodingHashTable.put('d', "1000");
        decodingHashTable.put('n', "0011");
        decodingHashTable.put('u', "0010");
        decodingHashTable.put('s', "11011");
        decodingHashTable.put('i', "10101");
        decodingHashTable.put('f', "10100");
        decodingHashTable.put('p', "10111");
        decodingHashTable.put('y', "00001");
        decodingHashTable.put('H', "00000");
        decodingHashTable.put('c', "00011");
        decodingHashTable.put('C', "101100");
        decodingHashTable.put('l', "1011011");
        decodingHashTable.put('!', "1011010");
        decodingHashTable.put('\'', "1101001");
        decodingHashTable.put('m', "1101000");
        decodingHashTable.put('b', "1101011");
        decodingHashTable.put('h', "1101010");
        decodingHashTable.put('v', "000101");
        decodingHashTable.put(':', "000100");
        decodingHashTable.put(' ', "111");
        testDecoder(decodingHashTable, "1011000100111110001101010100111011011000011011010111000000100111010110100111011111011011111000101101111001110001101101101101011110100100101111110000110010010011111100000001010100101001101000011000111111000010000111001100001001111111101011011011011010100011110010011111110011010100101111011101111001000101101011000010100011110101001110111001011001110110001110001111001001011001011100101100000100", "Certainly! Here's a test case for your Huffman decoder based on the provided input and output:");
    }

    // Test the decoder
    private static void testDecoder(HashTable<Character, String> decodingHashTable, String encodedString, String expected) {
        Decoder decoder = new Decoder(decodingHashTable);
        String decodedString = decoder.decode(encodedString);

        System.out.println("Encoded String: " + encodedString);
        System.out.println("Decoded String: " + decodedString);
        System.out.println("Expected: " + expected);
        System.out.println("Test Result: " + (decodedString.equals(expected) ? "Passed" : "Failed"));
        System.out.println();
    }
}
