package datastructures;

import static datastructures.BinarySearchTree.delete;
import static datastructures.BinarySearchTree.pullMaximumOnTopOf;
import static datastructures.BinarySearchTree.pullMinimumOnTopOf;
import static datastructures.matchers.BinarySearchTreeMatchers.hasLeftChild;
import static datastructures.matchers.BinarySearchTreeMatchers.hasNoLeftChild;
import static datastructures.matchers.BinarySearchTreeMatchers.hasNoRightChild;
import static datastructures.matchers.BinarySearchTreeMatchers.hasRightChild;
import static datastructures.matchers.BinarySearchTreeMatchers.hasValue;
import static datastructures.matchers.BinarySearchTreeMatchers.isTheSameTreeAs;
import static datastructures.matchers.BinarySearchTreeMatchers.valueWithoutChildrenNodes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import datastructures.BinarySearchTree.Node;

import org.junit.jupiter.api.Test;

class BinarySearchTreeDeleteUnitTest {

    @Test
    public void shouldPullMinimumFromTreeToItsTopWhenMinimumNodeIsALeaf() {
        int minimum = 5;
        Node root = Node.ofValue(20)
                .left(Node.ofValue(15)
                        .left(Node.ofValue(10)
                                .left(Node.ofValue(minimum).build())
                                .build())
                        .build())
                .build();

        Node rootAfterPullingMinimumOnTop = pullMinimumOnTopOf(root);

        assertThat(rootAfterPullingMinimumOnTop, allOf(
                hasValue(minimum),
                hasNoLeftChild(),
                hasRightChild(allOf(
                        hasValue(20),
                        hasNoRightChild(),
                        hasLeftChild(allOf(
                                hasValue(15),
                                hasNoRightChild(),
                                hasLeftChild(
                                        valueWithoutChildrenNodes(10))
                        ))
                ))
        ));
    }

    @Test
    public void shouldPullMinimumFromTreeToItsTopWhenMinimumNodeIsNotALeaf() {
        int minimum = 5;
        Node root = Node.ofValue(20)
                .left(Node.ofValue(15)
                        .left(Node.ofValue(10)
                                .left(Node.ofValue(minimum)
                                        .right(Node.ofValue(8)
                                                .left(Node.ofValue(7).build())
                                                .right(Node.ofValue(9).build())
                                                .build()
                                        )
                                        .build())
                                .build())
                        .build())
                .build();

        Node rootAfterPullingMinimumOnTop = pullMinimumOnTopOf(root);

        assertThat(rootAfterPullingMinimumOnTop, allOf(
                hasValue(minimum),
                hasNoLeftChild(),
                hasRightChild(allOf(
                        hasValue(20),
                        hasNoRightChild(),
                        hasLeftChild(allOf(
                                hasValue(15),
                                hasNoRightChild(),
                                hasLeftChild(allOf(
                                        hasValue(10),
                                        hasNoRightChild(),
                                        hasLeftChild(allOf(
                                                hasValue(8),
                                                hasLeftChild(valueWithoutChildrenNodes(7)),
                                                hasRightChild(valueWithoutChildrenNodes(9))
                                        ))
                                ))
                        ))
                ))
        ));
    }

    @Test
    public void shouldPullMaximumFromTreeToItsTopWhenMaximumNodeIsALeaf() {
        int maximum = 40;
        Node root = Node.ofValue(10)
                .right(Node.ofValue(20)
                        .right(Node.ofValue(30)
                                .right(Node.ofValue(maximum).build())
                                .build())
                        .build())
                .build();

        Node rootAfterPullingMaximumOnTop = pullMaximumOnTopOf(root);

        assertThat(rootAfterPullingMaximumOnTop, allOf(
                hasValue(maximum),
                hasNoRightChild(),
                hasLeftChild(allOf(
                        hasValue(10),
                        hasNoLeftChild(),
                        hasRightChild(allOf(
                                hasValue(20),
                                hasNoLeftChild(),
                                hasRightChild(allOf(
                                        valueWithoutChildrenNodes(30)
                                ))
                        ))
                ))
        ));
    }

    @Test
    public void shouldPullMaximumFromTreeToItsTopWhenMaximumNodeIsNotALeaf() {
        int maximum = 40;
        Node root = Node.ofValue(10)
                .right(Node.ofValue(20)
                        .right(Node.ofValue(30)
                                .right(Node.ofValue(maximum)
                                        .left(Node.ofValue(35)
                                                .left(Node.ofValue(33).build())
                                                .right(Node.ofValue(37).build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();

        Node rootAfterPullingMaximumOnTop = pullMaximumOnTopOf(root);

        assertThat(rootAfterPullingMaximumOnTop, allOf(
                hasValue(maximum),
                hasNoRightChild(),
                hasLeftChild(allOf(
                        hasValue(10),
                        hasNoLeftChild(),
                        hasRightChild(allOf(
                                hasValue(20),
                                hasNoLeftChild(),
                                hasRightChild(allOf(
                                        hasValue(30),
                                        hasNoLeftChild(),
                                        hasRightChild(allOf(
                                                hasValue(35),
                                                hasLeftChild(valueWithoutChildrenNodes(33)),
                                                hasRightChild(valueWithoutChildrenNodes(37))
                                        ))
                                ))
                        ))
                ))
        ));
    }

    @Test
    public void deletingANodeThatIsNotPresentInTheTreeShouldReturnTheStructureUnchanged() {
        Node root = Node.ofValue(50)
                .left(Node.ofValue(40)
                        .left(Node.ofValue(30).build())
                        .right(Node.ofValue(45).build())
                        .build()
                )
                .right(Node.ofValue(60)
                        .left(Node.ofValue(55)
                                .build())
                        .right(Node.ofValue(65)
                                .build())
                        .build())
                .build();

        Node rootAfterDeleting = delete(root, 37);
        assertThat(rootAfterDeleting, isTheSameTreeAs(root));
    }

    @Test
    public void shouldReturnNullWhenDeletingTheOnlyElementFromATree() {
        int value = 10;
        Node root = Node.ofValue(value).build();

        Node treeAfterDeletingTheOnlyNode = delete(root, value);

        assertThat(treeAfterDeletingTheOnlyNode, is(nullValue()));
    }

    @Test
    public void shouldDeleteTheRequestedNodeWhenItHasTheMinimumValueAndIsALeaf() {
        int minimum = 20;
        Node root = Node.ofValue(50)
                .left(Node.ofValue(40)
                        .left(Node.ofValue(30)
                                .left(Node.ofValue(minimum).build())
                                .right(Node.ofValue(35).build())
                                .build())
                        .right(Node.ofValue(45).build())
                        .build())
                .right(Node.ofValue(60).build())
                .build();

        Node treeAfterDeletingItsMinimumValue = delete(root, minimum);
        assertThat(treeAfterDeletingItsMinimumValue, allOf(
                hasValue(50),
                hasRightChild(valueWithoutChildrenNodes(60)),
                hasLeftChild(allOf(
                        hasValue(40),
                        hasRightChild(valueWithoutChildrenNodes(45)),
                        hasLeftChild(allOf(
                                hasValue(30),
                                hasRightChild(valueWithoutChildrenNodes(35)),
                                hasNoLeftChild()
                        ))
                ))
        ));
    }

    @Test
    public void shouldDeleteTheRequestedNodeWhenItHasTheMinimumValueAndIsNotALeaf() {
        int minimum = 20;
        Node root = Node.ofValue(50)
                .left(Node.ofValue(40)
                        .left(Node.ofValue(30)
                                .left(Node.ofValue(minimum)
                                        .right(Node.ofValue(25)
                                                .left(Node.ofValue(24).build())
                                                .right(Node.ofValue(26).build())
                                                .build())
                                        .build())
                                .right(Node.ofValue(35).build())
                                .build())
                        .right(Node.ofValue(45).build())
                        .build())
                .right(Node.ofValue(60).build())
                .build();

        Node treeAfterDeletingItsMinimumValue = delete(root, minimum);
        assertThat(treeAfterDeletingItsMinimumValue, allOf(
                hasValue(50),
                hasRightChild(valueWithoutChildrenNodes(60)),
                hasLeftChild(allOf(
                        hasValue(40),
                        hasRightChild(valueWithoutChildrenNodes(45)),
                        hasLeftChild(allOf(
                                hasValue(30),
                                hasRightChild(valueWithoutChildrenNodes(35)),
                                hasLeftChild(allOf(
                                        hasValue(24),
                                        hasNoLeftChild(),
                                        hasRightChild(allOf(
                                                hasValue(25),
                                                hasNoLeftChild(),
                                                hasRightChild(valueWithoutChildrenNodes(26))
                                        ))
                                ))
                        ))
                ))
        ));
    }

}
