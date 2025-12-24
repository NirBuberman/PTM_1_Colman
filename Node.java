package test;

import java.util.ArrayList;
import java.util.List;


public class Node {
    private String name;
    private List<Node> edges;
    private Message msg;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Node> getEdges() {
        return edges;
    }
    public void setEdges(List<Node> edges) {
        if (edges == null) {
            this.edges = new ArrayList<>();
        }else{
            this.edges = edges;
        }
    }

    public Message getMsg() {
        return msg;
    }
    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public void addEdge(Node node) {
        if (node != null && !edges.contains(node)) {
            edges.add(node);
        }
    }
   
    @Override
    public String toString() {
        return "Node [name=" + name + ", edges=" + edges + ", msg=" + msg + "]";
    }



}