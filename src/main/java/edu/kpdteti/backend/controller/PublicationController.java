package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;
import edu.kpdteti.backend.service.PublicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicationController {

    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @DeleteMapping(ApiPath.PUBLICATION)
    public ResponseEntity<DeletePublicationResponse> deletePublication(@RequestParam String publicationId) throws ClassNotFoundException {
        return new ResponseEntity<>(publicationService.deletePublication(publicationId), HttpStatus.OK);
    }

    @GetMapping(ApiPath.PUBLICATION_DOWNLOAD)
    public ResponseEntity<DownloadPublicationResponse> downloadPublication(@RequestParam String publicationId) {
        return new ResponseEntity<>(publicationService.downloadPublication(publicationId), HttpStatus.OK);
    }

    @GetMapping(ApiPath.AUTHOR_PUBLICATIONS)
    public ResponseEntity<List<GetPublicationsByAuthorResponse>> getPublicationByAuthor(@RequestParam String authorId) {
        return new ResponseEntity<>(publicationService.getPublicationsByAuthor(authorId), HttpStatus.OK);
    }

    @GetMapping(ApiPath.TOPIC_PUBLICATIONS)
    public ResponseEntity<List<GetPublicationsByTopicResponse>> getPublicationsByTopic(@RequestParam String topicId) {
        return new ResponseEntity<>(publicationService.getPublicationsByTopic(topicId), HttpStatus.OK);
    }

    @GetMapping(ApiPath.PUBLICATION)
    public ResponseEntity<GetPublicationResponse> getPublication(@RequestParam String publicationId) {
        return new ResponseEntity<>(publicationService.getPublication(publicationId), HttpStatus.OK);
    }

    @PostMapping(ApiPath.POST_PUBLICATION)
    public ResponseEntity<PostPublicationResponse> postPublication(@RequestBody PostPublicationRequest request) {
        return new ResponseEntity<>(publicationService.postPublication(request), HttpStatus.OK);
    }

    @PutMapping(ApiPath.PUBLICATION)
    public ResponseEntity<UpdatePublicationResponse> updatePublication(@RequestBody UpdatePublicationRequest request) {
        return new ResponseEntity<>(publicationService.updatePublication(request), HttpStatus.OK);
    }

}
