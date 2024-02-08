package data;

import java.util.ArrayList;
import java.util.Set;

public interface IGraph {
    /**
     * Interface work with graph
     * @author Vedernikov Vladislav
     * @version 1
     */

    void setVertexGraph(Set<Vertex> vertices);
    void setEdgeGraph(ArrayList<Edge> edges);

    void outGraph();
}
