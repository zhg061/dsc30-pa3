/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.ArrayList;

import java.util.AbstractList;

/**
 * Doubly-Linked List Implementation
 *
 * @author Zhaoyi Guo
 * @since 4/20/19
 */
public class DoublyLinkedList<T> extends AbstractList<T> {
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            //constructors of the singleton Node
            data = element;



        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            //constructor of the node to create a single link node of previous
            // and next
            data = element;
            next = nextNode;
            prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            //set the element
            data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            //get the node element, which is data
            return data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            //set the next node to the node of n.
            next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            //get the next node in the list
            return next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            //set the previous node to the node p
            prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            //get the previous node in the list
            return prev;
        }

        /**
         * Remove this node from the list. 
         * Update previous and next nodes
         */
        public void remove() {
            //remove the node from the list
            prev.next = next;
            next.prev = prev;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        //constructors of the class, that set the head and tail to null, and
        //set the number of elements to zero
        head  = new Node(null);
        tail  = new Node(null);
        head.next = tail;
        tail.prev = head;
        nelems = 0;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        //add the element at the end of the linked list
        //implementation of adding the new data
        Node addElem = new Node(element);
        if (element == null) {
            throw new NullPointerException();
        }
        tail.prev.next = addElem;
        addElem.prev = tail.prev;
        tail.prev = addElem;
        addElem.next = tail;
        nelems++;
        return true;

    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * parameter: index, element
     * return: nothing returned, but the original linked list get changed by adding
     * an element at certain index, if there is no nullpointerexception or
     * indexoutofboundsexception.
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //add the element at the certain index
        //implementation of adding the new data
        Node addElem = new Node(element);
        if (element == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addElem.next = head.next;
            head.next.prev = addElem;
            addElem.prev = head;
            head.next = addElem;
            return;
        }
        Node nodeAfter = getNth(index);
        Node nodeBefore = getNth(index - 1);
        addElem.next = nodeAfter;
        nodeAfter.prev = addElem;
        nodeBefore.next = addElem;
        addElem.prev = nodeBefore;
        nelems++;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        //set everything as declared as in the constructor, so the
        //linked list get cleared
        head  = new Node(null);
        tail  = new Node(null);
        head.next = tail;
        tail.prev = head;
        nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * parameter: the object or the element
     * return: true if the original linked list contains the object , false otherwise
     */
    @Override
    public boolean contains(Object element) {
        T data = (T)element;
        //checks whether the orginal linked list
        // contains the object that we are looking for
        Node curHead = head.next;
        while (curHead != tail) {
            if (curHead.getElement().equals(element)) {
                return true;
            }
            curHead = curHead.next;
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * parameter: the index of the element that we give
     * return: the node from the linked list from the certain index
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        //get the node of the linked list at the certain index
        //if the curNode exceeds tail, then throw
        // index out of bounds exception
        int curIndex = 0;
        Node curNode = head.next;
        while (curIndex < index) {
            if (curNode == tail) {
                throw new IndexOutOfBoundsException();
            }
            curNode = curNode.next;
            curIndex++;
        }
        return curNode.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * return: the node of the linked list at the certain index
     */
    private Node getNth(int index) {
        //find the node of the linked list at certain index
        int curIndex = 0;
        Node curNode = head.next;
        while (curIndex < index) {
            curNode = curNode.next;
            curIndex++;
        }
        return curNode;
    }

    /**
     * Determine if the list empty
     *
     * return: if the nelems equals to zero, return true, otherwise, return false;
     */
    @Override
    public boolean isEmpty() {
        //check if the linked list is empty by checking whether the
        //element equals to zero
        return nelems == 0;
    }

    /**
     * Remove the element from position index in the list
     *
     * parameter: the index
     * return: nothing return, but the element at the certian index get removed
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        //remove the element from the linked list at the certain index provided
        if (index < 0 || index > size()-1 ) {
            throw new IndexOutOfBoundsException();
        }
        T removedNode = getNth(index).getElement();
        if (index == 0 && this.size() > 1) {
            head.next = head.next.next;
            return removedNode;
        }
        Node nodeAfter = getNth(index + 1);
        Node nodeBefore = getNth(index - 1);
        nodeBefore.next = nodeAfter;
        nodeAfter.prev = nodeBefore;
        nelems--;
        return removedNode;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * parameter: the index of the linked list, the element that
     * we want to put at that index
     * return: nothing changes but the original linked list get changed
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //set the element at the index, so the original list get changed
        // but there shouldn't be any index out of bounds exception
        // and the null pointer exception.
        if (index < 0 || index >= size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        Node originalNode = getNth(index);
        Node prevNode = getNth(index).prev;
        Node nextNode = getNth(index).next;
        Node newNode = new Node(element);
        prevNode.next = newNode;
        newNode.prev = prevNode;
        nextNode.prev = newNode;
        newNode.next = nextNode;
        return originalNode.getElement();
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * return: the size of the linked list
     */
    @Override
    public int size() {
        //return the size of the doubly linked list
        return this.nelems;
    }

    /**
     * Inserts another linked list of the same type into this one
     *
     * parameter: a doubly linked list that used to be inserted for
     * nothing returned but the original linked list is changed by inserting
     * the other list to the ori
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {
        //if the index is less than 0 or larger than that size, throw IndexOutOfBoundsException
        // if the other list is empty, don't change anything
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (otherList.size() == 0) {
            return;
        }
        //make a copy of the other list, so that it wont's change the original list as more splice
        // happens, also, adds the other lists to the original list at the certian index
        DoublyLinkedList<T> copyList = new DoublyLinkedList<T>();
        for (int i = 0; i < otherList.size(); i++) {
            copyList.add(otherList.get(i));
        }
        if (index == 0) {
            head.next.prev = copyList.tail.prev;
            copyList.tail.prev.next = head.next;
            copyList.head.next.prev = head;
            head = copyList.head;
            return;
        }

        Node nodeAfter = getNth(index);
        Node nodeBefore = getNth(index - 1);
        nodeBefore.next = copyList.head.next;
        copyList.head.next.prev = nodeBefore;
        nodeAfter.prev = copyList.tail.prev;
        copyList.tail.prev.next = nodeAfter;
        nelems += copyList.size();


    }

    /**
     * Determine the starting indices that match the subSequence
     *
     * parameter: doubly linked list, subsequences
     * return: the list that contain indexes of the original list that
     * matches the subsequence linked list
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        //A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        //return a list that contains the indexes of original Doubly linked list that
        //matched the subsequences
        int curIndex = 0;
        while (curIndex < this.size()) {
            int subIndex = 0;
            boolean pass = true;
            while (subIndex < subsequence.size() && subIndex + curIndex <= this.size()) {
                if (subIndex + curIndex == this.size() && subIndex < subsequence.size()) {
                    pass = false;
                    break;
                }
                if (!this.get(subIndex + curIndex).equals(subsequence.get(subIndex))) {
                    pass = false;
                    break;
                }
                if (subIndex + curIndex > this.size()) {
                    break;
                }
                subIndex++;
            }
            if (pass) {
                indices.add(curIndex);
            }
            curIndex++;

        }



        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }


}



