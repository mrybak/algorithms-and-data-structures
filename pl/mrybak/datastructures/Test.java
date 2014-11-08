package pl.mrybak.datastructures;

public class Test {
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