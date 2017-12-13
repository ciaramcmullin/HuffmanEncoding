# HuffmanEncoding
A project done in my Data Structures class during the Fall 2017. This program takes a text file, encodes it into Huffman encoding, then decodes it back into the text file.

In this two part assignment, I implement an efficient scheme for compressing a text
message called Huffman coding. A simple method to encode text as a string of 0s and 1s is to
represent each character as a unique string of 8 binary digits (bits). A text message is thus
translated into a string of bits. By exploiting the fact that not all characters appear with the same
frequency in the text, we can encode rarely used characters with long codes and frequently
used ones with short codes.
I use a given a set of characters and their corresponding frequencies to create an
optimal coding scheme: a special binary tree where the path to each leaf represents a different
character. I then take a text file and, using a Huffman Tree, encode/decode its
message.
