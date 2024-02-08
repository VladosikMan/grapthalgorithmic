package algorithm;

import data.Edge;
import data.Graph;
import data.Vertex;
import util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Prima extends Graph {
    public Prima() {

    }

    public Prima(Set<Vertex> vertices, ArrayList<Edge> edges) {
        setVertexGraph(vertices);
        setEdgeGraph(edges);
    }

    public boolean solution(Set<Vertex> vertices, ArrayList<Edge> edges) {
        solutionAlgorithm((LinkedHashSet<Vertex>) vertices, edges);
        return true;
    }

    public boolean solution() {
        solutionAlgorithm(vertices, edges);
        return true;
    }

    public boolean solutionAlgorithm(LinkedHashSet<Vertex> vertices, ArrayList<Edge> edges) {
        int size = vertices.size();
        boolean result = true;
        ArrayList<Edge> spanningSet = new ArrayList<>();
        try {
            Vertex<Integer> currentVertex = edges.get(0).getVertexPair().getValue1();
            while (!vertices.isEmpty()) {
                vertices.remove(currentVertex);
                searchIncidentEdges(edges, currentVertex, vertices);
                currentVertex = searchMinEdge(vertices);
                if (currentVertex.getParentEdge() != null)
                    spanningSet.add(currentVertex.getParentEdge());
            }
        } catch (Exception e) {
            result = false;
        }
        outResult(result, spanningSet);
        return result;
    }

    private void outResult(boolean result, ArrayList<Edge> spanningSet) {
        if (result) {
            for (Edge x : spanningSet) {
                System.out.println(x.getVertexPair().getValue1().getName() + " <---> " + x.getVertexPair().getValue2().getName() + " = " + x.getWeight());
            }
        } else {
            System.out.println("Ошибка");
        }
    }

    private boolean testResult(ArrayList<Edge> spanningSet, int size) {
        if (spanningSet.size() < size - 1) {
        	System.out.println("Здесь ошибка");
            return false;
        }
        return true;
    }

    private void searchIncidentEdges(ArrayList<Edge> edges, Vertex currentVertex, LinkedHashSet<Vertex> vertices) {
        // поиск инциндетных ребёр

        for (Iterator<Edge> iterator = edges.iterator(); iterator.hasNext(); ) {
            Edge x = iterator.next();
            if (currentVertex == x.getVertexPair().getValue1() || currentVertex == x.getVertexPair().getValue2()) {
                // мы нашли это ребро
                // сравнием инциндетную вершину с ключами вершин, если ключ больше веса, то обновляем ключ и ребро родитель
                // удаляем ребро
                Vertex incidentVertex = x.getIncidentVertex(currentVertex);
                if (!vertices.contains(incidentVertex))
                    continue;
                if (incidentVertex.getKey() > x.getWeight()) {
                    incidentVertex.setKey(x.getWeight());
                    incidentVertex.setParentEdge(x);
                }
                iterator.remove();
            }
        }

        //обновили пирамиду
    }

    private Vertex searchMinEdge(LinkedHashSet<Vertex> vertices) {
        Vertex<Integer> minVertex = new Vertex(-1);
        for (Vertex x : vertices) {
            if (x.getKey() < minVertex.getKey())
                minVertex = x;
        }
        return minVertex;
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
            System.out.print(x.getName().toString());
            System.out.println();
        }

        for (Edge x : edges) {
            System.out.print(x.getVertexPair().getValue1().getName() + " <---> " + x.getVertexPair().getValue2().getName() + " = " + x.getWeight());
            System.out.println();
        }
    }
    public void outHelp(){
        System.out.println("Вывести помощь с работой");
    }
}