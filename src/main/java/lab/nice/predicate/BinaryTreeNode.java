package lab.nice.predicate;

import java.util.*;

public class BinaryTreeNode<E> {
    public E value;
    public BinaryTreeNode<E> left;
    public BinaryTreeNode<E> right;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(E value) {
        this(value, null, null);
    }

    public BinaryTreeNode(E value, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public BinaryTreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<E> left) {
        this.left = left;
    }

    public BinaryTreeNode<E> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<E> right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTreeNode<?> that = (BinaryTreeNode<?>) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right);
    }

    @Override
    public String toString() {
        return "{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public List<E> preOrderTraversal(BinaryTreeNode<E> root) {
        List<E> res = new ArrayList<>();
        Stack<BinaryTreeNode<E>> stack = new Stack<>();
        BinaryTreeNode<E> current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                res.add(current.value);
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            current = current.right;
        }
        return res;
    }

    public List<E> preOrderTraversalRecursive(BinaryTreeNode<E> root) {
        List<E> res = new ArrayList<>();
        /*if (root == null) {
            return res;
        }
        res.add(root.value);
        res.addAll(preOrderTraversalRecursive(root.left));
        res.addAll(preOrderTraversalRecursive(root.right));*/
        preOrderTraversalRecursiveHelper(root, res);
        return res;
    }

    public void preOrderTraversalRecursiveHelper(BinaryTreeNode<E> root, List<E> res) {
        if (root == null) {
            return;
        }
        res.add(root.value);
        preOrderTraversalRecursiveHelper(root.left, res);
        preOrderTraversalRecursiveHelper(root.right, res);
    }

    public List<E> inorderTraversal(BinaryTreeNode<E> root) {
        List<E> res = new ArrayList<>();
        Stack<BinaryTreeNode<E>> stack = new Stack<>();
        BinaryTreeNode<E> current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            res.add(current.value);
            current = current.right;
        }
        return res;
    }

    public List<E> inorderTraversalRecursive(BinaryTreeNode<E> root) {
        List<E> res = new ArrayList<>();
        /*if (root == null) {
            return res;
        }
        res.addAll(inorderTraversalRecursive(root.left));
        res.add(root.value);
        res.addAll(inorderTraversalRecursive(root.right));*/
        inorderTraversalRecursiveHelper(root, res);
        return res;
    }

    public void inorderTraversalRecursiveHelper(BinaryTreeNode<E> root, List<E> res) {
        if (root != null) {
            if (root.left != null) {
                inorderTraversalRecursiveHelper(root.left, res);
            }
            res.add(root.value);
            if (root.right != null) {
                inorderTraversalRecursiveHelper(root.right, res);
            }
        }
    }

    public List<E> postOrderTraversal(BinaryTreeNode<E> root) {
        List<E> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<BinaryTreeNode<E>> stack = new Stack<>();
        BinaryTreeNode<E> current = root, lastVisited = root;
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.peek();
            if ((current.left == null && current.right == null) //leaf node
                    || (current.right == null && lastVisited == current.left) // right child node is null and left child node visited
                    || (lastVisited == current.right) // left and right child node visited
            ) {
                res.add(current.value);
                lastVisited = current;
                stack.pop();
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }

            }
        }
        return res;
    }

    public List<E> postOrderTraversalRecursive(BinaryTreeNode<E> root) {
        List<E> res = new ArrayList<>();
        /*if (root == null) {
            return res;
        }
        res.addAll(postOrderTraversalRecursive(root.left));
        res.addAll(postOrderTraversalRecursive(root.right));
        res.add(root.value);*/
        postOrderTraversalRecursiveHelper(root, res);
        return res;
    }

    public void postOrderTraversalRecursiveHelper(BinaryTreeNode<E> root, List<E> res) {
        if (root != null) {
            postOrderTraversalRecursiveHelper(root.left, res);
            postOrderTraversalRecursiveHelper(root.right, res);
            res.add(root.value);
        }
    }

    public List<E> levelOrderTraversal(BinaryTreeNode<E> root) {
        List<E> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        BinaryTreeNode<E> current = root;
        Queue<BinaryTreeNode<E>> queue = new LinkedList<>();
        queue.offer(current);
        while (!queue.isEmpty()) {
            current = queue.poll();
            res.add(current.value);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return res;
    }
}
