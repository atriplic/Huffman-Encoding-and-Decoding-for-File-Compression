/*
 * Author: Abhyuday Singh
 * Filename: Decoder.java
 * Description: This class is used to decode a string using a Huffman tree.
 */

public class Decoder {
    private HashTable<Character, String> decodingHashTable;
    private Node rootNode;

    public Decoder(HashTable<Character, String> decodingHashTable) {
        this.decodingHashTable = decodingHashTable;
        this.rootNode = buildHuffmanTreeFromHashTable(decodingHashTable);
    }

    // Decode the encoded string using the Huffman tree
    public String decode(String encodedString) {
        StringBuilder decodedString = new StringBuilder();
        Node currentNode = rootNode;

        for (char bit : encodedString.toCharArray()) {
            if (bit == '0') {
                currentNode = currentNode.left;
            } else if (bit == '1') {
                currentNode = currentNode.right;
            }

            if (currentNode.left == null && currentNode.right == null) {
                decodedString.append(currentNode.c);
                currentNode = rootNode;
            }
        }

        return decodedString.toString();
    }

    // Build a Huffman tree from the decoding hash table
    private Node buildHuffmanTreeFromHashTable(HashTable<Character, String> decodingHashTable) {
        Node root = new Node(0, (char) 0); // Dummy root node
        for (char c : decodingHashTable.keySet()) {
            String code = decodingHashTable.getValue(c);
            Node currentNode = root;

            for (char bit : code.toCharArray()) {
                if (bit == '0') {
                    if (currentNode.left == null) {
                        currentNode.left = new Node(0, (char) 0);
                    }
                    currentNode = currentNode.left;
                } else if (bit == '1') {
                    if (currentNode.right == null) {
                        currentNode.right = new Node(0, (char) 0);
                    }
                    currentNode = currentNode.right;
                }
            }
            currentNode.c = c;
        }

        return root;
    }

    // Inner class for Node similar to the one in the Encoder
    private static class Node {
        int freq;
        char c;
        Node left;
        Node right;

        Node(int freq, char c) {
            this.freq = freq;
            this.c = c;
            left = null;
            right = null;
        }

        Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            freq = left.freq + right.freq;
            c = 0;
        }
    }
}
