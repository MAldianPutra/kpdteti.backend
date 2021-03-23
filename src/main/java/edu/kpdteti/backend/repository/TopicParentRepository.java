package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.TopicParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicParentRepository extends JpaRepository<TopicParent, Long> {

    TopicParent findByTopicParentId(Long topicParentId);

}
