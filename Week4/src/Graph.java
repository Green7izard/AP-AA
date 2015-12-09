import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bas on 9-12-2015.
 */
public class Graph
{

    public static final int defaultNodes = 10;
    public static final int defaultSize = 20;
    List<Node> nodes;

    public Graph(List<Node> nodes)
    {
        this.nodes = nodes;
    }

    public Graph(int size, int maxX, int maxY)
    {
        this(generateRandom(size, maxX, maxY));
    }

    public Graph(int size)
    {
        this(size, size * 2, size * 2);
    }

    public Graph()
    {
        this(defaultNodes, defaultSize, defaultSize);
    }

    public static List<Node> generateRandom(int size, int maxX, int maxY)
    {
        List<Node> nodes = new ArrayList<>(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++)
        {
            nodes.add(new Node(rand.nextInt(maxX), rand.nextInt(maxY)));
        }
        return nodes;
    }

    public List<Node> getNodes()
    {
        return nodes;
    }
}
