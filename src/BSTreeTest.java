import org.junit.Before;

import static org.junit.Assert.*;

class BSTreeTest {

    BSTree tree1;
    BSTree tree2;
    BSTree tree3;
    @Before
    void setUp() {
        tree1 = new BSTree<>();
        tree2 = new BSTree<>();
        tree3 = new BSTree<>();
    }

    @org.junit.Test
    void getRoot() {
        assertNull(tree1.getRoot());

        tree2.insert(5);
        assertEquals(5, tree2.getRoot().getKey());

        tree3.insert(5);
        tree3.insert(6);
        tree3.insert(4);
        assertEquals(5, tree3.getRoot().getKey());

        tree1 = new BSTree<>();
        tree2 = new BSTree<>();
        tree3 = new BSTree<>();
    }

    @org.junit.Test
    void getSize() {
        assertEquals(0, tree1.getSize());

        tree2.insert(5);
        tree2.insert(6);
        tree2.insert(7);
        assertEquals(3, tree2.getSize());

        tree3.insert(8);
        assertEquals(1, tree3.getSize());


    }



    @org.junit.Test
    void insert() {
        tree1 = new BSTree<>();
        tree2 = new BSTree<>();
        tree3 = new BSTree<>();

        tree1.insert(5);
        assertEquals(1, tree1.getSize());

        tree2.insert(6);
        tree2.insert(7);
        assertEquals(6, tree2.getRoot().getKey());
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    void insertError() {
        tree3.insert(null);
    }

    @org.junit.Test
    void findKey() {
        tree1 = new BSTree<>();
        tree2 = new BSTree<>();
        tree3 = new BSTree<>();

        tree1.insert(5);
        assertTrue(tree1.findKey(5));

        tree2.insert(2);
        assertFalse(tree2.findKey(5));
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    void findError() {
        tree3.findKey(null);
    }

    @org.junit.Test
    void insertData() {
        tree1 = new BSTree<>();
        tree2 = new BSTree<>();
        tree3 = new BSTree<>();
    }

    @org.junit.Test
    void findDataList() {
    }

    @org.junit.Test
    void findHeight() {
    }
}