package bitcamp.util;

public class LinkedList {

  Node first;
  Node last;

  public void add(Object value) {
    Node node = new Node();
    node.value = value;

    if (last == null) {
      first = last = node;
    } else {
      last.next = node;
      last = node;
    }
  }
}
