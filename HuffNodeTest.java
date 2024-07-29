import java.util.*;

public class HuffNodeTest {
    private static int[] leafFreq, internalFreq;
    private static char[] leafChars;
    private static HuffNode[] leafNodes, internalNodes;

    public static void main(String[] args) {
        Random rand = new Random();
        leafFreq = new int[3];
        internalFreq = new int[3];
        leafChars = new char[3];

        leafNodes = genLeafNodes(rand);
        internalNodes = genInternalNodes();

        test();
    }

    private static void test() {
        if(testFrequency()) {
            System.out.println("All getFrequency() tests passed.");
        }
        if(testChar()) {
            System.out.println("All getChar() tests passed.");
        }
        if(testCompareTo()) {
            System.out.println("All compareTo() tests passed.");
        }
        if(testLeftRight()) {
            System.out.println("All getLeft() and getRight() tests passed.");
        }
    }

    private static boolean testFrequency() {
        boolean allPassed = true;

        for(int i = 0; i < 3; i++) {
            if(leafNodes[i].getFrequency() != leafFreq[i]) {
                System.out.println("Leaf node should have frequency ("+leafNodes[i].getFrequency()+") but it has the frequency ("+leafFreq[i]+").");
                allPassed = false;
            }
        }


        if(internalNodes[0].getFrequency() != internalFreq[0]) {
            System.out.println("Internal node with 2 leaf nodes for children has incorrect frequency.");
            allPassed = false;
        }

        if(internalNodes[1].getFrequency() != internalFreq[1]) {
            System.out.println("Internal node with 1 leaf node and 1 internal node for children has incorrect frequency.");
            allPassed = false;
        }

        if(internalNodes[2].getFrequency() != internalFreq[2]) {
            System.out.println("Internal node with 2 internal nodes for children has incorrect frequency.");
            allPassed = false;
        }

        return allPassed;
    }

    private static boolean testChar() {
        boolean allPassed = true;

        for(int i = 0; i < 3; i++) {
            if(leafNodes[i].getChar() != leafChars[i]) {
                System.out.println("Leaf node is returning character '"+leafNodes[i].getChar()+"' when it should be returning the character '" + leafChars[i]+"'.");
                allPassed = false;
            }
        }

        for(int i = 0; i < 3; i++) {
            if(internalNodes[i].getChar() != 0) {
                System.out.println("Internal node is returning character '"+internalNodes[i].getChar()+"' instead of '0'.");
                allPassed = false;
            }
        }

        return allPassed;
    }

    private static boolean testLeftRight() {
        boolean allPassed = true;

        if(internalNodes[0].getLeft().compareTo(leafNodes[0])!=0) {
            System.out.println("Internal node with 2 leaf nodes for children has the wrong left node.");
            allPassed = false;
        }
        if(internalNodes[0].getRight().compareTo(leafNodes[1])!=0) {
            System.out.println("Internal node with 2 leaf nodes for children has the wrong right node.");
            allPassed = false;
        }

        if(internalNodes[1].getLeft().compareTo(leafNodes[2])!=0) {
            System.out.println("Internal node with 1 leaf node and 1 internal node for children has the wrong left node.");
            allPassed = false;
        }
        if(internalNodes[1].getRight().compareTo(internalNodes[0])!=0) {
            System.out.println("Internal node with 1 leaf node and 1 internal node for children has the wrong right node.");
            allPassed = false;
        }

        if(internalNodes[2].getLeft().compareTo(internalNodes[0])!=0) {
            System.out.println("Internal node with 2 internal nodes for children has the wrong left node.");
            allPassed = false;
        }
        if(internalNodes[2].getRight().compareTo(internalNodes[1])!=0) {
            System.out.println("Internal node with 2 internal nodes for children has the wrong right node.");
            allPassed = false;
        }

        return allPassed;
    }

    private static boolean testCompareTo() {
        boolean allPassed = true;

        // Tests to make sure node comparisons are reflexive
        for(int i = 0; i < 3; i++) {
            if(leafNodes[i].compareTo(leafNodes[i])!=0) {
                System.out.println("Leaf node comparison is not reflexive.");
                allPassed = false;
            }
        }
        for(int i = 0; i < 3; i++) {
            if(internalNodes[i].compareTo(internalNodes[i])!=0) {
                System.out.println("Internal node comparison is not reflexive.");
                allPassed = false;
            }
        }

        // Tests how leaf nodes compare to each other & that comparisons are symmetric
        for(int i = 0; i < 2; i++) {
            for(int j = i++; j < 3; j++) {
                if(leafNodes[i].getFrequency() < leafNodes[j].getFrequency()) {
                    if(leafNodes[i].compareTo(leafNodes[j]) >= 0) {
                        System.out.println("Leaf node ("+leafFreq[i]+") is meant to compare as less than leaf node ("+leafFreq[j]+") but doesn't.");
                        allPassed = false;
                    }
                    else if(leafNodes[j].compareTo(leafNodes[i]) <= 0) {
                        System.out.println("Leaf node comparison is not symmetric.");
                        allPassed = false;
                    }

                }
                else if(leafNodes[i].getFrequency() > leafNodes[j].getFrequency()) {
                    if(leafNodes[i].compareTo(leafNodes[j]) <= 0) {
                        System.out.println("Leaf node ("+leafFreq[i]+") is meant to compare as greater than leaf node ("+leafFreq[j]+") but doesn't.");
                        allPassed = false;
                    }
                    else if(leafNodes[j].compareTo(leafNodes[i]) >= 0) {
                        System.out.println("Leaf node comparison is not symmetric.");
                        allPassed = false;
                    }
                }
                else {
                    if(leafNodes[i].compareTo(leafNodes[j]) != 0) {
                        System.out.println("Leaf node ("+leafFreq[i]+") is meant to compare as equal to leaf node ("+leafFreq[j]+") but doesn't.");
                        allPassed = false;
                    }
                    else if(leafNodes[j].compareTo(leafNodes[i]) != 0) {
                        System.out.println("Leaf node comparison is not symmetric.");
                        allPassed = false;
                    }
                }
            }
        }

        // Tests to make sure internal nodes always compare to be > children
        if(internalNodes[0].compareTo(leafNodes[0]) < 0 || internalNodes[0].compareTo(leafNodes[1]) < 0) {
            System.out.println("Internal node ("+internalFreq[0]+") with 2 leaf nodes ("+leafFreq[0]+","+leafFreq[1]+") for children is not comparing as greater than both its children.");
            allPassed = false;
        }
        if(internalNodes[1].compareTo(leafNodes[2]) < 0 || internalNodes[1].compareTo(internalNodes[0]) < 0) {
            System.out.println("Internal node ("+internalFreq[1]+") with 1 leaf node ("+leafFreq[2]+") and 1 internal node ("+internalFreq[0]+") for children is not comparing as greater than both its children.");
            allPassed = false;
        }
        if(internalNodes[2].compareTo(internalNodes[0]) < 0 || internalNodes[2].compareTo(internalNodes[1]) < 0) {
            System.out.println("Internal node ("+internalFreq[2]+") with 2 internal nodes ("+internalFreq[0]+","+internalFreq[1]+") for children is not comparing as greater than both its children.");
            allPassed = false;
        }

        return allPassed;
    }

    private static HuffNode[] genLeafNodes(Random rand) {
        char c = 'a';
        HuffNode[] nodes = new HuffNode[3];
        for(int i = 0; i < 3; i++) {
            leafFreq[i] = rand.nextInt(100);;
            leafChars[i] = c++;
            nodes[i] = new HuffNode(leafFreq[i], leafChars[i]);
        }

        return nodes;
    }

    private static HuffNode[] genInternalNodes() {
        HuffNode[] nodes = new HuffNode[5];
        
        // internal node w/ 2 leaf nodes for children
        internalFreq[0] = leafFreq[0] + leafFreq[1];
        nodes[0] = new HuffNode(leafNodes[0], leafNodes[1]);
        // internal node w/ 1 leaf node & 1 internal node for children
        internalFreq[1] = leafFreq[2] + internalFreq[0];
        nodes[1] = new HuffNode(leafNodes[2], nodes[0]);
        // internal node w/ 2 internal node for children
        internalFreq[2] = internalFreq[0] + internalFreq[1];
        nodes[2] = new HuffNode(nodes[0], nodes[1]);

        return nodes;
    }
}
