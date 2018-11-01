package datastructures.trees;

import static datastructures.trees.BinarySearchTree.insert;
import static datastructures.matchers.BinarySearchTreeMatchers.hasLeftChild;
import static datastructures.matchers.BinarySearchTreeMatchers.hasNoLeftChild;
import static datastructures.matchers.BinarySearchTreeMatchers.hasNoRightChild;
import static datastructures.matchers.BinarySearchTreeMatchers.hasRightChild;
import static datastructures.matchers.BinarySearchTreeMatchers.hasValue;
import static datastructures.matchers.BinarySearchTreeMatchers.valueWithoutChildrenNodes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.nullValue;

import datastructures.trees.BinarySearchTree.Node;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeInsertionUnitTest {

    @Test
    public void insertingIntoAnEmptyTreeShouldReturnNewNodeAsRoot() {
        int value = 10;
        Node nodeAfterInsertion = insert(null, value);

        assertThat(nodeAfterInsertion, allOf(
                hasLeftChild(nullValue(Node.class)),
                hasRightChild(nullValue(Node.class)),
                hasValue(value)
        ));
    }

    @Test
    public void shouldInsertNewNodeAsLeftChildOfRoot() {

        int rightChildValueAlreadyPresent = 30;
        int newValueToBeInserted = 10;

        Node root = Node.ofValue(20)
                .right(
                        Node.ofValue(rightChildValueAlreadyPresent).build())
                .build();

        Node rootAfterInsertion = insert(root, newValueToBeInserted);

        assertThat(rootAfterInsertion, allOf(
                hasValue(20),
                hasRightChild(
                        valueWithoutChildrenNodes(rightChildValueAlreadyPresent)),
                hasLeftChild(
                        valueWithoutChildrenNodes(newValueToBeInserted))
        ));
    }

    @Test
    public void shouldInsertNewNodeAsRightChildOfRoot() {

        int leftChildValueAlreadyPresent = 10;
        int newValueToBeInserted = 30;

        Node root = Node.ofValue(20)
                .left(
                        Node.ofValue(leftChildValueAlreadyPresent).build())
                .build();

        Node rootAfterInsertion = insert(root, newValueToBeInserted);

        assertThat(rootAfterInsertion, allOf(
                hasValue(20),
                hasRightChild(
                        valueWithoutChildrenNodes(newValueToBeInserted)),
                hasLeftChild(
                        valueWithoutChildrenNodes(leftChildValueAlreadyPresent))
        ));
    }

    @Test
    public void shouldInsertNewNodeAsTheLeftmostLeafWhenItsValueIsMinimum() {
        int value = 10;
        Node root = Node.ofValue(50)
                .left(
                        Node.ofValue(30)
                                .left(
                                        Node.ofValue(20).build()
                                )
                                .build())
                .build();


        Node rootAfterInsertion = insert(root, value);

        assertThat(rootAfterInsertion, allOf(
                hasValue(50),
                hasNoRightChild(),
                hasLeftChild(allOf(
                        hasValue(30),
                        hasNoRightChild(),
                        hasLeftChild(allOf(
                                hasValue(20),
                                hasNoRightChild(),
                                hasLeftChild(
                                        valueWithoutChildrenNodes(value)
                                )
                        ))
                ))
        ));
    }

    @Test
    public void shouldInsertNewNodeAsTheRightmostLeafWhenItsValueIsMaximum() {
        int value = 80;
        Node root = Node.ofValue(50)
                .right(
                        Node.ofValue(60)
                                .right(
                                        Node.ofValue(70).build()
                                )
                                .build())
                .build();


        Node rootAfterInsertion = insert(root, value);

        assertThat(rootAfterInsertion, allOf(
                hasValue(50),
                hasNoLeftChild(),
                hasRightChild(allOf(
                        hasValue(60),
                        hasNoLeftChild(),
                        hasRightChild(allOf(
                                hasValue(70),
                                hasNoLeftChild(),
                                hasRightChild(
                                        valueWithoutChildrenNodes(value)
                                )
                        ))
                ))
        ));
    }

    @Test
    public void shouldInsertNewNodeAsLeftChildOfItsParentThatItselfIsTheRightChildOfItsParent() {
        Node root = Node.ofValue(50)
                .left(
                        Node.ofValue(20)
                                .right(
                                        Node.ofValue(25)
                                                .right(
                                                        Node.ofValue(40).build()
                                                )
                                                .build()
                                )
                                .build())
                .build();
        int valueToInsert = 30;

        Node rootAfterInsertion = insert(root, valueToInsert);

        assertThat(rootAfterInsertion, allOf(
                hasValue(50),
                hasNoRightChild(),
                hasLeftChild(allOf(
                        hasValue(20),
                        hasNoLeftChild(),
                        hasRightChild(allOf(
                                hasValue(25),
                                hasNoLeftChild(),
                                hasRightChild(allOf(
                                        hasValue(40),
                                        hasNoRightChild(),
                                        hasLeftChild(
                                                valueWithoutChildrenNodes(valueToInsert)
                                        )))))))));
    }

    @Test
    public void shouldInsertNewNodeAsRightChildOfItsParentThatItselfIsTheLeftChildOfItsParent() {
        Node root = Node.ofValue(50)
                .right(
                        Node.ofValue(90)
                                .left(
                                        Node.ofValue(80)
                                                .left(
                                                        Node.ofValue(60).build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();
        int valueToInsert = 70;

        Node rootAfterInsertion = insert(root, valueToInsert);

        assertThat(rootAfterInsertion, allOf(
                hasValue(50),
                hasNoLeftChild(),
                hasRightChild(allOf(
                        hasValue(90),
                        hasNoRightChild(),
                        hasLeftChild(allOf(
                                hasValue(80),
                                hasNoRightChild(),
                                hasLeftChild(allOf(
                                        hasValue(60),
                                        hasNoLeftChild(),
                                        hasRightChild(valueWithoutChildrenNodes(valueToInsert))
                                ))
                        ))
                ))
        ));
    }

}
