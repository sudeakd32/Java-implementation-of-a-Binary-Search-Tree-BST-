# 🌲 Binary Search Tree (BST) with Imbalance Detection and Rebalancing

This project is a **Java implementation of a Binary Search Tree (BST)** that includes key features such as insertions, deletions, standard tree traversals, **imbalance detection**, and **automatic rebalancing** using a sorted list-based approach. It is designed for educational purposes and can be used as a foundational component for more complex data structure projects.

---

## 🧠 Features

- ✅ **BST Insertion (No Duplicates)**  
  Insert integer values while preserving BST properties. Duplicate values are ignored.

- ❌ **Search**  
  Efficient binary search to locate any value in the tree.

- 🗑 **Node Deletion**  
  Handles all three cases:  
  - Leaf node deletion  
  - Node with one child  
  - Node with two children (replaced with in-order successor)

- 🧭 **Traversals**
  - **Inorder** (Left → Root → Right)
  - **Preorder** (Root → Left → Right)
  - **Postorder** (Left → Right → Root)

- 📏 **Tree Height Calculation**  
  Computes the height of the tree using recursion.

- ⚖️ **Imbalance Detection**  
  Detects and prints all nodes with imbalance factor ≥ 2 or ≤ -2.

- 🔁 **Rebalancing**  
  Converts the BST to a balanced version using in-order traversal and recursive midpoint selection.

- 🔄 **Randomized Testing**  
  Generates random numbers to test tree growth, deletion, and rebalancing.

---

## 📂 Project Structure

├── Main.java                 # Main driver class with random insertions/deletions and test output
├── BinarySearchTree.java    # Core BST logic: insert, delete, rebalance, traversals, etc.
├── Node.java                # Basic Node class for the BST

## 📊 Sample Output (Illustrative)

postorder traversal: 3 9 15 12 8 7 11 6 10 14
imbalanced nodes: 10 12
imbalanced nodes: (none after rebalancing)

inorder traversal: 3 6 7 8 9 10 11 12 14 15
preorder traversal: 10 7 6 3 8 9 12 11 14 15
postorder traversal: 3 6 9 8 7 11 15 14 12 10

Height of a tree: 3

---

## 🛠 How It Works

### ✅ Insert

- Creates a new node and places it in the correct BST location.
- Prevents duplicates via a `search()` check before insertion.

### 🗑 Delete

- Searches for the node and deletes it using classic 3-case BST deletion:
  - Leaf node → remove directly
  - One child → replace node with child
  - Two children → replace with in-order successor

### ⚖ Imbalance Detection

- Traverses the tree and checks each node’s balance factor.
- A balance factor is defined as:

  ```java
  imbalance = height(left subtree) - height(right subtree)
