/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.Arrays;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {

    DoublyLinkedList<Integer> test;
    DoublyLinkedList<Integer> test1;
    DoublyLinkedList<Integer> test2;


    @org.junit.Before
    public void setUp() throws Exception {
        test = new DoublyLinkedList<>();
        test1 = new DoublyLinkedList<>();
        test2 = new DoublyLinkedList<>();
    }

    @org.junit.Test
    public void add() {
        assertEquals(true, test.add(2));
        assertEquals(1, test.size());
        assertEquals(new Integer(2), test.get(0));
        assertEquals(true, test.add(2));
        assertEquals(2, test.size());
        assertEquals(new Integer(2), test.get(1));
        assertEquals(true, test.add(3));
        assertEquals(3, test.size());
        assertEquals(new Integer(3), test.get(2));



    }

    @org.junit.Test(expected = NullPointerException.class)
    public void testNullPointerExceptionInAdd(){
        test.add(null);
    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInAdd(){
        test.add(10, 100);
    }

    @org.junit.Test
    public void add1() {
        assertEquals(true, test.add(2));
        test.add(8);
        test.add(1, 9);
        assertEquals(new Integer(2), test.get(0));
        assertEquals(new Integer(9), test.get(1));
        assertEquals(new Integer(8), test.get(2));
        test.add(2, 19);
        assertEquals(new Integer(19), test.get(2));
        assertEquals(true, test.contains(19));
        test.add(0, 19);
        assertEquals(new Integer(19), test.get(0));
    }


    @org.junit.Test
    public void clear() {
        test.add(2);
        test.add(8);
        test.add(1, 9);
        test.clear();
        assertEquals(0, test.size());
        test.add(2);
        test.remove(0);
        test.add(9);
        test.clear();
        assertEquals(0, test.size());

    }

    @org.junit.Test
    public void contains() {
        test.add(2);
        test.add(22);
        test.add(222);
        assertEquals(true, test.contains(2));
        assertEquals(true, test.contains(222));
        assertEquals(false, test.contains(4));

    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInGet(){
        test.get(1);
        test.get(0);
    }

    @org.junit.Test
    public void get() {
        test.add(2);
        test.add(22);
        test.add(222);
        assertEquals(new Integer(2), test.get(0));
        assertEquals(new Integer(22), test.get(1));
        assertEquals(new Integer(222), test.get(2));

    }

    @org.junit.Test
    public void isEmpty() {
        test.add(2);
        test.add(22);
        test.add(222);
        assertEquals(false, test.isEmpty());
        test.clear();
        assertEquals(true, test.isEmpty());
        test.add(2);
        assertEquals(false, test.isEmpty());
    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInRemove(){
        test.remove(1);
    }




    @org.junit.Test
    public void remove() {
        test.add(2);
        test.add(22);
        test.add(222);
        assertEquals(new Integer(2), test.remove(0));
        assertEquals(new Integer(22), test.remove(0));
        assertEquals(new Integer(222), test.remove(0));

    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInSet(){
        test.set(1, 1);
    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void testNullPointerExceptionInRemove(){
        test.set(1, null);
    }

    @org.junit.Test
    public void set() {
        test.add(2);
        test.add(22);
        test.add(222);
        test.set(1, 3);
        assertEquals(new Integer(3), test.get(1));
        test.set(0, 33);
        assertEquals(new Integer(33), test.get(0));
        test.set(0, 3333);
        assertEquals(new Integer(3333), test.get(0));
    }

    @org.junit.Test
    public void size() {
        assertEquals(0, test.size());
        test.add(2);
        test.add(22);
        test.set(0, 1);
        assertEquals(2, test.size());
        test.add(222);
        assertEquals(3, test.size());
    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInSPlice(){
        test.splice(1, test1);
    }

    @org.junit.Test
    public void splice() {
        test1.add(1);
        test1.add(2);
        test1.add(3);

        test.add(8);

        test1.splice(1, test);
        assertEquals(new Integer(1), test1.get(0));
        assertEquals(new Integer(8), test1.get(1));
        assertEquals(new Integer(2), test1.get(2));
        assertEquals(new Integer(3), test1.get(3));

        test1.splice(1, test);
        assertEquals(new Integer(1), test1.get(0));
        assertEquals(new Integer(8), test1.get(1));
        assertEquals(new Integer(8), test1.get(2));
        assertEquals(new Integer(2), test1.get(3));
        assertEquals(new Integer(3), test1.get(4));

        test1.splice(0, test);
        assertEquals(new Integer(8), test1.get(0));
        assertEquals(new Integer(1), test1.get(1));
        assertEquals(new Integer(8), test1.get(2));
        assertEquals(new Integer(8), test1.get(3));
        assertEquals(new Integer(2), test1.get(4));
        assertEquals(new Integer(3), test1.get(5));


        test.add(7);
        test1.splice(4, test);
        assertEquals(new Integer(8), test1.get(0));
        assertEquals(new Integer(1), test1.get(1));
        assertEquals(new Integer(8), test1.get(2));
        assertEquals(new Integer(8), test1.get(3));
        assertEquals(new Integer(8), test1.get(4));
        assertEquals(new Integer(7), test1.get(5));
        assertEquals(new Integer(2), test1.get(6));
        assertEquals(new Integer(3), test1.get(7));
//        test1.add(1);
//        test.add(2);
//        test.splice(0, test1);
//        assertEquals(new Integer(1), test.get(0));
//        assertEquals(new Integer(2), test.get(1));
//        test.add(3);
//        test.splice(1, test1);
//        assertEquals(new Integer(1), test.get(0));
//        assertEquals(new Integer(1), test.get(1));
////        assertEquals(new Integer(2), test.get(2));
//        assertEquals(new Integer(3), test.get(3));
    }

    @org.junit.Test
    public void match() {
        test.add(1);
        test.add(2);
        test.add(3);
        test1.add(4);
        test1.add(5);
        test1.add(6);
        test1.add(1);
        test1.add(2);
        test1.add(3);
        assertArrayEquals(new int[] {3}, test1.match(test));
        test1.add(1);
        test1.add(2);
        test1.add(3);
        assertArrayEquals(new int[] {3, 6}, test1.match(test));
        test.add(1);
        test.add(1);
        test.add(1);
        test.add(1);
        test2.add(1);
        System.out.println(Arrays.toString(test.match(test2)));
        assertArrayEquals(new int[] {0, 3, 4, 5, 6}, test.match(test2));
        test2.add(1);
        System.out.println(Arrays.toString(test.match(test2)));
        assertArrayEquals(new int[] {3, 4, 5}, test.match(test2));

    }

}