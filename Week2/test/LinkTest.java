import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bas on 30-11-2015.
 */
public class LinkTest
{

    @Test
    public void sortLinks()
    {
        KeyValuePair<Integer, String> a = new KeyValuePair<Integer, String>(1, "");
        KeyValuePair<Integer, String> b = new KeyValuePair<Integer, String>(2, "");
        KeyValuePair<Integer, String> c = new KeyValuePair<Integer, String>(3, "");
        KeyValuePair<Integer, String> d = new KeyValuePair<Integer, String>(4, "");
        KeyValuePair<Integer, String> e = new KeyValuePair<Integer, String>(5, "");
        KeyValuePair<Integer, String> f = new KeyValuePair<Integer, String>(6, "");

        Node.Link<Integer, String> l1 = new Node.Link();
        l1.rightValue = a;

        Node.Link<Integer, String> l2 = new Node.Link();
        l2.leftValue = a;
        l2.rightValue = b;

        Node.Link<Integer, String> l3 = new Node.Link();
        l3.leftValue = b;
        l3.rightValue = c;

        Node.Link<Integer, String> l4 = new Node.Link();
        l4.leftValue = c;
        l4.rightValue = d;

        Node.Link<Integer, String> l5 = new Node.Link();
        l5.leftValue = d;
        l5.rightValue = e;

        Node.Link<Integer, String> l6 = new Node.Link();
        l6.leftValue = e;
        l6.rightValue = f;

        Node.Link<Integer, String> l7 = new Node.Link();
        l7.leftValue = f;

        List<Node.Link<Integer, String>> list = new ArrayList();
        list.add(l4);
        list.add(l3);
        list.add(l2);
        list.add(l6);
        list.add(l5);
        list.add(l1);
        list.add(l7);

        Collections.sort(list);

        assertEquals(l1, list.get(0));
        assertEquals(l2, list.get(1));
        assertEquals(l3, list.get(2));
        assertEquals(l4, list.get(3));
        assertEquals(l5, list.get(4));
        assertEquals(l6, list.get(5));
        assertEquals(l7, list.get(6));

    }
}
