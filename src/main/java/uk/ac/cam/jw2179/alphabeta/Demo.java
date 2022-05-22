package uk.ac.cam.jw2179.alphabeta;

import java.util.ArrayList;
import java.util.List;
import uk.ac.cam.jw2179.alphabeta.tree.BranchNode;
import uk.ac.cam.jw2179.alphabeta.tree.LeafNode;
import uk.ac.cam.jw2179.alphabeta.tree.Node;

public class Demo {
  private static <T> Node<T> partition(List<T> leaves, List<Integer> branchingFactors) {
    if (leaves.size() == 1) {
      return new LeafNode<>(leaves.get(0));
    }

    int branchingFactor = branchingFactors.get(0);
    int branchSize = leaves.size() / branchingFactor;
    List<Integer> remainingBranchingFactors = branchingFactors.subList(1, branchingFactors.size());

    List<Node<T>> children = new ArrayList<>();
    for (int i = 0; i < leaves.size(); i += branchSize) {
      children.add(
          partition(
              leaves.subList(i, Math.min(i + branchSize, leaves.size())),
              remainingBranchingFactors));
    }

    return new BranchNode<>(children);
  }

  public static void main(String[] args) {
    List<Integer> integers =
        List.of(
            1, -15, 2, 19, 18, 23, 4, 3, 2, 1, 7, 8, 9, 10, -2, 5, -1, -30, 4, 7, 20, -1, -1, -5);
    List<Integer> branchingFactors = List.of(3, 2, 2, 2);
    Node<Integer> tree = partition(integers, branchingFactors);

    AlphaBetaSearcher<Integer> searcher =
        new AlphaBetaSearcher<>() {
          @Override
          public int eval(Integer integer) {
            return integer;
          }
        };

    int move = searcher.search(tree);
    System.out.println(move);
  }
}
