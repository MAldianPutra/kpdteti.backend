package edu.kpdteti.backend.enums;

public enum TopicLabelEnum {

    LABEL_0("Computer System Organization"),
    LABEL_1("Networks"),
    LABEL_2("Software and its Engineering"),
    LABEL_3("Theory of Computation"),
    LABEL_4("Mathematics of Computing"),
    LABEL_5("Information System"),
    LABEL_6("Security and Privacy"),
    LABEL_7("Human-Centered Computing"),
    LABEL_8("Computing Methodologies"),
    LABEL_9("Applied Computing");

    private String topicLabelEnum;

    TopicLabelEnum(String topicLabelEnum) {this.topicLabelEnum = topicLabelEnum;}

    public String getTopicLabelEnum() {return topicLabelEnum;}

}