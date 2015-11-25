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
        int number = 5000;
        for(int i = 0; i<number;i++)
        {
            assertNull(tree.put(i, i+""));
        }
        assertEquals(number, tree.size());
        for(int i = 0; i<number;i++)
        {
            String s = tree.get(i);
            assertNotNull(s);
            assertEquals(i+"", s);
        }
    }



}
