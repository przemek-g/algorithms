package datastructures.matchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import datastructures.trees.BinarySearchTree.Node;
import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

@UtilityClass
public class BinarySearchTreeMatchers {

    public static Matcher<Node> hasLeftChild(Matcher<Node> leftNodeMatcher) {
        return new FeatureMatcher<Node, Node>(leftNodeMatcher, "has left child", ".left") {

            protected Node featureValueOf(Node node) {
                return node.getLeft();
            }
        };
    }

    public static Matcher<Node> hasNoLeftChild() {
        return hasLeftChild(nullValue(Node.class));
    }

    public static Matcher<Node> hasRightChild(Matcher<Node> rightNodeMatcher) {
        return new FeatureMatcher<Node, Node>(rightNodeMatcher, "has right child", ".right") {

            protected Node featureValueOf(Node node) {
                return node.getRight();
            }
        };
    }

    public static Matcher<Node> hasNoRightChild() {
        return hasRightChild(nullValue(Node.class));
    }

    public static Matcher<Node> hasValue(int expectedValue) {
        return hasValue(equalTo(expectedValue));
    }

    private static Matcher<Node> hasValue(Matcher<Integer> valueMatcher) {
        return new FeatureMatcher<Node, Integer>(valueMatcher, "has value", ".value") {

            protected Integer featureValueOf(Node node) {
                return node.getValue();
            }
        };
    }

    public static Matcher<Node> valueWithoutChildrenNodes(int expectedValue) {
        return Matchers.allOf(
                hasValue(expectedValue),
                hasNoLeftChild(),
                hasNoRightChild()
        );
    }

    public static Matcher<Node> isTheSameTreeAs(Node node) {
        return new TreeEqualityMatcher(node);
    }

    @AllArgsConstructor
    private static final class TreeEqualityMatcher extends BaseMatcher<Node> {

        private final Node referenceNode;

        public boolean matches(Object o) {

            return  bothAreNull(o, referenceNode)
                    || ((o instanceof Node) && treesAreIdentical((Node)o, referenceNode));
        }

        private static boolean treesAreIdentical(Node nodeOne, Node nodeTwo) {
            return bothAreNull(nodeOne, nodeTwo)
                    ||
                    (notNullWithEqualValues(nodeOne, nodeTwo)
                            && nodesHaveIdenticalRightSubtrees(nodeOne, nodeTwo)
                            && nodesHaveIdenticalLeftSubtrees(nodeOne, nodeTwo));
        }

        private static boolean nodesHaveIdenticalRightSubtrees(Node nodeOne, Node nodeTwo) {
            return treesAreIdentical(nodeOne.getRight(), nodeTwo.getRight());
        }

        private static boolean nodesHaveIdenticalLeftSubtrees(Node nodeOne, Node nodeTwo) {
            return treesAreIdentical(nodeOne.getLeft(), nodeTwo.getLeft());
        }

        private static boolean notNullWithEqualValues(Node nodeOne, Node nodeTwo) {
            return bothAreNotNull(nodeOne, nodeTwo)
                    && nodeOne.getValue() == nodeTwo.getValue();
        }

        public void describeTo(Description description) {

        }
    }

    private static boolean bothAreNull(Object nodeOne, Object nodeTwo) {
        return nodeOne == null && nodeTwo == null;
    }

    private static boolean bothAreNotNull(Node nodeOne, Node nodeTwo) {
        return nodeOne != null && nodeTwo != null;
    }
}
