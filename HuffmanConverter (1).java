import java.io.*;

/**
 *
 * Ciara McMullin
 * 08 December, 2017
 * A program that converts a text file into HuffmanTree and then encoding and decodes the text file
 *
 */

public class HuffmanConverter {

    // The # of chars in the ACII table dictates the siz of the count[] and code[] arrays
    public static final int NUMBER_OF_CHARACTERS = 256;

    // the contents of our message...
    private String contents;

    // the tree created from the message
    private HuffmanTree huffmanTree;

    // tracks how often each character occurs
    private int count[];

    // the huffman code for each character
    private String code[];

    // stores the # of unique characters in content
    private int uniqueChars = 0;

    /**
     * constructor taking input String to be converted
     *
     * @param input
     */
    public HuffmanConverter(String input) {
        this.contents = input;
        this.count = new int[NUMBER_OF_CHARACTERS];
        this.code = new String[NUMBER_OF_CHARACTERS];
    }

    /**
     * records the frequencies that each character of our messages occurs
     */
    public void recordFrequencies() {
        for (char c : contents.toCharArray()) {
            int temp = (int) c;
            count[temp] += 1;
        }
    }

    /**
     * Converts our frequency list into a HuffmanTree
     * First creates a binary heap from the count[] list of frequencies. Then prints heap
     * and makes HuffmanTree from the binary heap
     */
    public void frequenciesToTree() {
        try {
            int num = 0;
            for (int i = 0; i < count.length; i++){
                if(count[i] != 0)
                    num++;
            }

            HuffmanNode[] huffs = new HuffmanNode[num];
            int huffCount = 0;
            for (int i = 0; i < count.length;i++) {
                if (count[i] != 0) {
                    String c = Character.toString((char)i);
                    huffs[huffCount] = new HuffmanNode(c, (double) count[i]);
                    huffCount++;
                }
            }
            BinaryHeap heap = new BinaryHeap(huffs);
            heap.printHeap();
            this.huffmanTree = HuffmanTree.createFromHeap(heap);

        } catch (UnderflowException e) {
            e.printStackTrace();
        }

    }

    /**
     * iterates over the huffmanTree to get the code for each letter
     */
    public void treeToCode() {

        for (int i = 0; i < code.length; i++) {
            code[i] = "";
        }
        treeToCode(huffmanTree.root, "");
    }

    /**
     * a method that converts HuffmanTree to
     * @param t
     * @param s
     */
    private void treeToCode(HuffmanNode t, String s) {

        if (t.left == null && t.right == null) {
            code[(int)t.letter.charAt(0)] = s;
            System.out.print(t.letter + "=" + s +" ");
        }
        else {
            treeToCode(t.left, s + "0");
            treeToCode(t.right, s + "1");
        }
    }

    /**
     * method that creates Huffman encoding for the message stores in contents
     * @return encoded message
     */
    public String encodeMessage() {
        String huffmanEncoded = "";
        for (int i = 0; i < contents.length(); i++) {
            huffmanEncoded += code[(int) contents.charAt(i)];
        }
        return huffmanEncoded;
    }

    public static String readContents(String filename){
        FileReader reader = null;
        String content = null;
        try {
            reader = new FileReader(filename);
            char[] chars = new char[(int) filename.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return content;
    }

    /**
     * method that decodes the encoded string
     * @param encodedStr
     * @return
     */
    public String decodeMessage(String encodedStr){
        String decoded = "";
        HuffmanNode base = huffmanTree.root;
        while (!encodedStr.isEmpty()) {
            if(encodedStr.charAt(0) == '1'){
                base = base.right;
                encodedStr = encodedStr.substring(1);
            }
            else {
                base = base.left;
                encodedStr = encodedStr.substring(1);
            }

            if (base.left == null && base.right == null){
                decoded += base.letter;
                base = huffmanTree.root;
            }
        }
        return decoded;
    }

    /**
     * main method
     * @param args
     */
    public static void main(String args[]) {
        String message = HuffmanConverter.readContents(args[0]);
        HuffmanConverter huffmanConverter = new HuffmanConverter(message);
        huffmanConverter.recordFrequencies();
        huffmanConverter.frequenciesToTree();
        huffmanConverter.treeToCode();
        String encoded = huffmanConverter.encodeMessage();
        System.out.print(encoded);
        System.out.print(huffmanConverter.decodeMessage(encoded));
    }
}