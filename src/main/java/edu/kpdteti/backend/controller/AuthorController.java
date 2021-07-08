package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.response.author.GetAuthorResponse;
import edu.kpdteti.backend.model.response.publication.DeletePublicationResponse;
import edu.kpdteti.backend.service.AuthorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(ApiPath.AUTHOR)
    public ResponseEntity<GetAuthorResponse> getAuthor(@RequestParam String authorId) {
        return new ResponseEntity<>(authorService.getAuthor(authorId), HttpStatus.OK);
    }

}
