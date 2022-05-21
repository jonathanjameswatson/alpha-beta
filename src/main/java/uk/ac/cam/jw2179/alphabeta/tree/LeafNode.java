package uk.ac.cam.jw2179.alphabeta.tree;

public class LeafNode<T> extends Node<T> {
  private T value;

  public LeafNode(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
