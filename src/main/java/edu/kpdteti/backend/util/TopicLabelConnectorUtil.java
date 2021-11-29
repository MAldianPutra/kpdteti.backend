package edu.kpdteti.backend.util;

import edu.kpdteti.backend.entity.Topic;
import edu.kpdteti.backend.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicLabelConnectorUtil {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicLabelConnectorUtil(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    // Not yet completed
    public Topic getTopicByLabel(Integer label) {
        return new Topic();
    }

}
