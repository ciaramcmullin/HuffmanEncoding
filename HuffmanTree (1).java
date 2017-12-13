import java.io.IOException;

/**
 * Created by ciaramcmullin on 11/23/17.
 *
 * A program that creates a Binary Heap from HuffmanNodes, and then a Huffman Tree from these nodes
 *
 */
public class HuffmanTree {

    HuffmanNode root;

    /**
     * constructor for huffman Tree
     * @param huff
     */
    public HuffmanTree(HuffmanNode huff){
        this.root = huff;
    }

    /**
     * method that calls private method printLegend
     */
    public void printLegend(){

        printLegend(root, "");

    }

    /**
     * recursive method that prints out legend
     * @param t
     * @param s
     */
    private void printLegend(HuffmanNode t, String s){

        if (t.letter.length() > 1){

            printLegend(t.left, s + "0");
            printLegend(t.right, s + "1");
        }

        else {
            System.out.println(t.letter + "=" + s);
        }
    }

    /**
     * method that takes legend and creates heap from it
     * @param legend
     * @return heap or null
     */
    public static BinaryHeap legendToHeap(String legend){

        try {

            String[] split = legend.split(" ");

            HuffmanNode[] huffs = new HuffmanNode[split.length / 2];
            int i = 0;
            int j = 0;
            while( i < split.length )
            {
                huffs[ j ] = new HuffmanNode( split[i], Double.parseDouble(split[i + 1]));

                i = i + 2;
                j++;
            }

            BinaryHeap heap = new BinaryHeap( huffs );
            return heap;

        } catch(UnderflowException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * method that creates HuffmanTree from BinaryHeap
     * @param b
     * @return huffTree or null
     */
    public static HuffmanTree createFromHeap(BinaryHeap b) {

        try {
            while (b.getSize() > 1) {

                HuffmanNode min1 = (HuffmanNode) b.deleteMin();
                HuffmanNode min2 = (HuffmanNode) b.deleteMin();
                HuffmanNode huff = new HuffmanNode(min1, min2);
                b.insert(huff);

            }

            HuffmanTree huffTree = new HuffmanTree( (HuffmanNode) b.deleteMin() );
            return huffTree;

        }
        catch (UnderflowException e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * main method
     * @param args
     */
    public static void main(String[] args){

        String legend = "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
        BinaryHeap bheap = legendToHeap(legend);
        bheap.printHeap();
        HuffmanTree htree = createFromHeap(bheap);
        htree.toString();
        htree.printLegend();

    }
}
