package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.TopicParent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicParentRepository extends MongoRepository<TopicParent, String> {

    TopicParent findByTopicParentId(String topicParentId);

}
