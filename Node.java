package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    public Message getMessage() {
        return msg;
    }
    public void setMessage(Message message) {
        this.msg = message;
    }

    public void addEdge(Node node) {
        if (node != null && !edges.contains(node)) {
            edges.add(node);
        }
    }
    
    public boolean hasCycles() {
        Set<Node> visited = new HashSet<>();
        return hasCyclesHelper(this, visited);
    }
    
    private boolean hasCyclesHelper(Node node, Set<Node> visited) {
        if (visited.contains(node)) {
            return true;
        }
        visited.add(node);
        for (Node edge : node.edges) {
            if (hasCyclesHelper(edge, visited)) {
                return true;
            }
        }
        visited.remove(node);
        return false;
    }
   
    @Override
    public String toString() {
        return "Node [name=" + name + ", edges=" + edges + ", msg=" + msg + "]";
    }



}