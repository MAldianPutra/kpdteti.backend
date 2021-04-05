package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Publication;
import edu.kpdteti.backend.entity.PublicationAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    Publication findByPublicationId(Long publicationId);

    Set<Publication> findAllByPublicationAuthors(Set<PublicationAuthor> publicationAuthors);

}
