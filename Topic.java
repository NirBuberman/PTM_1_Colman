package test;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    public final String name;

    private final List<Agent> subscribers = new ArrayList<>();
    private final List<Agent> publishers = new ArrayList<>();

    Topic(String name){
        this.name=name;
    }

    public void subscribe(Agent a){
        if (a != null && !subscribers.contains(a)) {
            subscribers.add(a);
        }
    }
    public void unsubscribe(Agent a){
        subscribers.remove(a);
    }

    public void publish(Message m){
        List<Agent> current = new ArrayList<>(subscribers);
        for (Agent agent : current) {
            agent.callback(name, m);
        }
    }

    public void addPublisher(Agent a){
        if (a != null && !publishers.contains(a)) {
            publishers.add(a);
        }
    }

    public void removePublisher(Agent a){
        publishers.remove(a);
    }


}
