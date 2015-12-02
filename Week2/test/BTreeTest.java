import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        assertEquals(0, tree.getRoot().amountOfPairs());
        assertEquals(1, tree.size());
    }

    @Test
    public void additionTest(){
        Node<Integer, String> root = tree.getRoot();
        tree.put(20, "2");
        tree.put(30, "2");
        tree.put(10, "2");

        assertEquals(3, tree.getRoot().amountOfPairs());
        assertEquals(1, tree.size());

        tree.put(10, "3");

        assertEquals(3, tree.getRoot().amountOfPairs());
        assertEquals(1, tree.size());
        assertEquals(root, tree.getRoot());
    }

    //First instance of a  split
    @Test
    public void addition4Test(){
        int number = 4;
        for(int i = 1; i<=number;i++)
        {
            tree.put(i, i+"");
            checkTree(tree);
        }
        assertEquals(number, tree.getRoot().amountOfPairs());
        for(int i = 1; i<=number;i++)
        {
            String s = tree.get(i);
            assertNotNull("failed to get "+i, s);
            assertEquals(i+"", s);
        }
    }

    //More then one instance of a double split
    @Test
    public void addition12Test(){
        int number = 12;
        for(int i = 1; i<=number;i++)
        {
            tree.put(i, i+"");
            checkTree(tree);
        }
        assertEquals(number, tree.getRoot().amountOfPairs());
        for(int i = 1; i<=number;i++)
        {
            String s = tree.get(i);
            assertNotNull("failed to get "+i, s);
            assertEquals(i+"", s);
        }
    }

    private void checkTree(BTree<Integer, String> tree)
    {
        checkFullNode(tree.getRoot());
    }
    private void checkFullNode(Node<Integer, String> node)
    {
        checkNodeValues(node);
        for(Node.Link<Integer, String> link : node.getLinks())
        {
            if(link.subnode!=null)
            {
                checkFullNode(link.subnode);
            }
        }
    }

    private void checkNodeValues(Node<Integer, String> node){
        List<Node.Link<Integer, String>> list = node.getLinks();
        for(int i = 0; i<list.size();i++)
        {
            Node.Link<Integer, String> link = list.get(i);
            if(i==0)
            {
                assertNull(link.leftValue);
            }
            else
            {
                assertNotNull(link.leftValue);
            }
            if(i==list.size()-1)
            {
                assertNull(link.rightValue);
            }
            else
            {
                assertNotNull(link.rightValue);
            }
        }
    }

    @Test
    public void additionMultipleTreesTest(){
        int number = 25;
        for(int i = 1; i<=number;i++)
        {
            tree.put(i, i + "");
        }
        assertEquals(number, tree.getRoot().amountOfPairs());
        for(int i = 1; i<=number;i++)
        {
            String s = tree.get(i);
            assertNotNull("failed to get "+i, s);
            assertEquals(i+"", s);
        }
    }



}
