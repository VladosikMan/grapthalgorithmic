package data;

public class Vertex<T> {
    /**
     * Data class Vertex graph
     */
    private T name;

    public Vertex(){

    }
    public Vertex(T name) {
        this.name = name;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }
    //аналог бесконечности ключа
    private int key = Integer.MAX_VALUE;
    private Edge parentEdge;


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Edge getParentEdge() {
        return parentEdge;
    }

    public void setParentEdge(Edge parentEdge) {
        this.parentEdge = parentEdge;
    }
}