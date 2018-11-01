package datastructures.trees;

import static datastructures.trees.BinarySearchTree.delete;
import static datastructures.trees.BinarySearchTree.pullMaximumOnTopOf;
import static datastructures.trees.BinarySearchTree.pullMinimumOnTopOf;
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

import datastructures.trees.BinarySearchTree.Node;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeDeleteUnitTest {

    @Test
    public void shouldPullMinimumFromTreeToItsTopWhenMinimumNodeIsALeaf() {
        int minimum = 5;
        Node root = Node.ofValue(20)
                .left(Node.ofValue(15)
                        .left(Node.ofValue(10)
                                .left(Node.ofValueWithoutChildren(minimum))
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
                                                .left(Node.ofValueWithoutChildren(7))
                                                .right(Node.ofValueWithoutChildren(9))
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
                                .right(Node.ofValueWithoutChildren(maximum))
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
                                                .left(Node.ofValueWithoutChildren(33))
                                                .right(Node.ofValueWithoutChildren(37))
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
                        .left(Node.ofValueWithoutChildren(30))
                        .right(Node.ofValueWithoutChildren(45))
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
    public void shouldDeleteTheRequestedNodeWhenItHasTheMinimumValueAndHasNoSubtrees() {
        int minimum = 20;
        Node root = Node.ofValue(50)
                .left(Node.ofValue(40)
                        .left(Node.ofValue(30)
                                .left(Node.ofValueWithoutChildren(minimum))
                                .right(Node.ofValueWithoutChildren(35))
                                .build())
                        .right(Node.ofValueWithoutChildren(45))
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
                                                .left(Node.ofValueWithoutChildren(24))
                                                .right(Node.ofValueWithoutChildren(26))
                                                .build())
                                        .build())
                                .right(Node.ofValueWithoutChildren(35))
                                .build())
                        .right(Node.ofValueWithoutChildren(45))
                        .build())
                .right(Node.ofValueWithoutChildren(60))
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
                                        hasValue(25),
                                        hasLeftChild(valueWithoutChildrenNodes(24)),
                                        hasRightChild(valueWithoutChildrenNodes(26))
                                ))
                        ))
                ))
        ));
    }

    @Test
    public void shouldDeleteTheRequestedNodeWhenItIsTheLeftChildOfItsParentAndHasNoSubtrees() {
        int valueToDelete = 20;
        Node root = Node.ofValue(10)
                .left(Node.ofValue(5).build())
                .right(Node.ofValue(40)
                        .left(Node.ofValue(30)
                                .left(Node.ofValueWithoutChildren(valueToDelete))
                                .right(Node.ofValueWithoutChildren(35))
                                .build())
                        .right(Node.ofValueWithoutChildren(45))
                        .build())
                .build();

        Node treeAfterDeleting = delete(root, valueToDelete);
        assertThat(treeAfterDeleting, allOf(
                hasValue(10),
                hasLeftChild(valueWithoutChildrenNodes(5)),
                hasRightChild(allOf(
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
    public void shouldDeleteTheRequestedNodeWhenItIsTheLeftChildOfItsParentAndOnlyHasTheRightSubtree() {
        int valueToDelete = 20;
        Node root = Node.ofValue(10)
                .left(Node.ofValueWithoutChildren(5))
                .right(Node.ofValue(40)
                        .left(Node.ofValue(30)
                                .left(Node.ofValue(valueToDelete)
                                        .right(Node.ofValue(25)
                                                .left(Node.ofValueWithoutChildren(24))
                                                .right(Node.ofValueWithoutChildren(26))
                                                .build()
                                        )
                                        .build())
                                .right(Node.ofValueWithoutChildren(35))
                                .build())
                        .right(Node.ofValueWithoutChildren(45))
                        .build())
                .build();

        Node treeAfterDeleting = delete(root, valueToDelete);
        assertThat(treeAfterDeleting, allOf(
                hasValue(10),
                hasLeftChild(valueWithoutChildrenNodes(5)),
                hasRightChild(allOf(
                        hasValue(40),
                        hasRightChild(valueWithoutChildrenNodes(45)),
                        hasLeftChild(allOf(
                                hasValue(30),
                                hasRightChild(valueWithoutChildrenNodes(35)),
                                hasLeftChild(allOf(
                                        hasValue(25),
                                        hasLeftChild(valueWithoutChildrenNodes(24)),
                                        hasRightChild(valueWithoutChildrenNodes(26))
                                ))
                        ))
                ))
        ));
    }

    @Test
    public void shouldDeleteTheRequestedNodeWhenItIsTheLeftChildOfItsParentAndOnlyHasTheLeftSubtree() {
        int valueToDelete = 20;
        Node root = Node.ofValue(4)
                .left(Node.ofValueWithoutChildren(1))
                .right(Node.ofValue(40)
                        .left(Node.ofValue(30)
                                .left(Node.ofValue(valueToDelete)
                                        .left(Node.ofValue(10)
                                                .left(Node.ofValueWithoutChildren(5))
                                                .right(Node.ofValueWithoutChildren(15))
                                                .build()
                                        )
                                        .build())
                                .right(Node.ofValueWithoutChildren(35))
                                .build())
                        .right(Node.ofValueWithoutChildren(45))
                        .build())
                .build();

        Node treeAfterDeleting = delete(root, valueToDelete);
        assertThat(treeAfterDeleting, allOf(
                hasValue(4),
                hasLeftChild(valueWithoutChildrenNodes(1)),
                hasRightChild(allOf(
                        hasValue(40),
                        hasRightChild(valueWithoutChildrenNodes(45)),
                        hasLeftChild(allOf(
                                hasValue(30),
                                hasRightChild(valueWithoutChildrenNodes(35)),
                                hasLeftChild(allOf(
                                        hasValue(10),
                                        hasLeftChild(valueWithoutChildrenNodes(5)),
                                        hasRightChild(valueWithoutChildrenNodes(15)))
                                ))
                        ))
                ))
        );
    }

    @Test
    public void shouldDeleteTheRequestedNodeWhenItIsTheLeftChildOfItsParentAndHasBothSubtrees() {
        int valueToDelete = 20;
        Node root = Node.ofValue(30)
                .left(Node.ofValue(valueToDelete)
                        .left(Node.ofValue(10)
                                .left(Node.ofValueWithoutChildren(5))
                                .right(Node.ofValueWithoutChildren(15))
                                .build())
                        .right(Node.ofValue(28)
                                .left(Node.ofValue(24)
                                        .right(Node.ofValue(26)
                                                .left(Node.ofValueWithoutChildren(25))
                                                .right(Node.ofValueWithoutChildren(27))
                                                .build())
                                        .build())
                                .right(Node.ofValueWithoutChildren(29))
                                .build())
                        .build())
                .right(Node.ofValueWithoutChildren(35))
                .build();

        Node treeAfterDeleting = delete(root, valueToDelete);
        assertThat(treeAfterDeleting, allOf(
                hasValue(30),
                hasRightChild(valueWithoutChildrenNodes(35)),
                hasLeftChild(allOf(
                        hasValue(24),
                        hasLeftChild(allOf(
                                hasValue(10),
                                hasLeftChild(valueWithoutChildrenNodes(5)),
                                hasRightChild(valueWithoutChildrenNodes(15))
                        )),
                        hasRightChild(allOf(
                                hasValue(28),
                                hasRightChild(valueWithoutChildrenNodes(29)),
                                hasLeftChild(allOf(
                                        hasValue(26),
                                        hasLeftChild(valueWithoutChildrenNodes(25)),
                                        hasRightChild(valueWithoutChildrenNodes(27))
                                ))
                        ))
                ))
        ));
    }

    @Test
    public void shouldDeleteTheRequestedNodeWhenItIsTheRightChildOfItsParentAndHasNoSubtrees() {
        int valueToDelete = 95;
        Node root = Node.ofValue(60)
                .left(Node.ofValueWithoutChildren(50))
                .right(
                        Node.ofValue(90)
                                .left(Node.ofValueWithoutChildren(80))
                                .right(Node.ofValueWithoutChildren(valueToDelete))
                                .build())
                .build();

        Node treeAfterDeleting = delete(root, valueToDelete);

        assertThat(treeAfterDeleting, allOf(
                hasValue(60),
                hasLeftChild(valueWithoutChildrenNodes(50)),
                hasRightChild(allOf(
                        hasValue(90),
                        hasLeftChild(valueWithoutChildrenNodes(80)),
                        hasNoRightChild()
                ))
        ));
    }

    @Test
    public void shouldDeleteTheRequestedNodeWhenItIsTheRightChildOfItsParentAndOnlyHasTheRightSubtree() {
        int valueToDelete = 80;
        Node root = Node.ofValue(60)
                .left(Node.ofValueWithoutChildren(50))
                .right(
                        Node.ofValue(valueToDelete)
                                .right(
                                        Node.ofValue(90)
                                                .right(Node.ofValueWithoutChildren(95))
                                                .left(Node.ofValueWithoutChildren(85))
                                                .build()
                                )
                                .build())
                .build();

        Node treeAfterDeleting = delete(root, valueToDelete);

        assertThat(treeAfterDeleting, allOf(
                hasValue(60),
                hasLeftChild(valueWithoutChildrenNodes(50)),
                hasRightChild(allOf(
                        hasValue(90),
                        hasLeftChild(valueWithoutChildrenNodes(85)),
                        hasRightChild(valueWithoutChildrenNodes(95))
                ))
        ));
    }

    @Test
    public void shouldDeleteTheRequestedNodeWhenItIsTheRightChildOfItsParentAndOnlyHasTheLeftSubtree(){
        int valueToDelete = 80;
        Node root = Node.ofValue(60)
                .left(Node.ofValueWithoutChildren(50))
                .right(
                        Node.ofValue(valueToDelete)
                                .left(
                                        Node.ofValue(70)
                                                .left(Node.ofValueWithoutChildren(65))
                                                .right(Node.ofValueWithoutChildren(75))
                                                .build()
                                )
                                .build())
                .build();

        Node treeAfterDeleting = delete(root, valueToDelete);
        assertThat(treeAfterDeleting, allOf(
                hasValue(60),
                hasLeftChild(valueWithoutChildrenNodes(50)),
                hasRightChild(allOf(
                        hasValue(70),
                        hasLeftChild(valueWithoutChildrenNodes(65)),
                        hasRightChild(valueWithoutChildrenNodes(75))
                ))
        ));
    }

    @Test
    public void shouldDeleteTheRequestedNodeWhenItIsTheRightChildOfItsParentAndHasBothSubtrees() {
        int valueToDelete = 80;
        Node root = Node.ofValue(60)
                .left(Node.ofValueWithoutChildren(50))
                .right(
                        Node.ofValue(valueToDelete)
                                .left(Node.ofValueWithoutChildren(70))
                                .right(
                                        Node.ofValue(90)
                                                .left(
                                                        Node.ofValue(85)
                                                                .right(
                                                                        Node.ofValue(87)
                                                                                .left(Node.ofValueWithoutChildren(86))
                                                                                .right(Node.ofValueWithoutChildren(89))
                                                                                .build()
                                                                )
                                                                .build()
                                                )
                                                .right(Node.ofValueWithoutChildren(95))
                                                .build()
                                )
                                .build()
                )
                .build();

        Node treeAfterDeleting = delete(root, valueToDelete);

        assertThat(treeAfterDeleting, allOf(
                hasValue(60),
                hasLeftChild(valueWithoutChildrenNodes(50)),
                hasRightChild(allOf(
                        hasValue(85),
                        hasLeftChild(valueWithoutChildrenNodes(70)),
                        hasRightChild(allOf(
                                hasValue(90),
                                hasLeftChild(allOf(
                                        hasValue(87),
                                        hasLeftChild(valueWithoutChildrenNodes(86)),
                                        hasRightChild(valueWithoutChildrenNodes(89))
                                )),
                                hasRightChild(valueWithoutChildrenNodes(95))
                        ))
                ))
        ));
    }
}
