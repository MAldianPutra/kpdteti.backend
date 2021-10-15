package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.response.author.GetAuthorResponse;

import java.util.List;

public interface AuthorService {

    GetAuthorResponse getAuthor(String authorId);

    List<GetAuthorResponse> getAllAuthors();

}
