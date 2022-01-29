package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.model.response.author.GetAuthorResponse;
import edu.kpdteti.backend.repository.AuthorRepository;
import edu.kpdteti.backend.service.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public GetAuthorResponse getAuthor(String authorId) {
        Author author = authorRepository.findByAuthorId(authorId);
        if(author == null) {
            throw new EntityNotFoundException("Author not found with id " + authorId);
        }
        GetAuthorResponse response = new GetAuthorResponse();
        BeanUtils.copyProperties(author, response);
        return response;
    }

    @Override
    public List<GetAuthorResponse> getAllAuthors(Integer page) {
        Page<Author> authors = authorRepository.findAll(PageRequest.of(page, 10, Sort.by("productName").ascending()));
        if(authors.isEmpty()) {
            throw new EntityNotFoundException("No Author in database");
        }
        List<GetAuthorResponse> responses = new ArrayList<>();
        authors.forEach(author -> {
            GetAuthorResponse response = new GetAuthorResponse();
            BeanUtils.copyProperties(author, response);
            responses.add(response);
        });
        return responses;
    }
}
