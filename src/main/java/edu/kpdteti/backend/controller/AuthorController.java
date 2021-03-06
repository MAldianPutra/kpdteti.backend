package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.response.author.GetAllAuthorsNameResponse;
import edu.kpdteti.backend.model.response.author.GetAllAuthorsResponse;
import edu.kpdteti.backend.model.response.author.GetAuthorResponse;
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

import java.util.List;

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

    @GetMapping(ApiPath.ALL_AUTHOR)
    public ResponseEntity<List<GetAllAuthorsResponse>> getAllAuthors(@RequestParam(required = false) Integer page) {
        if(page != null) {
            return new ResponseEntity<>(authorService.getAllAuthors(page, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(authorService.getAllAuthors(page, false), HttpStatus.OK);
        }
    }

    @GetMapping(ApiPath.ALL_AUTHOR_NAME)
    public ResponseEntity<List<GetAllAuthorsNameResponse>> getAllAuthorsName() {
        return new ResponseEntity<>(authorService.getAllAuthorsName(), HttpStatus.OK);
    }

}
