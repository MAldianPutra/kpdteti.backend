package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Publication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends MongoRepository<Publication, String> {

    Publication findByPublicationId(String publicationId);

    List<Publication> findAllByAuthorDto_AuthorId(String authorId);

    List<Publication> findAllByTopicDto_TopicId(String topicId);


}
