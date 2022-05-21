package uk.ac.cam.jw2179.alphabeta.tree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BranchNode<T> extends Node<T> implements Iterable<Node<T>> {

  private final Set<Node<T>> children;

  public BranchNode(Set<Node<T>> children) {
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
    }
    sb.append(']');
    return sb.toString();
  }
}
