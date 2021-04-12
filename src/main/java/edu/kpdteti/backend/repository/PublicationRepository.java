package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Publication;
import edu.kpdteti.backend.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    Publication findByPublicationId(Long publicationId);

    List<Publication> findAllByAuthors(Author author);

    List<Publication> findAllByTopics(Topic topic);


}
