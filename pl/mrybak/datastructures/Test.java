package pl.mrybak.datastructures;

public class Test {
  public static void main(String[] args) {
    // basic linked list operations
    SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>();
  
    l1.append(2);
    l1.append(7);
    l1.append(4);
    l1.append(5);
    System.out.println(l1.empty());
    System.out.println(l1.size());
    System.out.println(l1.contains(4L));
    System.out.println(l1.contains(5));
    System.out.println(l1.contains("foo"));
    System.out.println(l1);
    l1.remove(7);
    System.out.println(l1);
    l1.remove(5);
    System.out.println(l1);
    System.out.println(l1.get(0));
    System.out.println(l1.get(1));
    l1.clear();
    System.out.println(l1.empty());
    System.out.println(l1.size());
    System.out.println(l1.contains(4));
    System.out.println(l1);
    
    // can be used as stack
    SinglyLinkedList<String> stack = new SinglyLinkedList<>();
    stack.push("foo");
    stack.push("bar");
    stack.push("baz");
    System.out.println(stack);
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());    
    
    BST<Integer> bst = new BST<>();
    System.out.println(bst.contains(null));
    bst.insert(5);
    bst.insert(2);
    bst.insert(1);
    bst.insert(4);
    bst.insert(3);
    bst.insert(3);
    System.out.println("==/");
    System.out.println(bst.contains(4));
    System.out.println(bst.contains(6));
    System.out.println("===");
    bst.enumeratePreOrder();
    System.out.println("===");
    bst.enumerateInOrder();
    System.out.println("===");
    bst.enumeratePostOrder();
    System.out.println("===");
    bst.enumerateBFS();
  }
}