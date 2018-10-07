package datastructures.matchers;

import static datastructures.matchers.BinarySearchTreeMatchers.isTheSameTreeAs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import datastructures.BinarySearchTree.Node;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

public class BinarySearchTreeEqualityMatcherUnitTest {

    @Test
    public void shouldConsiderTwoEmptyTreesEqual() {
        Node nullNode = null;
        Matcher<Node> nullTreeComparison = isTheSameTreeAs((Node)null);

        boolean match = nullTreeComparison.matches((Node) nullNode);

        assertThat(match, is(true));
    }

    @Test
    public void shouldConsiderTwoTreesIdenticalWhenTheyConsistOfRootsOnlyWithEqualValues() {
        Node firstRoot = Node.ofValue(10).build();
        Node secondRoot = Node.ofValue(10).build();

        Matcher<Node> oneNodeTreeComparison = isTheSameTreeAs(firstRoot);

        boolean match = oneNodeTreeComparison.matches(secondRoot);
        assertThat(match, is(true));
    }

    @Test
    public void shouldConsiderATreeIdenticalToItself() {
        Node treeToCompareAgainstItself = Node.ofValue(10).build();

        Matcher<Node> sameInstanceComparison = isTheSameTreeAs(treeToCompareAgainstItself);

        boolean match = sameInstanceComparison.matches(treeToCompareAgainstItself);
        assertThat(match, is(true));
    }

    @Test
    public void shouldConsiderTwoTreesIdenticalIfTheyHaveTheSameStructureAndValues() {
        int root = 50;
        int left = 30, leftLeft = 20, leftRight = 40;
        int right = 70, rightLeft = 60, rightRight = 80;

        Node firstTree = Node.ofValue(root)
                .left(Node.ofValue(left)
                        .left(Node.ofValue(leftLeft)
                                .build())
                        .right(Node.ofValue(leftRight)
                                .build())
                        .build())
                .right(Node.ofValue(right)
                        .left(Node.ofValue(rightLeft)
                                .build())
                        .right(Node.ofValue(rightRight)
                                .build())
                        .build())
                .build();

        Node secondTree = Node.ofValue(root)
                .left(Node.ofValue(left)
                        .left(Node.ofValue(leftLeft)
                                .build())
                        .right(Node.ofValue(leftRight)
                                .build())
                        .build())
                .right(Node.ofValue(right)
                        .left(Node.ofValue(rightLeft)
                                .build())
                        .right(Node.ofValue(rightRight)
                                .build())
                        .build())
                .build();

        boolean match = isTheSameTreeAs(firstTree).matches(secondTree);

        assertThat(match, is(true));
    }

    @Test
    public void shouldConsiderTwoTreesNotIdenticalIfAtLeastOneNodeIsNotEqualToItsCorrespondingNode() {

        int root = 50;
        int left = 30, leftLeft = 20, leftRight = 40;
        int right = 70, rightLeft = 60, rightRight = 80;

        Node firstTree = Node.ofValue(root)
                .left(Node.ofValue(left)
                        .left(Node.ofValue(leftLeft)
                                .build())
                        .right(Node.ofValue(leftRight)
                                .build())
                        .build())
                .right(Node.ofValue(right)
                        .left(Node.ofValue(rightLeft)
                                .build())
                        .right(Node.ofValue(rightRight)
                                .build())
                        .build())
                .build();

        int alteredRightRight = rightRight + 1;

        Node secondTree = Node.ofValue(root)
                .left(Node.ofValue(left)
                        .left(Node.ofValue(leftLeft)
                                .build())
                        .right(Node.ofValue(leftRight)
                                .build())
                        .build())
                .right(Node.ofValue(right)
                        .left(Node.ofValue(rightLeft)
                                .build())
                        .right(Node.ofValue(alteredRightRight)
                                .build())
                        .build())
                .build();

        Matcher<Node> sameTreeAs = isTheSameTreeAs(firstTree);
        boolean match = sameTreeAs.matches(secondTree);

        assertThat(match, is(false));
    }

    @Test
    public void shouldConsiderTwoTreesNotIdenticalIfOneOfThemExtendsTheOtherOne() {
        int root = 50;
        int left = 30, leftLeft = 20, leftRight = 40;
        int right = 70, rightLeft = 60, rightRight = 80;

        Node firstTree = Node.ofValue(root)
                .left(Node.ofValue(left)
                        .left(Node.ofValue(leftLeft)
                                .build())
                        .right(Node.ofValue(leftRight)
                                .build())
                        .build())
                .right(Node.ofValue(right)
                        .left(Node.ofValue(rightLeft)
                                .build())
                        .right(Node.ofValue(rightRight)
                                .build())
                        .build())
                .build();

        Node secondTree = Node.ofValue(root)
                .left(Node.ofValue(left)
                        .left(Node.ofValue(leftLeft)
                                .build())
                        .right(Node.ofValue(leftRight)
                                .build())
                        .build())
                .right(Node.ofValue(right)
                        .left(Node.ofValue(rightLeft)
                                .right(Node.ofValue(anIntegerLargerThanLeftAndSmallerOrEqualToRight(rightLeft, right)).build())
                                .build())
                        .right(Node.ofValue(rightRight)
                                .build())
                        .build())
                .build();

        Matcher<Node> sameTreeAs = isTheSameTreeAs(firstTree);
        boolean match = sameTreeAs.matches(secondTree);

        assertThat(match, is(false));
    }

    private static int anIntegerLargerThanLeftAndSmallerOrEqualToRight(int left, int right) {
        return left + (right - left)/2;
    }
}
