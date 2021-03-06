package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.request.admin.PostAuthorRequest;
import edu.kpdteti.backend.model.request.admin.PostTopicRequest;
import edu.kpdteti.backend.model.response.admin.*;
import edu.kpdteti.backend.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping(ApiPath.ADMIN_AUTHOR)
    public ResponseEntity<DeleteAuthorResponse> deleteAuthor(@RequestParam String authorId) {
        return new ResponseEntity<>(adminService.deleteAuthor(authorId), HttpStatus.OK);
    }

    @DeleteMapping(ApiPath.ADMIN_TOPIC)
    public ResponseEntity<DeleteTopicResponse> deleteTopic(@RequestParam String topicId) {
        return new ResponseEntity<>(adminService.deleteTopic(topicId), HttpStatus.OK);
    }

    @PostMapping(ApiPath.ADMIN_AUTHOR)
    public ResponseEntity<PostAuthorResponse> postAuthor(@Valid @RequestBody PostAuthorRequest request) {
        return new ResponseEntity<>(adminService.postAuthor(request), HttpStatus.CREATED);
    }

    @PostMapping(ApiPath.ADMIN_TOPIC)
    public ResponseEntity<PostTopicResponse> postTopic(@Valid @RequestBody PostTopicRequest request) {
        return new ResponseEntity<>(adminService.postTopic(request), HttpStatus.CREATED);
    }

    @PostMapping(ApiPath.ADMIN_AUTHOR_POPULATE)
    public ResponseEntity<List<PopulateAuthorResponse>> populateAuthor() {
        return new ResponseEntity<>(adminService.populateAuthor(), HttpStatus.CREATED);
    }

    @PostMapping(ApiPath.ADMIN_TOPIC_POPULATE)
    public ResponseEntity<List<PopulateTopicResponse>> populateTopic() {
        return new ResponseEntity<>(adminService.populateTopic(), HttpStatus.CREATED);
    }

    @PostMapping(ApiPath.ADMIN_PUBLICATION_POPULATE)
    public ResponseEntity<PopulatePublicationResponse> populatePublication() {
        return new ResponseEntity<>(adminService.populatePublication(), HttpStatus.CREATED);
    }
}
