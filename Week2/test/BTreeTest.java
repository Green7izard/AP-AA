import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Bas on 25-11-2015.
 */
public class BTreeTest
{
    BTree<Integer, String> tree;

    @Before
    public void setUp(){
        tree = new BTree(3);
    }

    @Test
    public void creationTest(){
        assertNotNull(tree);
        assertEquals(0, tree.size());
    }

    @Test
    public void singleTest(){
        assertNull(tree.put(20, "2"));
        assertEquals(1, tree.size());
        assertEquals("2", tree.get(20));
    }

    @Test
    public void doubleTest(){
        assertNull(tree.put(20, "2"));
        assertNull(tree.put(25, "3"));
        assertEquals(2, tree.size());
        assertEquals("2", tree.get(20));
        assertEquals("3", tree.get(25));
    }

    @Test
    public void additionTest(){
        assertNull(tree.put(20, "2"));
        assertNull(tree.put(30, "2"));
        assertNull(tree.put(10, "2"));
        assertEquals(3, tree.size());

        assertNotNull(tree.put(10, "3"));
        assertEquals(3, tree.size());
    }

    @Test
    public void additionMultipleTreesTest(){
        int number = 50;
        for(int i = 0; i<number;i++)
        {
            System.out.println(i);
            assertNull(tree.put(i, i + ""));
        }
        System.out.println("Added All");
        assertEquals(number, tree.size());
        for(int i = 0; i<number;i++)
        {
            System.out.println("Get: "+ i);
            String s = tree.get(i);
            assertNotNull(s);
            assertEquals(i + "", s);
        }
    }



}
