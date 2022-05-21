package uk.ac.cam.jw2179.alphabeta;

import java.util.List;
import java.util.Set;
import uk.ac.cam.jw2179.alphabeta.tree.BranchNode;
import uk.ac.cam.jw2179.alphabeta.tree.LeafNode;
import uk.ac.cam.jw2179.alphabeta.tree.Node;

public class Demo {
  private static <T> Node<T> partition(List<T> leaves) {
    if (leaves.size() == 1) {
      return new LeafNode<>(leaves.get(0));
    }

    int middle = leaves.size() / 2;
    List<T> left = leaves.subList(0, middle);
    List<T> right = leaves.subList(middle, leaves.size());

    return new BranchNode<>(Set.of(partition(left), partition(right)));
  }

  public static void main(String[] args) {
    List<Integer> integers =
        List.of(
            1, -15, 2, 19, 18, 23, 4, 3, 2, 1, 7, 8, 9, 10, -2, 5, -1, -30, 4, 7, 20, -1, -1, -5);
    Node<Integer> tree = partition(integers);

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
