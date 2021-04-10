package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    Publication findByPublicationId(Long publicationId);

}
