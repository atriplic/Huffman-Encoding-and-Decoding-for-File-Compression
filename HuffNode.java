/*
 * File: HuffNode.java
 * Author: Madison Ryan and Abhyuday Singh
 * Purpose: Class for the nodes of a Huffman tree.
 */

/**
 * A Huffman Node used in a Huffman tree as part of Huffman Coding.
 * 
 * Huffman nodes are either leaf nodes with a character value or internal nodes
 * with a left child and a right child. All nodes have a frequency value.
 */
public class HuffNode {
    private int freq;
    private char c; 
    private HuffNode left;
    private HuffNode right;

    /**
     * Constructs a leaf node for a Huffman tree, which represents the frequency
     * of a character.
     * 
     * Left and right child values will be set to null.
     * 
     * @param freq  the number of times the character appears
     * @param c  the character in question
     */
    public HuffNode(int freq, char c) {
        this.freq = freq;
        this.c = c;
        left = null;
        right = null;
    }

    /**
     * Constructs an internal node for a Huffman tree.
     * 
     * Frequency value will be the sum of children's frequency values. 
     * Character value will be set to 0.
     * 
     * @param left  non-null HuffNode to be the left child of this node
     * @param right  non-null HuffNode to be the right child of this node
     */
    public HuffNode(HuffNode left, HuffNode right) {
        this.left = left;
        this.right = right;
        freq = left.freq + right.freq;
        c = 0;
    }

    /** 
     * Gets the frequency of this node. If this is a leaf node, that is the 
     * number of times that the character appears. Or if it's an internal node, 
     * that is the sum of the frequencies of this node's children.
     */ 
    public int getFrequency() {
        return freq;
    }

    /** 
     * Gets the character for this node (if it's a leaf node). 
     * 
     * If this is an internal node, will return the 0 character (which may also 
     * appear as the character for a leaf node).
     */ 
    public char getChar() {
        return c;
    }

    /**
     * Gets the left child of this node (if it's an internal node).
     * 
     * If this is a leaf node, will return null.
     */
    public HuffNode getLeft() {
        return left;
    }

    /**
     * Gets the right child of this node (if it's an internal node).
     * 
     * If this is a leaf node, will return null.
     */
    public HuffNode getRight() {
        return right;
    }

    /**
     * Compares this node with the given node.
     * 
     * @param node  the node to compare with
     * @return  > 0 if this node is > the other node, = 0 if same,
     *          < 0 if < the other node
     */
    public int compareTo(HuffNode node) {
        return freq - node.getFrequency();
    }
}
