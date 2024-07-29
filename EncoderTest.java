/*
 * Author: Madison Ryan
 * File: EncoderTest.java
 * Description: This class is used to test the Encoder class.
 */

public class EncoderTest {
    public static void main(String[] args) {
        constructorTest();
        encodeTest();
    }

    // Constructor test
    private static boolean constructorTest() {
        boolean retVal = true;
        Encoder emptyEncoder = new Encoder();
        Encoder testEncoder = new Encoder("Test input");

        if(!emptyEncoder.getInput().equals("")) {
            System.out.println("Empty constructor has non-empty input: " + emptyEncoder.getInput());
            retVal = false;
        }
        if(!testEncoder.getInput().equals("Test input")) {
            System.out.println("'Test input' constructor has non-empty input: " + testEncoder.getInput());
            retVal = false;
        }

        return retVal;
    }

    // Encode test for Encoder
    private static boolean encodeTest() {
        boolean retVal = true;
        Encoder testEncoder = new Encoder("This is a test input string");
        if(!testEncoder.encode().equals("01111011101101110011011100101100010001100111100001100101010001101100001111001010111001010111")) {
            System.out.println("Encoder for 'This is a test input string' is not encoding to the correct bit-string.");
            System.out.println(testEncoder.encode());
            testEncoder.getKey().printTable();
        }
        return retVal;
    }

    // getKey test for Encoder
    private static boolean getKeyTest() {
        boolean retVal = true;
        Encoder testEncoder = new Encoder("This is a test input string");
        HashTable<Character,String> key = testEncoder.getKey();

        return retVal;
    }

    private static boolean changeInputTest() {
        boolean retVal = true;
        

        return retVal;
    }   
}
