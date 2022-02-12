package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends MongoRepository<Publication, String> {

    Publication findByPublicationId(String publicationId);

    List<Publication> findAllByAuthorDto_AuthorId(String authorId, Sort sort);

    Page<Publication> findAllByAuthorDto_AuthorId(String authorId, PageRequest page);

    List<Publication> findAllByTopicDto_TopicId(String topicId, Sort sort);

    Page<Publication> findAllByTopicDto_TopicId(String topicId, PageRequest page);

    List<Publication> findAllByPublicationTitleContainingIgnoreCaseOrAuthorDto_AuthorNameContainingIgnoreCase(String publicationTitle, String authorName, Sort sort);

    Page<Publication> findAllByPublicationTitleContainingIgnoreCaseOrAuthorDto_AuthorNameContainingIgnoreCase(String publicationTitle, String authorName, PageRequest page);
}
