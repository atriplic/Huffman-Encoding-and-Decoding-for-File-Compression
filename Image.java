/*
 * File: Image.java
 * Author: Elizabeth Kight
 * Purpose: To encode and decode a given JPG image.
 */

//Imports for the file reading and writing.
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/*
 * This class is used to read, and store a JPG image. It will then encode the necessary frenquencies 
 * and store those into a seperate file with the encoding. For decoding, it will take the encoding 
 * from a given file path and proceed to rebuild the image from the given RGB values from the decoding.
 */
public class Image {
    private Scanner scanner;
    private static int width, height;

    public Image() {
        scanner = new Scanner(System.in);
    }

    // closes class scanner
    public void closeScanner() {
        scanner.close();
    }

    /*
     * This will be used to take in the JPG image and to go through and get the frequencies of the 
     * image. It will then convert these frequencies into a encoded line and will store it into a 
     * new file. It will then return the hashTable that containes all of the keys and individual values.
     */
    public HashTable<Character, String> encodeImage(String filePath) throws IOException{
        System.out.println("Enter a file name to store the encoding: ");
        String fileName = scanner.nextLine();
        
        return encodeImage(filePath, fileName);
    }

    /*
     * Same as above encodeImage but takes output file from params
     */
    public HashTable<Character,String> encodeImage(String inputFile, String outputFile) throws IOException {
        HashTable<Character, String> JPGTable = readMyImage(inputFile);

        String text = concatenateFrequencies(JPGTable);

        Encoder encoding = new Encoder();
        createAndWriteEncoded(outputFile, encoding.encode(text));
        return encoding.getKey();
    }

    /*
     * This will be used to go through an encoded string file that contains the frequencies to the image. 
     * It will then go through and take those encoded frequencies and decode them to then be used in the 
     * next functions that will convert it to an image and to save it to a new file. This new file will 
     * have a name given by the user.
     * 
     * Assumes file saves encosion is on the first line only (as done in encodeImage())
     */
    public void fileDecoder(String filePath, HashTable<Character, String> encodedFreq) throws IOException{
        //User input for the decoded file name
        System.out.println("Enter a file name to store the decoded file: ");
        String fileName = scanner.nextLine();

        //This will access the given file path to read
        File file = new File(filePath);
        Scanner scan = new Scanner(file);

        // gets encoded line from file
        String allStrings = scan.nextLine();
        
        //Here the decoding will be done and placed onto the create and write file methods.
        // adding the encoding key as the parameter in the Decoder so that it can build the huffman tree
        Decoder decoding = new Decoder(encodedFreq);
        String[] allDecoding = decoding.decode(allStrings).split("\n");
 
        scan.close();
        
        //Passes the new file name and the HashTable to create a new encoded file
        createAndWriteDecoded(fileName, allDecoding);
    }

    /*
     * This will be used to read the image file and to get the necessary width and height of the image. 
     * It will then move onto another function that will get the frequencies from the JPG image.
     */
    public HashTable<Character, String> readMyImage(String filePath){
        // Read the image file
        BufferedImage passedImage;
        try {
            passedImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Gets the information off the image. Pulls each pixel from the 
        //image to also get the RGB from it.
        if (passedImage != null) {
        //Gets the width and height of the image
            width = passedImage.getWidth();
            height = passedImage.getHeight();
        } else {
            System.out.println("Image was not passed properly.");
            return null;
        }
        //This will get the frequencies or the RGB from the JPG image.
        HashTable<Character, String> imageTable = getFrequencies(passedImage, width, height);

        //Returns the frequencies from the JPG image
        return imageTable;
    }

    /*
     * This will get the frequencies from the JPG image and return a hash table of the frequencies 
     * in a string that will be used later on.
     */
    private static HashTable<Character, String> getFrequencies(BufferedImage input, int w, int h) {
        // Calculate the frequency of each pixel value in the image
        // Return a map with pixel values as keys and their frequencies as values
        // Hash table to store RGB frequencies
        HashTable<Character, String> fTable = new HashTable<Character, String>();

        // Loop through each pixel and get the RGB frequencies
        int key = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = input.getRGB(x, y);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                String rgbString = "[" + String.valueOf(red) + "," + String.valueOf(green) + "," + String.valueOf(blue) + "]";
                fTable.put((char)key, rgbString);
                key++;
            }
        }

        return fTable;
    }

    /*
     * This will create the new file with the given file name and the given string. Once 
     * finished, it will tell you if it was successfully created and written into or not.
     */
    public void createAndWriteEncoded(String fileName, String word) throws IOException {
        // Creates the new file
        File file = new File(fileName);

        //Writes in the new string into the new file
        try {
            FileWriter myWriter = new FileWriter(file);

            myWriter.write(word);
            myWriter.close();
            System.out.println("Successfully written to the file.");
        } 
        catch (IOException e) {
            System.out.println("An error occurred. Could not write to file.");
            e.printStackTrace();
        }
    }

    /*
     * This will create the new file with the given file name and input the information from 
     * the hash table. Once finished, it will tell you if it was successfully created and 
     * written into or not.
     */
    public void createAndWriteDecoded(String fileName, String[] allDecoded) throws IOException {
        //Creates new file
        String JPGName = fileName;
        if(!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg"))
            JPGName += ".jpg";

        // Decode Huffman codes and reconstruct the image
        BufferedImage reconstructedImage = decodeAndReconstruct(allDecoded);

        // Save the reconstructed image to a file
        saveImage(reconstructedImage, JPGName);
    }

    /**
     * This method takes a HashTable and concatenates all of the values together into one string.
     * @return A string is returned
     */
    public String concatenateFrequencies(HashTable<Character, String> table) {
    	// reading through the file
        StringBuilder content = new StringBuilder();
        // reading through each line and combining the strings together
        for (int i = 0; i < table.size(); i++) {
            // add the line break also
            content.append(table.getValue((char)i)).append("\n");
        }
        // return the toString
        String result = String.valueOf(content);
        return result;
    }

    /*
     * This will work in how it will take the decoded hash table and seperate it into a int[][][] with 
     * the proper rgb values in it. This will then work to turn it over into the proper rgb values that 
     * will then be assigned within the image and will reconstruct it. This function will return this 
     * reconstructed image.
     */
    private static BufferedImage decodeAndReconstruct(String[] allDecoded) {
        HashTable<Integer, int[]> rgbVals = new HashTable<Integer, int[]>();

        //Turning the hash table values into rgb storage and integers
        for (int i = 0; i < allDecoded.length; i++){
            int[] storage = new int[3];
            String cur = allDecoded[i].replace("[", "").replace("]", "");
            String[] ints = cur.split(",");

            storage[0] = Integer.valueOf(ints[0]);
            storage[1] = Integer.valueOf(ints[1]);
            storage[2] = Integer.valueOf(ints[2]);

            rgbVals.put(i, storage);
        }

        BufferedImage recoImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int counter = 0;
        //This will convert the current values into the proper rgb values
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //Getting the decoded values
                int redVal = rgbVals.getValue(counter)[0];
                int greenVal = rgbVals.getValue(counter)[1];
                int blueVal = rgbVals.getValue(counter)[2];
                counter++;
                
                //Convert the pixel value to the corresponding rgb values
                int rgb = (redVal << 16) | (greenVal << 8) | blueVal;

                // Set the RGB values for the reconstructed image
                recoImage.setRGB(x, y, rgb);
            }
        }
        //Return the reconstructed image
        return recoImage;
    }

    /*
     * This will be used to save the Image into the desired loccation.
     */
    private static void saveImage(BufferedImage image, String outPath) {
        try {
            File outputImage = new File(outPath);
            ImageIO.write(image, "jpg", outputImage);
            System.out.println("Reconstructed image saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
