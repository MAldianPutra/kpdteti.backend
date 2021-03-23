package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findByTopicId(Long topicId);

    List<Topic> findAllByTopicParent_TopicParentId(Long topicParentId);

}
