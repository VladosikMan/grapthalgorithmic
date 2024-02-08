package data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Graph implements IGraph{
    protected LinkedHashSet<Vertex> vertices;
    protected ArrayList<Edge> edges;
}
