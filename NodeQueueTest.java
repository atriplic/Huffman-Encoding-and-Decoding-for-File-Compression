/*
 * Authors: Madison Ryan
 * File: NodeQueueTest.java
 * Description: This class is used to test the NodeQueue class.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NodeQueueTest {
    public static void main(String[] args) {
        HuffNode[] nodeArr = genHuffNodes();
        if(testSize()) {
            System.out.println("All size() tests passed!");
        }
        if(testPeek(nodeArr)) {
            System.out.println("All peek() tests passed!");
        }
        NodeQueue testQueue = new NodeQueue();
        if(testAdd(testQueue, nodeArr)) {
            System.out.println("All add() tests passed!");
        }
        if(testPoll(testQueue)) {
            System.out.println("All poll() tests passed!");
        }

    }

    /**
     * Tests whether the size() function for NodeQueue is accurate. Specifically
     * ensures that it works through a resize of the array used in the class.
     */
    private static boolean testSize() {
        System.out.println("Starting tests on size()...");
        boolean allPassed = true;
        NodeQueue testQueue = new NodeQueue();
        HuffNode testNode = new HuffNode(10, 'a');

        if(testQueue.size() != 0) {
            System.out.println("Node Queue is not instantiated with size 0.");
            allPassed = false;
        }

        testQueue.add(testNode);
        if(testQueue.size() != 1) {
            System.out.println("Incorrect size() return for when size should be 1.");
            allPassed = false;

        }

        // 11 is the value of the first resize, so makes sure size() works through that point
        for(int i = 0; i <= 10; i++) {
            testQueue.add(testNode);
        }
        if(testQueue.size() != 12) {
            System.out.println("Incorrect size() return ("+testQueue.size()+") for when size should be 11.");
            allPassed = false;
        }

        return allPassed;
    }

    private static boolean testPeek(HuffNode[] nodeArr) {
        System.out.println("Starting tests on peek()...");
        boolean allPassed = true;
        NodeQueue testQueue = new NodeQueue();
        HuffNode testNode = new HuffNode(10, 'a');

        if(testQueue.peek() != null) {
            System.out.println("Non-null peek() return when size is 0");
            allPassed = false;
        }

        testQueue.add(testNode);
        if(testQueue.peek().getFrequency() != 10) {
            System.out.println("Incorrect peek() return when size is > 0");
            allPassed = false;
        }

        return allPassed;
    }

    /**
     * Tests whether the add() function for NodeQueue is accurate. Specifically
     * 
     * @return
     */
    private static boolean testAdd(NodeQueue testQueue, HuffNode[] nodeArr) {
        System.out.println("Starting tests on add()...");
        boolean allPassed = true;

        for(int i = 0; i < 11; i++) {
            testQueue.add(nodeArr[i]);
            if(testQueue.size() != i+1) {
                System.out.println("add() did not work/increase size for "+(i+1)+" element.");
                allPassed = false;
            }

            //testQueue.printCurrentOrder();
        }

        if(testQueue.peek().getFrequency() != 1) {
            System.out.println("add() did not sort/prioritize properly because frequency 1 is not at front of queue");
            allPassed = false;
        }

        return allPassed;
    }

    /**
     * Tests whether the poll() function for NodeQueue is accurate. Specifically
     * ensures that it works through a resize of the array used in the class.
     */

    private static boolean testPoll(NodeQueue testQueue) {
        System.out.println("Starting tests on poll()...");
        boolean allPassed = true;
        HuffNode curNode = testQueue.poll();
        for(int i = 1; i <= 11; i++) {
            if(curNode.getFrequency() != i) {
                System.out.println("poll() returned "+curNode.getFrequency()+" when it should have returned "+i+".");
                allPassed = false;
            }
            curNode = testQueue.poll();
        }

        if(curNode != null) {
            System.out.println("poll() on empty queue returned non-null value.");
            allPassed = false;
        }
        return allPassed;
    }

    private static HuffNode[] genHuffNodes() {
        HuffNode[] nodeArr = new HuffNode[11];
        int freq = 1;
        char c = 'a';

        // generates an array of HuffNodes w/ frequencies 1-10
        for(int i = 0; i < 11; i++) {
            nodeArr[i] = new HuffNode(freq, c);
            freq++;
            c++;
        }

        // shuffle the array
        List<HuffNode> nodeList = Arrays.asList(nodeArr);
        Collections.shuffle(nodeList);
        nodeList.toArray(nodeArr);

        System.out.println("The order of frequencies used is:");
        printOrder(nodeArr);

        return nodeArr;
    }

    // prints the order of frequencies in the array
    private static void printOrder(HuffNode[] nodeArr) {
        for(int i = 0; i < 11; i++) {
            System.out.print(nodeArr[i].getFrequency());
            if(i < 10)
                System.out.print(", ");
            else
                System.out.println();
        }
    }
}
