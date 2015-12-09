import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bas on 9-12-2015.
 * Reverse deletion = Kruskal Inverse
 */
public class ReverseDelete
{

    private final List<Edge> edges;
    int lastProcessed;

    public ReverseDelete(List<Edge> edges)
    {
        Collections.sort(edges);
        this.edges = edges;
        lastProcessed = edges.size() - 1;
    }

    public ReverseDelete(Graph graph)
    {
        this(generateEdges(graph));
    }

    public ReverseDelete()
    {
        this(new Graph());
    }

    public ReverseDelete(int size, int maxX, int maxY)
    {
        this(new Graph(size, maxX, maxY));
    }

    public ReverseDelete(int size)
    {
        this(new Graph(size));
    }

    private static List<Edge> generateEdges(Graph graph)
    {
        List<Edge> edges = new ArrayList<>();
        for (Node node1 : graph.getNodes())
        {
            for (Node node2 : graph.getNodes())
            {
                if (! node1.equals(node2))
                {
                    Edge edge = new Edge(node1, node2);
                    if (! edges.contains(edge))
                    {
                        edges.add(edge);
                    }
                }
            }
        }
        Collections.sort(edges);
        return edges;
    }

    public List<Edge> getEdges()
    {
        return edges;
    }

    boolean safeToDelete(Edge toRemove)
    {
        if (toRemove.getFirst().amountOfEdges() <= 1 || toRemove.getSecond().amountOfEdges() <= 1)
        {
            return false;
        } else
        {
            for (Edge current : edges)
            {
                if (! current.equals(toRemove) && hasPathWithout(toRemove.getFirst(), toRemove.getSecond(), toRemove))
                {
                    return true;
                }
            }
            return false;

        }
    }

    boolean isSolved()
    {
        for (Edge edge : edges)
        {
            if (safeToDelete(edge))
            {
                return false;
            }
        }
        return true;
    }

    boolean hasPathWithout(Node start, Node end, Edge toIgnore)
    {
        List<Edge> edges = new ArrayList<>();
        edges.add(toIgnore);
        return hasPathWithout(start, end, edges);
    }

    boolean hasPathWithout(Node start, Node end, List<Edge> toIgnore)
    {
        if (start.equals(end))
        {
            return true;
        }
        for (Edge edge : start.getEdges())
        {
            if (! toIgnore.contains(edge))
            {
                if (edge.contains(end))
                {
                    return true;
                }
                List<Edge> edges = new ArrayList<>(toIgnore);
                edges.add(edge);
                Node newNode;
                if (edge.getFirst().equals(start))
                {
                    newNode = edge.getSecond();
                } else
                {
                    newNode = edge.getFirst();
                }
                if (hasPathWithout(newNode, end, edges))
                {
                    return true;
                }
            }
        }
        return false;
    }

    void checkNext()
    {
        if (lastProcessed == 0 && this.isSolved())
        {
            if (this.isSolved())
            {
                lastProcessed = - 1;
            } else
            {
                lastProcessed = edges.size() - 1;
            }
        }
    }

    void takeStep()
    {
        if (lastProcessed > 0)
        {
            Edge removeable = edges.get(lastProcessed);
            if (safeToDelete(removeable))
            {
                edges.remove(removeable);
                removeable.getFirst().removeEdge(removeable);
                removeable.getSecond().removeEdge(removeable);
            }
            lastProcessed--;
        }
        checkNext();
    }

    void autoComplete()
    {
        while (lastProcessed >= 0)
        {
            takeStep();
        }
    }
}