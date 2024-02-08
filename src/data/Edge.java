package data;

import util.Pair;

public class Edge {
    /**
     * Data class Edge graph
     * @author Vedernikov Vladislav
     * @version 1
     */
    private Pair<Vertex, Vertex> vertexPair;
    private int weight;

    public Edge(Pair<Vertex, Vertex> vertexPair, int weight) {
        this.vertexPair = vertexPair;
        this.weight = weight;
    }

    public Pair<Vertex, Vertex> getVertexPair() {
        return vertexPair;
    }

    public void setVertexPair(Pair<Vertex, Vertex> vertexPair) {
        this.vertexPair = vertexPair;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public Vertex getIncidentVertex(Vertex currentVertex){
        return vertexPair.getValue1()==currentVertex ? vertexPair.getValue2() : vertexPair.getValue1();

    }
}
