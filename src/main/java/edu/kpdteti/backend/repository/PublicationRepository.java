package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Publication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends MongoRepository<Publication, String> {

    Publication findByPublicationId(String publicationId);

    List<Publication> findAllByAuthorDto_AuthorId(String authorId, PageRequest page);

    List<Publication> findAllByTopicDto_TopicId(String topicId, PageRequest page);

    List<Publication> findAllByPublicationTitleContaining(String publicationTitle, PageRequest page);

    List<Publication> findAllByTopicDto_TopicNameContaining(String topicName, PageRequest page);

    List<Publication> findAllByAuthorDto_AuthorNameContaining(String authorName, PageRequest page);

}
