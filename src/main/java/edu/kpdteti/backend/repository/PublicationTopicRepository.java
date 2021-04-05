package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.PublicationTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationTopicRepository extends JpaRepository<PublicationTopic, Long> {
}
