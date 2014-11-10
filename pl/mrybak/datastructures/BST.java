package pl.mrybak.datastructures;

/**
  * There are two approaches for implementing a tree, either using nested Node class or not.
  * Code below does not use Node class. This is for learning purposes (i.e discovering differences b'ween two approaches)
  */
public class BST<T extends Comparable<T>> {
  
  T value;
  BST<T> left;
  BST<T> right;
  
  public void insert(T elem) {
    if (value == null) { value = elem; return; } // new tree     
    if (elem.compareTo(value) == 0) { return; }  // do not allow duplicates
    
    BST<T> newNode = new BST<T>(); 
    newNode.value = elem; 
    
    if (elem.compareTo(value) < 0) {
	if (left == null) { left = newNode; } else { left.insert(elem); }
    } else {
	if (right == null) { right = newNode; } else { right.insert(elem); }
    }
  };
  
  public boolean contains(T elem) {
    if (value == null) { return false; }  // do not accept null elems
    if (value.compareTo(elem) == 0) { return true; }
    
    return (left != null && left.contains(elem))
	|| (right != null && right.contains(elem));
  };
  
  
  public int height() {
    return height(this);
  };
  
  private int height(BST<T> tree) {
    if (tree == null) return 0;
    return 1 + Math.max(height(tree.left), height(tree.right)); 
  }
  
  
  /**
    * Below: BST node removal
    */
  // TODO
  // public void delete(T elem) {};  -> findSuccessor(Node)
  
  
  /**
    * Below: BST traversal methods 
    */
  public void enumeratePreOrder() {
    System.out.println(value);
    if (left != null) left.enumeratePreOrder();
    if (right != null) right.enumeratePreOrder();
  };
  
  
  public void enumerateInOrder() {
    if (left != null) left.enumerateInOrder();
    System.out.println(value);
    if (right != null) right.enumerateInOrder();
  };
  
  public void enumeratePostOrder() {
    if (left != null) left.enumeratePostOrder();
    if (right != null) right.enumeratePostOrder();
    System.out.println(value);
  }
  
  public void enumerateBFS() {
    SinglyLinkedList<BST<T>> queue = new SinglyLinkedList<>();
    queue.append(this);
    
    while(!queue.empty()) {
      BST<T> node = queue.pop();
      System.out.println(node.value);
      if (node.left != null) { queue.append(node.left); }
      if (node.right != null) { queue.append(node.right); }
    }
  };
}
