package datastructures;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class BinarySearchTree {

    public static Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        Node currentNode = root;
        while (currentNode != null) {
            boolean goLeft = (value <= currentNode.value);
            if (goLeft) {
                if (currentNode.left == null) {
                    currentNode.left = new Node(value);
                    return root;
                } else {
                    currentNode = currentNode.left;
                }
            } else {
                if (currentNode.right == null) {
                    currentNode.right = new Node(value);
                    return root;
                } else {
                    currentNode = currentNode.right;
                }
            }
        }

        return root;
    }

    public static Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }

        Node parent = null, current = root;
        current = root;
        boolean goLeft = false;
        while(current != null && current.value != value) {
            goLeft = value <= current.value;
            parent = current;
            if (goLeft) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        /* No node with given value was found */
        if (current == null) {
            return root;
        }

        Node substitute = null;
        if (current.right != null) {
            substitute = pullMinimumOnTopOf(current.right);
            substitute.left = current.left;
        } else if (current.left != null) {
            substitute = pullMaximumOnTopOf(current);
        }

        /* The value was actually found in the root; the root is effectively null now */
        if (parent == null) {
            return substitute;
        } else if (goLeft) {
            parent.left = substitute;
        } else {
            parent.right = substitute;
        }

        return root;
    }

    static Node pullMaximumOnTopOf(Node node) {
        Node substitute;
        substitute = extractMaxFrom(node);
        substitute.left = node;
        return substitute;
    }

    static Node pullMinimumOnTopOf(Node node) {
        Node substitute;
        substitute = extractMinFrom(node);
        substitute.right = node;
        return substitute;
    }

    static Node extractMaxFrom(Node node) {
        Node parent = null, current = node;
        while (current.right != null) {
            parent = current;
            current = current.right;
        }
        if (parent != null) {
            parent.right = current.left;
        }
        return current;
    }

    static Node extractMinFrom(Node node) {
        Node parent = null, current = node;
        while (current.left != null) {
            parent = current;
            current = current.left;
        }
        if (parent != null) {
            parent.left = current.right;
        }
        return current;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Node {

        Node left, right;
        int value;

        Node(int value) {
            this.value = value;
        }

        public static NodeBuilder ofValue(int value) {
            return new NodeBuilder().value(value);
        }
    }
}


