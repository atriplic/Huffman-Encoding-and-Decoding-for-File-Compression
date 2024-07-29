/*
 * File: Text.java
 * Author: Elizabeth Kight, Atul Triplicane
 * Purpose: To encode and decode either a given String or a given file into a coded set of a hashmaps.
 */

//Imports for the file reading and writing.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * This class will be used to encode and decode either a given String or a given file into a 
 * coded file. You will either give the string for that singular line, or the file path for 
 * the whole file to be encoded. This class includes the functions:
 *      singleEncoder
 *      fileEncoder
 *      fileDecoder
 *      createAndWrite
 *      createAndWriteSingle
 *      concatenateLines
 */

public class Text {
	
	// creating instance of Scanner to avoid line not found errors
	private Scanner scanner;
	
	public Text() {
        this.scanner = new Scanner(System.in);
    }

    public void closeScanner() {
        scanner.close();
    }
	
    /*
     * This class will be used to encode a singular line or text into a file. The user will 
     * supply the name of the encoding file to be used. 
     */
    public HashTable<Character, String> singleEncoder(String text) throws IOException{
    	System.out.println("Enter a file name to store the encoding: ");
        String fileName = scanner.nextLine();
        Encoder encoding = new Encoder();
        createAndWriteSingle(fileName, encoding.encode(text));
        return encoding.getKey();
    }

    /*
     * This will be taking in a file path to access the actual file to encode, and then it 
     * will take user input for storing the result in a file name that is given. 
     */
    public HashTable<Character, String> fileEncoder(String filePath) throws IOException {
        System.out.println("Enter a file name to store the encoding: ");
        String fileName = scanner.nextLine();

        // calling the method to combine the characters into one string
        String text = concatenateLines(filePath);

        Encoder encoding = new Encoder();
        createAndWriteSingle(fileName, encoding.encode(text));
        return encoding.getKey();
    }

    /*
     * Same as above fileEncode, but takes output file from params
     */
    public HashTable<Character, String> fileEncoder(String inputFile, String outputFile) throws IOException {
        System.out.println("Enter a file name to store the encoding: ");

        // calling the method to combine the characters into one string
        String text = concatenateLines(inputFile);

        Encoder encoding = new Encoder();
        createAndWriteSingle(outputFile, encoding.encode(text));
        return encoding.getKey();
    }

    /*
     * This will take in the current file path of the encoded file and work through each 
     * line and decode it and pass it into a HashTable to store the decoded line. It will 
     * then put the decoded lines into a new file that will store the decoded lines inside 
     * of it.
     */
    public void fileDecoder(String filePath, HashTable<Character, String> encodedHashMap) throws IOException{
    	// HashTable to store the decoded lines
    	HashTable<Character, String> allStrings = new HashTable<Character, String>();
    	HashTable<Character, String> allDecoding = new HashTable<Character, String>();

        //User input for the decoded file name
        System.out.println("Enter a file name to store the decoded file: ");
        String fileName = scanner.nextLine();

        //This will access the given file path to read
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        int lineCount = 0;

        //Reads through line by line
        while (scan.hasNextLine()){
            String line = scan.nextLine();

            //Decodes each line and places it into the HashTable
            allStrings.put((char)lineCount, line);
            lineCount++;
        }
        
        //Here the decoding will be done and placed onto the create and write file methods.
        // adding the hashtable as the parameter in the Decoder so that it can build the huffman tree
        Decoder decoding = new Decoder(encodedHashMap);
        for (int i = 0; i < allStrings.size(); i++){
            String decoded = decoding.decode(allStrings.getValue((char)i));
            allDecoding.put((char)i, decoded);
        }
 
        scan.close();
        
        //Passes the new file name and the HashTable to create a new encoded file
        createAndWrite(fileName, allDecoding);
    }

    /*
     * This will create the new file with the given file name and input the information from 
     * the hash table. Once finished, it will tell you if it was successfully created and 
     * written into or not.
     */
    public void createAndWrite(String fileName, HashTable<Character, String> table) throws IOException {
        //Creates new file
        File file = new File(fileName);
        try {
            //Access the file with a file writer
            FileWriter myWriter = new FileWriter(file);

            //Writes the information from the hash table into the file 
            for (int i = 0; i < table.size(); i++){
                myWriter.write(table.getValue((char)i));
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred. Could not write to file.");
            e.printStackTrace();
        }
  }
    
    /*
     * This will create the new file with the given file name and the given string. Once 
     * finished, it will tell you if it was successfully created and written into or not.
     */
    public void createAndWriteSingle(String fileName, String word) throws IOException {
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
    
    /**
     * This method takes a file and concatenates all of the lines together into one string
     * @param filePath
     * @return A string is returned
     */
    public String concatenateLines(String filePath) {
    	// reading through the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            // reading through each line and combining the strings together
            while ((line = reader.readLine()) != null) {
            	// add the line break also
                content.append(line).append("\n");
            }
            // return the toString
            String result = content.toString();
            return result;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return filePath;
    }
}
