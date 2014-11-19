package pl.mrybak.datastructures;

/**
 * There are two approaches for implementing a tree, either using nested Node class or not.
 * Code below now uses Node class. This is for learning purposes (i.e discovering differences b'ween two approaches)
 */
public class BST<T extends Comparable<T>> {

    Node<T> root;

    private static class Node<T> {
        private T value;
        private Node<T> left, right;

        public Node(T elem) {
            this.value = elem;
        }

        public Node(T elem, Node<T> left, Node<T> right) {
            this.value = elem;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Insert elem into tree; duplicates are not allowed
     */
    public void insert(T elem) {
        root = insert(root, elem);
    }

    private Node<T> insert(Node<T> tree, T elem) {
        if (tree == null) {
            return new Node<T>(elem);
        }

        int compResult = tree.value.compareTo(elem);
        if (compResult > 0) {
            tree.left = insert(tree.left, elem);
        }
        if (compResult < 0) {
            tree.right = insert(tree.right, elem);
        }

        return tree;
    }

    ;


    /**
     * Check if elem is in this tree
     */
    public boolean contains(T elem) {
        return contains(root, elem);
    }

    private boolean contains(Node<T> tree, T elem) {
        if (tree == null) return false;

        int compResult = tree.value.compareTo(elem);

        if (compResult > 0) {
            return contains(tree.left, elem);
        }
        if (compResult < 0) {
            return contains(tree.right, elem);
        }

        return true;
    }

    ;


    /**
     * Get height of this tree
     */
    public int height() {
        return height(root);
    }

    ;

    private int height(Node<T> tree) {
        if (tree == null) return 0;
        return 1 + Math.max(height(tree.left), height(tree.right));
    }


    /**
     * Get node connected with elem (inner usage);
     * May be useful as public method when key-value nodes are introducted
     */
    private Node<T> get(T elem) {
        return get(root, elem);
    }

    private Node<T> get(Node<T> tree, T elem) {
        if (tree == null) return null;

        int compResult = tree.value.compareTo(elem);

        if (compResult > 0) {
            return get(tree.left, elem);
        }
        if (compResult < 0) {
            return get(tree.right, elem);
        }

        return tree;
    }


    /**
     * Remove elem from this tree
     */
    public void delete(T elem) {
        root = delete(root, elem);
    }

    private Node<T> delete(Node<T> tree, T elem) {
        if (tree == null) {
            return null;
        }

        int compResult = tree.value.compareTo(elem);
        if (compResult > 0) {
            tree.left = delete(tree.left, elem);
            return tree;
        }
        if (compResult < 0) {
            tree.right = delete(tree.right, elem);
            return tree;
        }

        // compResult == 0 so we are in the node we want to delete
        return deleteRoot(tree);
    }

    private Node<T> deleteRoot(Node<T> tree) {
        if (tree == null || (tree.left == null && tree.right == null)) {
            return null;
        }

        if (tree.left == null && tree.right != null) {
            return tree.right;
        }

        if (tree.right == null && tree.left != null) {
            return tree.left;
        }

        T minValue = findMin(tree.right);
        tree.right = deleteMin(tree.right);
        tree.value = minValue;

        return tree;
    }

    /**
     * Find or delete minimal element in this tree
     */
    public T findMin() {
        return root == null ? null : findMin(root);
    }

    private T findMin(Node<T> tree) {
        return tree.left == null ?
                tree.value :
                findMin(tree.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node<T> deleteMin(Node<T> tree) {
        if (tree == null) {
            return null;
        }
        if (tree.left == null) {
            return tree.right;
        }

        tree.left = deleteMin(tree.left);
        return tree;
    }


    /**
     * Below: BST traversal methods
     */

    public void enumeratePreOrder() {
        enumeratePreOrder(root);
    }

    ;

    private void enumeratePreOrder(Node<T> tree) {
        if (tree != null) {
            System.out.println(tree.value);
            enumeratePreOrder(tree.left);
            enumeratePreOrder(tree.right);
        }
    }

    ;

    public void enumerateInOrder() {
        enumerateInOrder(root);
    }

    ;

    private void enumerateInOrder(Node<T> tree) {
        if (tree != null) {
            enumerateInOrder(tree.left);
            System.out.println(tree.value);
            enumerateInOrder(tree.right);
        }
    }

    ;

    public void enumeratePostOrder() {
        enumeratePostOrder(root);
    }

    ;

    private void enumeratePostOrder(Node<T> tree) {
        if (tree != null) {
            enumeratePostOrder(tree.left);
            enumeratePostOrder(tree.right);
            System.out.println(tree.value);
        }
    }

    ;


    public void enumerateBFS() {
        SinglyLinkedList<Node<T>> queue = new SinglyLinkedList<>();
        queue.append(root);

        while (!queue.empty()) {
            Node<T> node = queue.pop();
            System.out.println(node.value);
            if (node.left != null) {
                queue.append(node.left);
            }
            if (node.right != null) {
                queue.append(node.right);
            }
        }
    }

    ;
}
