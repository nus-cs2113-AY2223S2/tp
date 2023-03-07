package seedu.dukeofbooks.data.book;

import seedu.dukeofbooks.common.IVerifiable;

public class Topic implements IVerifiable {
    private String topic;
    public Topic(String topic) {
        setTopic(topic);;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } 
        else {
            return other instanceof Topic && this.hasSameData((Topic) other);
        }
    }

    public boolean equals(String otherTopic) {
        return topic.equals(otherTopic);
    }

    private boolean hasSameData(Topic other) {
        return topic == other.getTopic();
    }

    @Override
    public int hashCode() {
        return topic.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Topic: %S", topic);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
}
