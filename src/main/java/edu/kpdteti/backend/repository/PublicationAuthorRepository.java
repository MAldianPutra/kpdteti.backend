package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.PublicationAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PublicationAuthorRepository extends JpaRepository<PublicationAuthor, Long> {

    Set<PublicationAuthor> findAllByAuthor_AuthorId(Long authorId);

}
