package uk.ac.cam.jw2179.alphabeta.tree;

import java.util.Iterator;
import java.util.List;

public class BranchNode<T> extends Node<T> implements Iterable<Node<T>> {

  private final List<Node<T>> children;

  public BranchNode(List<Node<T>> children) {
    this.children = children;
  }

  @Override
  public Iterator<Node<T>> iterator() {
    return this.children.iterator();
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("[");
    for (Node<T> child : this.children) {
      sb.append(child.toString());
      sb.append(", ");
    }
    if (this.children.size() > 0) {
      sb.delete(sb.length() - 2, sb.length());
    }
    sb.append(']');
    return sb.toString();
  }
}
