package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.response.author.GetAllAuthorsNameResponse;
import edu.kpdteti.backend.model.response.author.GetAllAuthorsResponse;
import edu.kpdteti.backend.model.response.author.GetAuthorResponse;
import org.apache.xpath.operations.Bool;

import java.util.List;

public interface AuthorService {

    GetAuthorResponse getAuthor(String authorId);

    List<GetAllAuthorsResponse> getAllAuthors(Integer page, Boolean usePage);

    List<GetAllAuthorsNameResponse> getAllAuthorsName();

}
