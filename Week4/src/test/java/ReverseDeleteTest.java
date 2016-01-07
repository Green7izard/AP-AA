import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bas on 9-12-2015.
 */
public class ReverseDeleteTest
{

    public List<Edge> getSimpleTestEdges()
    {
        List<Edge> list = new ArrayList<Edge>(8);
        Node n1 = new Node(0, 0);
        Node n2 = new Node(0, 2);
        Node n3 = new Node(1, 1);
        Node n4 = new Node(2, 0);
        Node n5 = new Node(2, 2);

        list.add(new Edge(n1, n2));
        list.add(new Edge(n2, n5));
        list.add(new Edge(n4, n5));
        list.add(new Edge(n1, n4));

        list.add(new Edge(n1, n3));
        list.add(new Edge(n2, n3));
        list.add(new Edge(n4, n3));
        list.add(new Edge(n5, n3));
        return list;
    }

    @Test
    public void initRemovableTest()
    {
        List<Edge> edges = getSimpleTestEdges();
        ReverseDelete grapher = new ReverseDelete(edges);
        for (Edge edge : edges)
        {
            String text = edge.getFirst().getX() + "," + edge.getFirst().getY() + "<>" + edge.getSecond().getX() + "," + edge.getSecond().getY();
            assertTrue(text, grapher.safeToDelete(edge));
        }
    }

    @Test
    public void isolationTest()
    {
        List<Edge> edges = getSimpleTestEdges();
        ReverseDelete grapher = new ReverseDelete(edges);
        for (int i = edges.size() - 1; i >= 0; i--)
        {
            Edge removeable = edges.get(i);
            if (i >= 4)
            {
                assertFalse(grapher.isSolved());
                assertTrue(grapher.safeToDelete(removeable));
                edges.remove(removeable);
                removeable.getFirst().removeEdge(removeable);
                removeable.getSecond().removeEdge(removeable);

            } else
            {
                assertFalse(grapher.safeToDelete(removeable));
            }
        }
        assertTrue(grapher.isSolved());
    }

    @Test
    public void deformTest()
    {
        List<Edge> edges = getSimpleTestEdges();
        ReverseDelete grapher = new ReverseDelete(edges);

        assertFalse(grapher.isSolved());
        assertTrue(grapher.safeToDelete(edges.get(0)));
        edges.get(0).getFirst().removeEdge(edges.get(0));
        edges.get(0).getSecond().removeEdge(edges.get(0));
        edges.remove(0);

        assertFalse(grapher.isSolved());
        assertTrue(grapher.safeToDelete(edges.get(0)));
        edges.get(0).getFirst().removeEdge(edges.get(0));
        edges.get(0).getSecond().removeEdge(edges.get(0));
        edges.remove(0);

        assertFalse(grapher.isSolved());
        assertTrue(grapher.safeToDelete(edges.get(0)));
        edges.get(0).getFirst().removeEdge(edges.get(0));
        edges.get(0).getSecond().removeEdge(edges.get(0));
        edges.remove(0);

        assertFalse(grapher.isSolved());
        assertTrue(grapher.safeToDelete(edges.get(1)));
        edges.get(1).getFirst().removeEdge(edges.get(1));
        edges.get(1).getSecond().removeEdge(edges.get(1));
        edges.remove(1);

        for (Edge edge : edges)
        {
            assertFalse(grapher.safeToDelete(edge));
        }
        assertTrue(grapher.isSolved());
    }

    @Test
    public void stepCompleteTest()
    {
        List<Edge> edges = getSimpleTestEdges();
        ReverseDelete grapher = new ReverseDelete(edges);
        for (int i = 0; i < edges.size(); i++)
        {
            grapher.takeStep();
        }
        assertTrue(grapher.isSolved());
    }

    @Test
    public void AutoSolveTest()
    {
        List<Edge> edges = getSimpleTestEdges();
        ReverseDelete grapher = new ReverseDelete(edges);
        grapher.autoComplete();
        assertTrue(grapher.isSolved());
    }

}
