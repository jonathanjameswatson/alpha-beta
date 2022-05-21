package uk.ac.cam.jw2179.alphabeta;

import uk.ac.cam.jw2179.alphabeta.tree.BranchNode;
import uk.ac.cam.jw2179.alphabeta.tree.LeafNode;
import uk.ac.cam.jw2179.alphabeta.tree.Node;

public abstract class AlphaBetaSearcher<T> {
  public abstract int eval(T value);

  protected T player(int alpha, int beta, Node<T> node) {
    if (node instanceof LeafNode) {
      return ((LeafNode<T>) node).getValue();
    }

    int maxEvalValue = Integer.MIN_VALUE;
    T maxValue = null;
    int currentAlpha = alpha;

    BranchNode<T> branchNode = (BranchNode<T>) node;
    for (Node<T> child : branchNode) {
      T value = opponent(currentAlpha, beta, child);
      int evalValue = eval(value);
      if (evalValue >= maxEvalValue) {
        maxEvalValue = evalValue;
        maxValue = value;
      }

      if (maxEvalValue >= beta) {
        return value;
      }

      if (maxEvalValue > currentAlpha) {
        currentAlpha = maxEvalValue;
      }
    }

    if (maxValue == null) {
      throw new IllegalArgumentException("Given tree has a branch node with no children");
    }

    return maxValue;
  }

  protected T opponent(int alpha, int beta, Node<T> node) {
    if (node instanceof LeafNode) {
      return ((LeafNode<T>) node).getValue();
    }

    int minEvalValue = Integer.MAX_VALUE;
    T minValue = null;
    int currentBeta = beta;

    BranchNode<T> branchNode = (BranchNode<T>) node;
    for (Node<T> child : branchNode) {
      T value = player(alpha, currentBeta, child);
      int evalValue = eval(value);
      if (evalValue <= minEvalValue) {
        minEvalValue = evalValue;
        minValue = value;
      }

      if (minEvalValue <= alpha) {
        return value;
      }

      if (minEvalValue < currentBeta) {
        currentBeta = minEvalValue;
      }
    }

    if (minValue == null) {
      throw new IllegalArgumentException("Given tree has a branch node with no children");
    }

    return minValue;
  }

  public T search(Node<T> node) {
    return player(Integer.MIN_VALUE, Integer.MAX_VALUE, node);
  }
}
