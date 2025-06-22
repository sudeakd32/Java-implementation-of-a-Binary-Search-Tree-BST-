import java.util.*;

class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
        left = right = null;
    }
}

class BinarySearchTree{
    Node root;

    public void insert(int key){
        Node newNode = new Node(key);

        if(root == null){
            root = newNode;
            return;
        }

        if(search(key) != null) return;

        Node current = root;
        Node parentOfInsertedNode = null;
        while(current != null){
            parentOfInsertedNode = current;
            if(key < current.data)
                current = current.left;
            else if(key > current.data)
                current = current.right;
        }

        if(key > parentOfInsertedNode.data)
            parentOfInsertedNode.right = newNode;

        else
            parentOfInsertedNode.left = newNode;

    }

    public Node search(int key){
        Node current = root;

        while(current != null){
            if(key == current.data)
                return current;
            else if (key < current.data)
                current = current.left;
            else
                current = current.right;
        }

        return null;
    }

    public void delete(int key){

        if(search(key) == null)
            return;

        Node toBeDeletedNode = search(key);

        //if node that we will delete is leaf node
        if(toBeDeletedNode.left == null && toBeDeletedNode.right == null){
            deleteLeafNode(key);
        }

        //Node with one child
        else if(toBeDeletedNode.left == null || toBeDeletedNode.right == null){
            deleteNodeWithOneChild(toBeDeletedNode);
        }

        //Node with two child
        else{
            Node successor = findInOrderSuccessor(toBeDeletedNode.right);
            //if successor is leaf node
            if(successor.left == null && successor.right == null)
                deleteLeafNode(successor.data);

                //if successor has one child
            else if(successor.left == null || successor.right == null)
                deleteNodeWithOneChild(successor);

            toBeDeletedNode.data = successor.data;
        }

    }

    private Node findParent(Node node) {

        if (node == root) return null;

        Node current = root;
        Node parent = null;
        while (current != null && current != node) {
            parent = current;
            if (node.data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return parent;
    }

    private void deleteLeafNode(int key){

        Node current = root;
        Node parentOfDeletedNode = null;

        if(current == null) return;

        while(current != null){
            parentOfDeletedNode = current;
            if( (current.left != null && key == current.left.data)
                    || (current.right != null && key == current.right.data) )
                break;
            else if (key < current.data)
                current = current.left;
            else
                current = current.right;
        }

        if(parentOfDeletedNode.left != null && parentOfDeletedNode.left.data == key){
            parentOfDeletedNode.left = null;
        }
        else if(parentOfDeletedNode.right != null && parentOfDeletedNode.right.data == key)
            parentOfDeletedNode.right = null;


    }

    private void deleteNodeWithOneChild(Node toBeDeletedNode){

        Node parent = findParent(toBeDeletedNode);
        Node child = (toBeDeletedNode.left != null) ? toBeDeletedNode.left : toBeDeletedNode.right;

        if (parent == null)
            root = child; // Node is root
        else if (parent.left == toBeDeletedNode)
            parent.left = child;
        else
            parent.right = child;

    }

    private Node findInOrderSuccessor(Node node){

        while(node.left != null){
            node = node.left;
        }
        return node;

    }

    public void inorderTraversal(){

        System.out.println();
        System.out.print("inorder traversal: ");

        Node current = root;
        while(current.left != null){
            current = current.left;
        }

        boolean isNext = true;

        while(current != null){
            if (isNext){
                System.out.print(current.data + " ");
                current = findParent(current);
            }

            if (current == root){
                break;
            }

            if (current != null && current.right != null) {
                System.out.print(current.data + " ");
                preorderTraversalHelper(current.right);
                current = findParent(current);
                isNext = false;
            }
        }

        System.out.print(root.data+" ");
        preorderTraversalHelper(root.right);

    }

    public void preorderTraversalHelper(Node root){

        if (root == null) return;

        List<Node> list = new ArrayList<>();
        list.add(root);

        while (!list.isEmpty()) {
            Node current = list.removeLast();
            System.out.print(current.data + " ");

            if (current.right != null)
                list.add(current.right);

            if (current.left != null)
                list.add(current.left);
        }

    }


    public void preorderTraversal(){

        if (root == null) return;

        System.out.println();
        System.out.print("preorder traversal: ");
        List<Node> list = new ArrayList<>();
        list.add(root);

        while (!list.isEmpty()) {
            Node current = list.removeLast();
            System.out.print(current.data + " ");

            if (current.right != null)
                list.add(current.right);

            if (current.left != null)
                list.add(current.left);
        }

    }

    public void postorderTraversal(){

        if (root == null) return;

        System.out.println();
        System.out.print("postorder traversal: ");
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);

            if (current.left != null)
                stack1.push(current.left);
            if (current.right != null)
                stack1.push(current.right);
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }

    }

    private int getHeightHelper(Node node){

        //empty subtree
        if (node == null) return -1;

        int leftHeight = getHeightHelper(node.left);
        int rightHeight = getHeightHelper(node.right);

        return Math.max(leftHeight, rightHeight) + 1;

    }

    public int getHeight(Node node){

        return getHeightHelper(node);

    }

    private int getImbalanceFactor(Node node){

        return getHeightHelper(node.left) - getHeightHelper(node.right);

    }

    public void findImbalancedNode(){

        System.out.println();
        System.out.print("imbalanced nodes: ");
        List<Node> list = new ArrayList<>();
        Node current = root;
        while (current != null || !list.isEmpty()) {

            while (current != null) {
                list.add(current);
                current = current.left;
            }
            current = list.removeLast();

            if(Math.abs(getImbalanceFactor(current)) >= 2)
                System.out.print(current.data + " ");

            current = current.right;
        }
    }


    public void rebalance() {
        List<Integer> nodes = returnListOfData();
        root = buildBalancedBST(nodes, 0, nodes.size()-1);
    }


    private List<Integer> returnListOfData() {
        List<Integer> list = new ArrayList<>();
        collectDataFromTree(root, list);
        return list;
    }

    private void collectDataFromTree(Node node, List<Integer> list) {
        if (node == null) return;

        collectDataFromTree(node.left, list);
        list.add(node.data);
        collectDataFromTree(node.right, list);
    }

    private Node buildBalancedBST(List<Integer> sortedList, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        Node newNode = new Node(sortedList.get(mid));

        newNode.left = buildBalancedBST(sortedList, start, mid-1);
        newNode.right = buildBalancedBST(sortedList, mid+1, end);

        return newNode;
    }

}

public class Main {
    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();

        Set<Integer> uniqueNumbers = new HashSet<>();
        Random rand = new Random();

        while (uniqueNumbers.size()<20) {
            uniqueNumbers.add(rand.nextInt(30)+1);
        }

        for (int num : uniqueNumbers) {
            tree.insert(num);
        }
        tree.postorderTraversal();

        tree.findImbalancedNode();
        tree.rebalance();

        for(int i=0; i<10; i++){
            int num = rand.nextInt(30)+1;
            tree.delete(num);
        }

        tree.findImbalancedNode();
        tree.rebalance();

        System.out.println();

        tree.inorderTraversal();
        tree.preorderTraversal();
        tree.postorderTraversal();
        System.out.println();
        System.out.println("Height of a tree: "+tree.getHeight(tree.root));

    }
}