/*
 * Name: Abhyuday Singh and Madison Ryan
 * File: EncoderDecoderTest.java
 * Description: This class is used to test the Encoder and Decoder classes.
 */
public class EncoderDecoderTest {
    public static void main(String[] args) {
        comboTest();
    }

    // Test the encoder and decoder together
    private static boolean comboTest() {
        Encoder testEncoder = new Encoder("This is a test input string");

        Decoder testDecoder = new Decoder(testEncoder.getKey());
        if(!testDecoder.decode(testEncoder.encode()).equals("This is a test input string")) {
            System.out.println("Decoder does not give the proper decoded string, instead gives: ");
        }
        else {
            System.out.println("Everything works!");
        }
        return true;
    }
}
