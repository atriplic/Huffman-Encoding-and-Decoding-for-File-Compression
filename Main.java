/*
 * File Name: Main.java
 * Author: Atul Triplicane, Abhyuday Singh, Madison Ryan, and Elizabeth Kight
 * Description: This is the main class that is used to connect everything together
 */

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter name of a file to encode: ");
		String fileType = scanner.nextLine().strip();

		if (fileText(fileType) == true) {
			System.out.println("This is a Text File");
			HashTable<Character, String> encodeKey = null;
			Text textProcessor = new Text();
			System.out.println("Enter name of file to write encode to: ");
			String fileName = scanner.nextLine();
			encodeKey = textProcessor.fileEncoder(fileType, fileName);
			textProcessor.fileDecoder(fileName, encodeKey);
			textProcessor.closeScanner();
		} 
		// If the user wants to decode an image
		else if (fileImage(fileType) == true) {
			System.out.println("This is an Image File");
			Image imageProcessor = new Image();
			System.out.println("Enter name of file to write encode to: ");
			String fileName = scanner.nextLine();
			HashTable<Character, String> encodeKey = null;
			encodeKey = imageProcessor.encodeImage(fileType, fileName);
			imageProcessor.fileDecoder(fileName, encodeKey);
			imageProcessor.closeScanner();
		} 
		else {
			System.out.println("The input is incorrect, please try again");
		}
		scanner.close();
	}

	// Checks if the file is a text file
	private static boolean fileText(String fileType) {
		String fileTypeLowerCase = fileType.toLowerCase();
		if (fileTypeLowerCase.endsWith(".txt") == true || fileTypeLowerCase.endsWith(".text") == true) {
			return true;
		} 
		else {
			return false;
		}
	}

	// Checks if the file is an image file
	private static boolean fileImage(String fileType) {
		String fileTypeLowerCase = fileType.toLowerCase();
		if (fileTypeLowerCase.endsWith(".jpeg") == true || fileTypeLowerCase.endsWith(".jpg") == true
				|| fileTypeLowerCase.endsWith(".png") == true || fileTypeLowerCase.endsWith(".gif") == true
				|| fileTypeLowerCase.endsWith(".bmp") == true) {
			return true;
		} 
		else {
			return false;
		}
	}
}
