package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {

    Topic findByTopicId(String topicId);

    List<Topic> findAllByTopicParentDto_TopicParentId(String topicParentId);

}
