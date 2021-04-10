package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.model.response.author.GetAuthorResponse;
import edu.kpdteti.backend.repository.AuthorRepository;
import edu.kpdteti.backend.service.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public GetAuthorResponse getAuthor(Long authorId) {
        Author author = authorRepository.findByAuthorId(authorId);
        GetAuthorResponse response = new GetAuthorResponse();
        BeanUtils.copyProperties(author, response);
        return response;
    }
}
