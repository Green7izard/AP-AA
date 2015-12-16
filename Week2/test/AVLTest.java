import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Bas on 16-12-2015.
 */
public class AVLTest
{
    @Test
    public void comparisonTest()
    {
        KeyValuePair<Integer, Integer> rootValue = new KeyValuePair(2, 2);
        KeyValuePair<Integer, Integer> leftValue = new KeyValuePair(1, 1);
        KeyValuePair<Integer, Integer> rightValue = new KeyValuePair(3, 3);

        AVLNode<Integer, Integer> root = new AVLNode<Integer, Integer>(rootValue);
        AVLNode<Integer, Integer> left = new AVLNode<Integer, Integer>(leftValue);
        AVLNode<Integer, Integer> right = new AVLNode<Integer, Integer>(rightValue);
        root.leftValue = left;
        root.rightValue = right;
        assertEquals(left, root.getNextNode(1));
        assertEquals(right, root.getNextNode(18));
    }

    @Test
    public void firstDoubleRotationTest()
    {
        AVLNode<Integer, String> tree = new AVLNode<Integer, String>(new KeyValuePair<Integer, String>(0, "0"));
        int number = 4;
        for (int i = 1; i <= number; i++)
        {
            assertNull(tree.insert(i, i + ""));
            tree = tree.getRoot();
            int balanceFactor = tree.balanceFactor();
            assertTrue("BalanceFactor: "+balanceFactor+ " after inserting: "+i, -2<balanceFactor && balanceFactor<2);
        }
        assertEquals(number+1, tree.getRoot().size());

        for (int i = 0; i <= number; i++)
        {
            String s = tree.get(i);
            assertNotNull("failed to get " + i, s);
            assertEquals(i + "", s);
        }

    }

    @Test
    public void additionLargeTest()
    {
        AVLNode<Integer, String> tree = new AVLNode<Integer, String>(new KeyValuePair<Integer, String>(0, "0"));
        int number = 100;
        for (int i = 1; i <= number; i++)
        {
            assertNull(tree.insert(i, i + ""));
            tree = tree.getRoot();
            int balanceFactor = tree.balanceFactor();
            assertTrue("BalanceFactor: " + balanceFactor + " after inserting: " + i, - 2 < balanceFactor && balanceFactor < 2);
        }
        assertEquals(number+1, tree.getRoot().size());

        for (int i = 0; i <= number; i++)
        {
            String s = tree.get(i);
            assertNotNull("failed to get " + i, s);
            assertEquals(i + "", s);
        }

    }
}
