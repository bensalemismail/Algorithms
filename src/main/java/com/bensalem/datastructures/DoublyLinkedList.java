package com.bensalem.datastructures;

/**
 * Created by Ismail BENSALEM on 01/10/2023
 */
public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public void setHead(Node<T> node) {
        if (head == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        insertBefore(head, node);
    }

    public void setTail(Node<T> node) {
        if (tail == null) {
            setHead(node);
            return;
        }
        insertAfter(tail, node);
    }

    public void insertBefore(Node<T> node, Node<T> nodeToInsert) {
        if (nodeToInsert == head && nodeToInsert == tail) return;
        remove(nodeToInsert);
        Node<T> prevNode = node.prev;
        if (prevNode != null) {
            prevNode.next = nodeToInsert;
            nodeToInsert.prev = prevNode;
        } else head = nodeToInsert;
        nodeToInsert.next = node;
        node.prev = nodeToInsert;
    }

    public void insertAfter(Node<T> node, Node<T> nodeToInsert) {
        if (nodeToInsert == head && nodeToInsert == tail) return;
        remove(nodeToInsert);
        Node<T> nextNode = node.next;
        nodeToInsert.prev = node;
        if (nextNode == null) this.tail = nodeToInsert;
        else {
            nodeToInsert.next = nextNode;
            nextNode.prev = nodeToInsert;
        }
        node.next = nodeToInsert;
    }

    public void insertAtPosition(int position, Node<T> nodeToInsert) {
        if (position == 0) {
            setHead(nodeToInsert);
            return;
        }
        int index = 0;
        Node<T> currentNode = head;

        while (currentNode != null && index++ != position) currentNode = currentNode.next;

        if (currentNode == null) setTail(nodeToInsert);
        else insertBefore(currentNode, nodeToInsert);
    }

    public void removeNodesWithValue(T value) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            Node<T> nodeToRemove = currentNode;
            currentNode = currentNode.next;
            if (nodeToRemove.value == value)
                remove(nodeToRemove);
        }
    }

    public void remove(Node<T> node) {
        if (node == head) head = head.next;
        else if (node == tail) tail = tail.prev;
        else removeBinding(node);

    }

    private void removeBinding(Node<T> node) {
        final Node<T> prevNode = node.prev;
        final Node<T> nextNode = node.next;
        if (prevNode != null) {
            prevNode.next = node.next;
        }
        if (nextNode != null) {
            nextNode.prev = node.prev;
        }
        node.prev = null;
        node.next = null;
    }

    public boolean containsNodeWithValue(T value) {
        if (this.head != null && this.head.value == value) return true;
        else if (this.tail != null && this.tail.value == value) return true;

        Node<T> currentNode = this.head;
        while (currentNode != null && currentNode.value != value) {
            currentNode = currentNode.next;
        }
        return currentNode != null;
    }


    static class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private final T value;

        public Node(T value) {
            this.value = value;
        }

    }
}



