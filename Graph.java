import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Node {
    int index;
    double profitValue;
    ArrayList<Node> children;
    int pebbles;
    int heights;

    public Node(int index, double profit, int height) {
        this.index = index;
        this.profitValue = profit;
        children = new ArrayList<>();
        pebbles = 0;
        heights = height;
    }

    public ArrayList<Node> getChildren() {
        return this.children;
    }

    public double getProfitValue() {
        return this.profitValue;
    }

    public double getIndex() {
        return this.index;
    }

    public int getHeights() {
        return this.heights;
    }
}

class BinaryTree {
    int currentMax = 0;
    int counter = 0;
    int distraction = 4;
    Node root;

    int maxDepth(Node node) {
        if (node == null) {
            return 0;
        }
        return 1;
    }
}

public class Graph {
    public int counter = 0;
    public int G_adj[][]; //adjacency matrix of the graph
    public double profit[]; //profits of each node
    public int n; //number of vertices
    public int heights = 0;
    public final int reset = 0;
    boolean[] visited;
    Node nextNode;
    Node store;
    int index = 0;
    public int tree[];
    public Node rootNode;
    public char[] parentChild;
    List<Integer> nodeTracker = new ArrayList<Integer>();
    List<Integer> list = new ArrayList<>();
    int newHeight = 0;
    int currentMax = 0;
    int printed = 0;
    int height =0;

    /**
     * Take filename as parameter to the constructor
     * The file contains graph information as follows
     * first line contains, n, the number of vertices
     * then there is a nxn matrix of 0s and 1s indicating the adjacency matrix
     * finally there are n entries of double marking the profit
     * store vertex number in a variable and load adjcency matrix information into a 2d array
     * load profit information into an 1d array
     *
     * @param filename
     */

    public Graph(String filename) {
        //implementations here
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            String line;
            line = br.readLine();
            n = Integer.parseInt(line);
            G_adj = new int[n][n];
            visited = new boolean[n];
            for (int i = 0; i < visited.length; i++) {
                visited[i] = false;
            }
            tree = new int[n];
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                // System.out.println(line + " index "+ i);
                String[] l = line.split(" ");
                for (int j = 0; j < l.length; j++) {
                    G_adj[i][j] = Integer.parseInt(l[j]);
                    //    visited[i][j] = false;
                }
            }
            line = br.readLine();
            // System.out.println(line + " MAYBE THIS IS WRONG");
            String[] ll = line.split(" ");
            for (int i = 0; i < ll.length; i++) {
                profit = new double[ll.length];
                profit[i] = Double.parseDouble(ll[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * The function takes a vertex and finds the height of the tree subrooted at that vertex
     * To find the height of the entire tree you will pass the vertex at which the tree is rooted.(aka root of
     * the tree)
     *
     * @param V Starting vertex V
     * @return height of the tree rooted at the vertex
     */
    public int height(int V) {
        rootNode = new Node(V, profit[V], 0);
        counter =0;
        currentMax = 0;
        heights = 0;
        //nextNode = new Node(V+1,profit[V+1]);
        visited = new boolean[n];
        rootNode.children.add(buildTree(V, rootNode));
        System.out.println(rootNode.index);
        System.out.println(rootNode.children.get(0).index);
        // System.out.println(rootNode.children.get(1).index);
        BinaryTree k = new BinaryTree();
//        System.out.println(root.children.get(0).index + " " + root.children.get(1).index);
        int count = 0;
        visited = new boolean[n];
        // System.out.println(rootNode.getChildren().size());

     // int current = depth(rootNode);
        //System.out.println(current + " FOUND");
        return currentMax;
    }

    public int depth(Node root) {


    }


    public Node buildTree(int index, Node lastNode){
        Node current = new Node(index, profit[index], heights);
        visited[index] = true;

        for (int i = rootNode.index; i < G_adj[index].length; i++) {

            if (!visited[i] && G_adj[index][i] == 1) {
                Node child = new Node(i, profit[i], heights);

                heights++;
                lastNode.children.add(buildTree(i, child));
                heights--;
               // if (lastNode.children.size()==0){
                 //   if (currentMax <heights){
                   //     currentMax = heights;
                     //   heights =0;
                    //}
               // lastNode.children.add(child);
                System.out.println(lastNode.index + " is the parent of " + child.index + " height counter " + heights);
              //  if (currentMax < heights) {
                //    currentMax = heights;
                //}
               // heights = 0;
                // if (lastNode.index == rootNode.index){
                //    //currentMax = 0;
                //  heights =0;
                //}
              //  System.out.println(heights + " HEIGHT");

                //  System.out.println(heights + " CHECK HERE FOR 88");
                //buildTree(i);
                //current.children.get(index).children.add(child);

            } else if (visited[i] && lastNode.children.size()==0) {
                System.out.println("HAPPENED");
                //heights--;
            }

            // heights++;
            System.out.println(lastNode.heights + " add these up");


            //   buildTree(i);
            //current.children.add(child);


            //  System.out.println(heights + " NOE RIGHt");


            System.out.println("HEIGHTS " + heights);
            System.out.println(lastNode.index + " CURR");
            if (!(lastNode.children.isEmpty())) {
                System.out.println(lastNode.children.get(0).index + " CHILD");
            }
            //   root.children.add(current);
         //   if (visited[i] && lastNode.index == rootNode.index) {
           //     heights++;
            //}
        }
        if (heights > currentMax) {
            currentMax = heights;

        }

        //heights--;
        //  heights++;
        System.out.println(lastNode.index + " IND");
        // System.out.println(lastNode.children.get(0));
        //System.out.println(current.children.get(1));
        // heights--;
        return lastNode;

    }

    // rootNode.children.add(current);
    // System.out.println(rootNode.getChildren().size());
    //  return current;


    /**
     * The function takes a vertex that would be used to make rooted tree
     * make a rooted tree with V
     * Then write your implementations to find a set of vertices that returns maximum profit
     *
     * @param V starting vertex V
     * @return ArrayList of Integers
     */
    public ArrayList<Integer> pebbling(int V) {
        ArrayList<Integer> pebbbled = new ArrayList<Integer>();

        //Implementations here


        return pebbbled;
    }


    public static void main(String[] args) {
        // Check your implementation here
        // you d not need to implement this
        System.out.println("Main function: ");
    }
}


