# Huffman Encoding and Decoding for File Compression

## Overview
This project aims to demonstrate the effectiveness of Huffman Encoding and Decoding in compressing and decompressing various file types, including text and image files. The program provides evidence of the code's functionality by displaying the name and size of the file before and after encoding and decoding. The workflow involves the use of specific classes and data structures to handle the compression and reconstruction of files.

## Files Included
Along with all the code files needed for the program, we have also included a video recording(presentation_rec.mp4) of our team walking through our presentation(345 Project Final Presentation.ppt) and also a video(code_demonstration.mp4) of our team giving a code demonstration of how the compression and decompression works on a sample text and image file. Finally we have also included a Work Division Explanation document that explains what each of us members worked on and how we divided work within our team.

## Running the program
To run the program, go to Main.java. Before running the main, make sure that there is one command line argument, which is the input file that needs to be encoded/decoded. Once you run the program you will be asked to enter two things: The file name to put the encoded information in, and the file name to put the decoded information in. Once you do this and the program is run, you can open the two files that have the encoded and decoded information to make sure it works as expected. We showcase compression and decompression within Text.java and Image.java to store encoded and decoded information in separate new files which in the end show compression.

## Code Workflow
Main: Determines the type of file (text or image) and calls specific methods from the text/image classes based on that.
Text/Image Classes: These classes take a file, call the encoding and decoding methods, and write all the compressed/reconstructed information in new files.
Encoder/Decoder Classes: The encoder class compresses the data, and the decoder class reconstructs the compressed information.
HashTable/Priority Queue/Huffman Tree: These data structures are used to store information.

## Text Compression Workflow
Information is taken from the file and concatenated into one string.
The string is plugged into the encoder. The HashTable and Priority Queue get built using the information from the string.
Encoding: These data structures along with the Huffman Tree are used to compress the data.
The user is asked to enter a file name, and the program writes the compressed data into that file.
Decoding: Using the previous information, the compressed data gets decoded and reconstructed into what it originally was.
The user is asked to enter another file name, and the program writes the reconstructed data into that file.

## Image Compression Workflow
The image is read, and the width/height get saved. The HashTable is created with pixel values as the keys and frequencies as the values, then all this information is concatenated into one string.
The string is plugged into the encoder. The HashTable and Priority Queue get built using the information from the string.
Encoding: These data structures along with the Huffman Tree are used to compress the data.
The user is asked to enter a file name, and the program writes the compressed data into that file.
Decoding: Using the previous information, the compressed data gets decoded and reconstructed into the image that it originally was.
The user is asked to enter another file name, and the reconstructed image gets saved with that name.

## Test Files
The repository includes various test files such as ImageTest, TextTest, EncoderTest, DecoderTest, and EncoderDecoderTest.
