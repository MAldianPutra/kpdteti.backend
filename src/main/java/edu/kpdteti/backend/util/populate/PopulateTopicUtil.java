package edu.kpdteti.backend.util.populate;

import edu.kpdteti.backend.entity.Topic;
import edu.kpdteti.backend.enums.IdGeneratorEnum;
import edu.kpdteti.backend.util.IdGeneratorUtil;
import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopulateTopicUtil {

    private final IdGeneratorUtil idGeneratorUtil;

    @Autowired
    public PopulateTopicUtil(IdGeneratorUtil idGeneratorUtil) {
        this.idGeneratorUtil = idGeneratorUtil;
    }

    public List<Topic> populateTopic() {
        List<Pair<Integer, String>> topicPair = new ArrayList<>();
        topicPair.add(new Pair<>(0, "Computer System Organization"));
        topicPair.add(new Pair<>(1, "Networks"));
        topicPair.add(new Pair<>(2, "Software and its Engineering "));
        topicPair.add(new Pair<>(3, "Theory of Computation "));
        topicPair.add(new Pair<>(4, "Mathematics of Computing"));
        topicPair.add(new Pair<>(5, "Information System"));
        topicPair.add(new Pair<>(6, "Security and Privacy"));
        topicPair.add(new Pair<>(7, "Human-centered Computing"));
        topicPair.add(new Pair<>(8, "Computing Methodologies"));
        topicPair.add(new Pair<>(9, "Applied Computing"));

        List<Topic> topics = new ArrayList<>();
        topicPair.forEach(item -> {
            Topic topic = Topic.builder()
                    .topicId(idGeneratorUtil.generateId(IdGeneratorEnum.TOPIC))
                    .topicName(item.getValue())
                    .topicLabel(item.getKey())
                    .topicCreatedAt(LocalDateTime.now())
                    .topicLastUpdated(LocalDateTime.now())
                    .build();
            topics.add(topic);
        });
        return topics;
    }

}
