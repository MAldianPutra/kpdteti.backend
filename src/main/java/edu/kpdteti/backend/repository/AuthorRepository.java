package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByAuthorId(String authorId);


}
