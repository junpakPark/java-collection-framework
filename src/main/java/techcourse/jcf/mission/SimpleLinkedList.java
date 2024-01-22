package techcourse.jcf.mission;

import java.util.Objects;

public class SimpleLinkedList implements SimpleList {

    private int size;

    private Node first;
    private Node last;

    public SimpleLinkedList() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(String value) {
        return indexOf(value) >= 0;
    }


    private Node node(int index) {
        Node head = first;

        for (int i = 0; i < index; i++) {
            head = head.next;
        }

        return head;
    }

    private void linkLast(String value) {
        final Node tail = last;
        Node newNode = new Node(value, null);
        last = newNode;
        if (tail == null) {
            first = newNode;
        } else {
            tail.next = newNode;
        }
        size = size + 1;
    }

    private void linkBefore(String value, int index) {
        Node newNode;

        if (index == 0) {
            newNode = new Node(value, first);
            first = newNode;
        } else {
            final Node prev = node(index - 1);
            newNode = new Node(value, prev.next);
            prev.next = newNode;
        }

        size = size + 1;
    }



    @Override
    public boolean add(String value) {
        linkLast(value);
        return true;
    }

    @Override
    public void add(int index, String value) {
        Objects.checkIndex(index, size + 1);

        if (index == size) {
            linkLast(value);
            return;
        }
        linkBefore(value, index);
    }

    private String unlink(final Node prev, final Node node) {
        final Node next = node.next;
        final String element = node.item;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            if (next == null) {
                last = prev;
            }
        }

        node.item = null;
        node.next = null;
        size = size - 1;

        return element;
    }

    @Override
    public boolean remove(String value) {
        Node prev = null;
        Node current = first;

        while (current != null) {
            if (Objects.equals(value, current.item)) {
                unlink(prev, current);
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }


    @Override
    public String remove(int index) {
        Objects.checkIndex(index, size + 1);

        if (index == 0) {
            return unlink(null, first);
        }
        final Node prev = node(index - 1);

        return unlink(prev, prev.next);
    }

    @Override
    public void clear() {
        Node x = first;
        while (x != null) {
            Node next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }

        first = last = null;
        size = 0;
    }

    @Override
    public String get(int index) {
        Objects.checkIndex(index, size + 1);

        return node(index).item;
    }

    @Override
    public String set(int index, String value) {
        Objects.checkIndex(index, size + 1);

        Node node = node(index);
        String oldValue = node.item;
        node.item = value;

        return oldValue;
    }

    @Override
    public int indexOf(String value) {
        int index = 0;

        for (Node x = first; x != null; x = x.next) {
            if (Objects.equals(value, x.item)) {
                return index;
            }
            index = index + 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        if (first == null) {
            return "null";
        }

        if (size == 0) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (Node x = first; x != null; x = x.next) {
            b.append(x.item);
            if (x.next != null) {
                b.append(", ");
            }
        }
        b.append(']');

        return b.toString();
    }

    private static class Node {
        String item;
        Node next;

        Node(String element, Node next) {
            this.item = element;
            this.next = next;
        }
    }
}
