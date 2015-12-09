import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Bas on 9-12-2015.
 */
public class EdgeTest
{

    @Test
    public void HasTest()
    {
        Node Node1 = new Node(0,0);
        Node Node2 = new Node(1,0);
        Edge edge1 = new Edge(Node1, Node2);
        assertTrue(edge1.contains(Node1));
        assertTrue(edge1.contains(Node2));
        assertTrue(edge1.isEdge(Node1, Node2));
        assertTrue(edge1.isEdge(Node2, Node1));
    }

    @Test
    public void EqualsTest()
    {
        Node Node1 = new Node(0,0);
        Node Node2 = new Node(1,0);
        Node Node3 = new Node(1,1);
        Edge edge1 = new Edge(Node1, Node2);
        Edge edge2 = new Edge(Node1, Node3);
        List<Edge> edges = new ArrayList<Edge>(2);
        edges.add(edge1);
        edges.add(edge2);

        assertTrue(edges.contains(new Edge(Node2, Node1)));
        assertTrue(edges.contains(new Edge(Node3, Node1)));
    }

    @Test
    public void CompareTest()
    {
        Node Node1 = new Node(0,0);
        Node Node2 = new Node(1,0);
        Node Node3 = new Node(1,1);
        Edge edge1 = new Edge(Node1, Node2);
        Edge edge2 = new Edge(Node1, Node3);
        List<Edge> edges = new ArrayList<Edge>(2);
        edges.add(edge1);
        edges.add(edge2);
        Collections.sort(edges);
        assertEquals(edge1, edges.get(0));
        assertEquals(edge2, edges.get(1));
    }
}
