/**
 * Created by ciaramcmullin on 11/23/17.
 *
 * A program that creates a Huffman Node from letter and frequency or from its two children
 *
 */
public class HuffmanNode implements Comparable {

    public String letter;
    public Double frequency;
    public HuffmanNode left, right;

    /**
     * constructor for HuffmanNode
     * @param letter
     * @param frequency
     */
    public HuffmanNode(String letter, Double frequency){

        this.letter = letter;
        this.frequency = frequency;
        left = null;
        right = null;
    }

    /**
     * constructor for HuffmanNode
     * @param letter
     * @param frequency
     */
    public HuffmanNode (String letter, String frequency){
        this.letter = letter;
        this.frequency = Double.parseDouble(frequency);
    }

    /**
     * constructor for HuffmanNode
     * @param left
     * @param right
     */
    public HuffmanNode(HuffmanNode left, HuffmanNode right){
        this.left = left;
        this.right = right;
        this.letter = left.letter + right.letter;
        this.frequency = left.frequency + right.frequency;
    }

    /**
     * method that casts Object o into HuffmanNode and then uses compareTo that allows a heap of HuffmanNodes to be made
     * where the frequency determines which node is larger than which
     * @param o
     * @return compareTo value
     */
    public int compareTo(Object o){

        HuffmanNode huff = (HuffmanNode) o;
        return this.frequency.compareTo(huff.frequency);

    }

    /**
     * method that returns string of form "<" + letter + ", " + frequency + ">"
     * @return String
     */
    public String toString(){
        return "<" + letter + "," + frequency + ">";


    }
}
