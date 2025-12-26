package test;

import java.util.function.BinaryOperator;

import test.TopicManagerSingleton.TopicManager;

public class BinOpAgent implements Agent {
    private String name;
    private String input1Topic;
    private String input2Topic;
    private String outputTopic;
    private BinaryOperator<Double> op;
    private Message input1;
    private Message input2;
    private TopicManager tm;

    public BinOpAgent(String name, String input1Topic, String input2Topic, 
                      String outputTopic, BinaryOperator<Double> op) {
        this.name = name;
        this.input1Topic = input1Topic;
        this.input2Topic = input2Topic;
        this.outputTopic = outputTopic;
        this.op = op;
        this.tm = TopicManagerSingleton.get();
        this.tm.getTopic(input1Topic).subscribe(this);
        this.tm.getTopic(input2Topic).subscribe(this);
        this.tm.getTopic(outputTopic).addPublisher(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset() {
        this.input1 = new Message(0.0);
        this.input2 = new Message(0.0);
    }

    @Override
    public void callback(String topic, Message msg) {
        if (topic.equals(input1Topic)) {
            input1 = msg;
        } else if (topic.equals(input2Topic)) {
            input2 = msg;
        }
        
        if (input1 != null && input2 != null && 
            !Double.isNaN(input1.asDouble) && !Double.isNaN(input2.asDouble)) {
            double result = op.apply(input1.asDouble, input2.asDouble);
            tm.getTopic(outputTopic).publish(new Message(result));
        }
    }

    @Override
    public void close() {
    }
}
