/*
 * File: Encoder.java
 * Author: Madison Ryan
 * Purpose: Compresses/encodes a String of characters using Huffman encoding.
 */


/**
 * Takes a String input and converts it using Huffman encoding to a bit 
 * sequence.
 */
public class Encoder {
    private String inputStr;
    private HashTable<Character,String> key;
    private HuffNode rootNode;

    /**
     * Empty constructor for an Encoder.
     */
    public Encoder() {
        inputStr = "";
        key = new HashTable<>();
    }

    /**
     * Constructor for an Encoder for the given String.
     * 
     * @param inputString
     */
    public Encoder(String inputStr) {
        this.inputStr = inputStr;
        key = new HashTable<>();
        huffmanCoding();
    }

    /**
     * Encodes the input String (given elsewhere) using Huffman encoding.
     * 
     * @return  the String of the bit sequence representing the encoded input
     */
    public String encode() {
        String encodedStr = "";
        char[] charsArr = inputStr.toCharArray();

        // Inputs Huffman code for each char from the input into a String
        for(char c: charsArr) {
            encodedStr += key.getValue(c);
        }

        return encodedStr;
    }

    /**
     * Encodes the given input string 
     * 
     * @param inputStr  the String to encode
     * @return  the String of the bit sequence representing the encoded input
     */
    public String encode(String inputStr) {
        this.inputStr = inputStr;
        key = new HashTable<>();
        huffmanCoding();
        return encode();
    }

    /**
     * Getter for the key for the current/most recent input from Huffman coding.
     * 
     * @return  key for the encoded input string
     */
    public HashTable<Character,String> getKey() {
        return key;
    }

    /**
     * Changes the String to be encoded using Huffman encoding.
     * 
     * @param inputStr  the new input string to be encoded
     */
    public void changeInput(String inputStr) {
        this.inputStr = inputStr;
        key = new HashTable<Character, String>();
        huffmanCoding();
    }

    public String getInput() {
        return inputStr;
    }

    /**
     * Performs Huffman coding to generate a Huffman tree for the input string.
     */
    private void huffmanCoding() {
        NodeQueue huffNodeQueue = getHuffNodesFromStr(inputStr);
        rootNode = buildHuffmanTree(huffNodeQueue);
        makeKey(rootNode, "");
    }

    /**
     * Gets the frequency of each unique character in the input string and 
     * stores Nodes with the characters and their frequencies in a PriorityQueue
     * where the front is the least frequent character.
     * 
     * @param inputStr String to get frequency of characters from
     * @return PriorityQueue of Nodes for Huffman tree; priority by lowest freq
     */
    private NodeQueue getHuffNodesFromStr(String inputStr) {
        char[] charsArr = inputStr.toCharArray();
        HashTable<Character,Integer> charFreqMap = new HashTable<>();

        // Goes through each char in input & finds number of times it occurs
        for(char c: charsArr) {
            if(charFreqMap.containsKey(c)) {
                charFreqMap.put(c, charFreqMap.getValue(c)+1);
            }
            else {
                charFreqMap.put(c, 1);
            }
        }

        // adds Nodes with chars & freqs to the PriorityQueue
        NodeQueue huffNodeQueue = new NodeQueue();
        for(char c: charFreqMap.keySet()) {
            HuffNode tempNode = new HuffNode(charFreqMap.getValue(c),c);
            huffNodeQueue.add(tempNode);
        }

        return huffNodeQueue;
    }

    /**
     * Builds the Huffman tree using the Nodes from the given PriorityQueue.
     * 
     * @param huffNodeQueue  PriorityQueue of Nodes with front being the least frequent
     * @return  The root node of the priority queue
     */
    private HuffNode buildHuffmanTree(NodeQueue huffNodeQueue) {
        HuffNode left = huffNodeQueue.poll();
        HuffNode right = huffNodeQueue.poll();
        // Will always be at least 2 nodes in queue by Huffman algorithm
        HuffNode parent = new HuffNode(left, right);

        if(huffNodeQueue.isEmpty()) {
            return parent;
        }
        else {
            huffNodeQueue.add(parent);  // so always at least 2 nodes to compare
            return buildHuffmanTree(huffNodeQueue);
        }
    }
    
    /**
     * Makes the key used for Huffman encoding using the Huffman tree starting 
     * at rootNode. Specifically, this function recursively goes through the
     * Huffman tree from rootNode and assigns the characters found at leaf nodes
     * in the Huffman tree to bit Strings based on where they are in the tree.
     * 
     * @param curNode is the Node currently iterated on in the Huffman tree
     * @param bitStr the current bit string based on the past taken in the tree
     */
    private void makeKey(HuffNode curNode, String bitStr) {
        // When at a leaf node, adds the character from node to key + bitStr
        if(curNode.getLeft()==null && curNode.getRight()==null) {
            key.put(curNode.getChar(), bitStr);
        }
        else {
            makeKey(curNode.getLeft(), bitStr+0);  // left in the tree -> 0
            makeKey(curNode.getRight(), bitStr+1);  // right in the tree -> 1
        }
        
    }
}
