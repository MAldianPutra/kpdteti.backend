package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends MongoRepository<Publication, String> {

    Publication findByPublicationId(String publicationId);

    Page<Publication> findAllByAuthorDto_AuthorId(String authorId, PageRequest page);

    Page<Publication> findAllByTopicDto_TopicId(String topicId, PageRequest page);

    Page<Publication> findAllByPublicationTitleContaining(String publicationTitle, PageRequest page);

    Page<Publication> findAllByTopicDto_TopicNameContaining(String topicName, PageRequest page);

    Page<Publication> findAllByAuthorDto_AuthorNameContaining(String authorName, PageRequest page);

}
