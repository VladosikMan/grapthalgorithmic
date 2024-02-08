package algorithm;

import data.Edge;
import data.Graph;
import data.Vertex;
import util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Kruskal extends Graph {
    public Kruskal() {

    }

    public Kruskal(Set<Vertex> vertices, ArrayList<Edge> edges) {
        setVertexGraph(vertices);
        setEdgeGraph(edges);
    }

    public boolean solution(Set<Vertex> vertices, ArrayList<Edge> edges) {
        solutionAlgorithm(vertices, edges);
        return true;
    }

    public boolean solution() {
        solutionAlgorithm(vertices, edges);
        return true;
    }

    private boolean solutionAlgorithm(Set<Vertex> vertices, ArrayList<Edge> edges) {
        sortEdges(edges);
        // список под графов
        ArrayList<LinkedHashSet<Vertex>> verticesSetsList = new ArrayList<>();
        // остовный граф
        ArrayList<Edge> spanningSet = new ArrayList<>();
        while (spanningSet.size() < vertices.size() - 1) {

            //пару его вершин проверяем по списку подграф
            //рассматриваем реброов
            //если обе вершины нашлись в одном множестве, то удаляем это ребро и все
            //если обоих вершин не нигде, добавляем их в новый подграф, добавляем ребро и удаляем его
            //если мы нашли одну вершину в подграфе, а другую нигде, добавляем в него вершину, добавляем ребро и удаляем его
            //если обе вершины нашлись в разных подграфах, то обьединяем эти подграфы, добавляем и удаляем ребро

            int placeVertex1 = getPlaceVertexInSets(verticesSetsList, edges.get(0).getVertexPair().getValue1());
            int placeVertex2 = getPlaceVertexInSets(verticesSetsList, edges.get(0).getVertexPair().getValue2());

            if (placeVertex1 == 0 && placeVertex2 == 0) {
                verticesSetsList.add(new LinkedHashSet<Vertex>());
                verticesSetsList.get(verticesSetsList.size() - 1).add(edges.get(0).getVertexPair().getValue1());
                verticesSetsList.get(verticesSetsList.size() - 1).add(edges.get(0).getVertexPair().getValue2());
                spanningSet.add(edges.get(0));
                edges.remove(0);
                continue;
            }
            if (placeVertex1 == placeVertex2) {
                edges.remove(0);
                continue;
            }
            if (placeVertex1 == 0) {
                verticesSetsList.get(placeVertex2 - 1).add(edges.get(0).getVertexPair().getValue1());
                spanningSet.add(edges.get(0));
                edges.remove(0);
                continue;
            }
            if (placeVertex2 == 0) {
                verticesSetsList.get(placeVertex1 - 1).add(edges.get(0).getVertexPair().getValue2());
                spanningSet.add(edges.get(0));
                edges.remove(0);
                continue;
            }
            unionSets(verticesSetsList, placeVertex1, placeVertex2);
            spanningSet.add(edges.get(0));
            edges.remove(0);

        }
        for (Edge x : spanningSet) {
            System.out.println(x.getVertexPair().getValue1().getName() + " <---> " + x.getVertexPair().getValue2().getName() + " = " + x.getWeight());
        }
        return true;
    }

    private int getPlaceVertexInSets(ArrayList<LinkedHashSet<Vertex>> verticesSetsList, Vertex vertex) {
        for (int i = 0; i < verticesSetsList.size(); i++)
            if (verticesSetsList.get(i).contains(vertex))
                return i + 1;
        return 0;
    }

    private void unionSets(ArrayList<LinkedHashSet<Vertex>> verticesSetsList, int p1, int p2) {
        verticesSetsList.get(p1 - 1).addAll(verticesSetsList.get(p2 - 1));
        verticesSetsList.remove(p2 - 1);
    }

    private void sortEdges(ArrayList<Edge> edges) {
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
    }

    @Override
    public void setVertexGraph(Set<Vertex> vertices) {
        this.vertices = (LinkedHashSet<Vertex>) vertices;
    }

    @Override
    public void setEdgeGraph(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public void outGraph() {
        for (Vertex<Integer> x : vertices) {
            System.out.println(x.getName().toString());
        }
        for (Edge x : edges) {
            System.out.println(x.getVertexPair().getValue1().getName() + " <---> " + x.getVertexPair().getValue2().getName() + " = " + x.getWeight());
        }
    }
}
