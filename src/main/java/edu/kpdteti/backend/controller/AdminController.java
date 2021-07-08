package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.request.admin.PostAuthorRequest;
import edu.kpdteti.backend.model.request.admin.PostTopicParentRequest;
import edu.kpdteti.backend.model.request.admin.PostTopicRequest;
import edu.kpdteti.backend.model.response.admin.*;
import edu.kpdteti.backend.model.response.publication.DeletePublicationResponse;
import edu.kpdteti.backend.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(ApiPath.ADMIN_TOPIC_PARENT)
    public ResponseEntity<DeleteTopicParentResponse> deleteTopicParent(@RequestParam String topicParentId) {
        return new ResponseEntity<>(adminService.deleteTopicParent(topicParentId), HttpStatus.OK);
    }

    @DeleteMapping(ApiPath.ADMIN_TOPIC)
    public ResponseEntity<DeleteTopicResponse> deleteTopic(@RequestParam String topicId) {
        return new ResponseEntity<>(adminService.deleteTopic(topicId), HttpStatus.OK);
    }

    @PostMapping(ApiPath.ADMIN_AUTHOR)
    public ResponseEntity<PostAuthorResponse> postAuthor(@RequestBody PostAuthorRequest request) {
        return new ResponseEntity<>(adminService.postAuthor(request), HttpStatus.OK);
    }

    @PostMapping(ApiPath.ADMIN_TOPIC_PARENT)
    public ResponseEntity<PostTopicParentResponse> postTopicParent(@RequestBody PostTopicParentRequest request) {
        return new ResponseEntity<>(adminService.postTopicParent(request), HttpStatus.OK);
    }

    @PostMapping(ApiPath.ADMIN_TOPIC)
    public ResponseEntity<PostTopicResponse> postTopic(@RequestBody PostTopicRequest request) {
        return new ResponseEntity<>(adminService.postTopic(request), HttpStatus.OK);
    }
}
