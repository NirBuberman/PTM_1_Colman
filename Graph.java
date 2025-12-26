package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import test.TopicManagerSingleton.TopicManager;

public class Graph extends ArrayList<Node>{
    
    public boolean hasCycles() {
        for (Node node : this) {
            if (node.hasCycles()) {
                return true;
            }
        }
        return false;
    }
    
    public void createFromTopics(){
        this.clear();
        Map<String, Node> nodeMap = new HashMap<>();
        TopicManager tm = TopicManagerSingleton.get();
        
        for (Topic topic : tm.getTopics()) {
            String topicNodeName = "T" + topic.name;
            Node topicNode = nodeMap.get(topicNodeName);
            if (topicNode == null) {
                topicNode = new Node(topicNodeName);
                nodeMap.put(topicNodeName, topicNode);
                this.add(topicNode);
            }
            
            for (Agent agent : topic.getSubscribers()) {
                String agentNodeName = "A" + agent.getName();
                Node agentNode = nodeMap.get(agentNodeName);
                if (agentNode == null) {
                    agentNode = new Node(agentNodeName);
                    nodeMap.put(agentNodeName, agentNode);
                    this.add(agentNode);
                }
                topicNode.addEdge(agentNode);
            }
        }
        
        for (Topic topic : tm.getTopics()) {
            String topicNodeName = "T" + topic.name;
            Node topicNode = nodeMap.get(topicNodeName);
            
            for (Agent agent : topic.getPublishers()) {
                String agentNodeName = "A" + agent.getName();
                Node agentNode = nodeMap.get(agentNodeName);
                if (agentNode != null) {
                    agentNode.addEdge(topicNode);
                }
            }
        }
    }    
}
