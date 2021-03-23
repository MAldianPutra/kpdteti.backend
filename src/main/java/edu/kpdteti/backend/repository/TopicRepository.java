package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findByTopicId(Long topicId);

}
