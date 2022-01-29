package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.response.author.GetAllAuthorsNameResponse;
import edu.kpdteti.backend.model.response.author.GetAllAuthorsResponse;
import edu.kpdteti.backend.model.response.author.GetAuthorResponse;

import java.util.List;

public interface AuthorService {

    GetAuthorResponse getAuthor(String authorId);

    List<GetAllAuthorsResponse> getAllAuthors(Integer page);

    List<GetAllAuthorsNameResponse> getAllAuthorsName();

}
