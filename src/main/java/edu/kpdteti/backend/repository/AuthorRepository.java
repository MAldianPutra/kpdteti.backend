package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    Author findByAuthorId(Long authorId);


}
