package pl.mrybak.datastructures;

public class SinglyLinkedList<T> {
  
  private Node<T> head;
  private int size;
  
  
  
  private static class Node<T> {
    private T value;
    private Node<T> next;
    
    Node(T value) {
      this.value = value;
    }
  }
  
  
  
  public void append(T elem) {
    Node<T> newNode = new Node<T>(elem);
      if (empty()) {
      head = newNode;
    }
    else {
      Node<T> lastNode = head;
    
      while (lastNode.next != null) {
	lastNode = lastNode.next;
      }
      lastNode.next = newNode;
    }
    size++;
  }   
  
  
  
  private boolean equal(T value, Object o) {
    return value == null ? o == null : value.equals(o);
  }
  
  
  
  public boolean contains(Object o) {
    if (!empty()) {
      Node<T> node = head;
      while (node != null) {
	if (equal(node.value, o)) {
	  return true;
	}
	node = node.next;
      }
    }
    return false;
  }
  
  
  public T get(int index) {
    if (empty() || index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    
    Node<T> node = head;
    T result = null;
    while (node != null) {
      if (index-- == 0) {
	result = node.value;
      }
      node = node.next;
    }    
    return result;
  }
  
  
  
  public void remove(Object o) {
    if (!empty()) {
      Node<T> node = head;
      while (node != null) {
	if (node.next != null && equal(node.next.value, o)) { 
	  Node<T> toBeRemoved = node.next;
	  node.next = toBeRemoved.next;
	  size--;
	  return;
	}
	node = node.next;
      }
    }
  }
  
  
  
  public boolean empty() {
    return size == 0;
  }
  
  
  
  public int size() {
    return size;
  }
  
  
  
  public void clear() {
    size = 0;
    head = null;
  }
  
  
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append(getClass().getName());
    sb.append(" {");
    Node<T> node = head;
    while (node != null) {
      sb.append(node.value);
      sb.append(", ");
      node = node.next;
    }
    if (!empty()) { 
      sb.delete(sb.length() - 2, sb.length());  // remove trailing comma&space
    }
    sb.append("}");
    
    return sb.toString();
  }
  
  public static void main(String[] args) {
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
  }
}