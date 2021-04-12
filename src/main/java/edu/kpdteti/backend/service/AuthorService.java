package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.response.author.GetAuthorResponse;

public interface AuthorService {

    GetAuthorResponse getAuthor(String authorId);

}
