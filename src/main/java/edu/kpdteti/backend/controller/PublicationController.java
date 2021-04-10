package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.response.publication.DeletePublicationResponse;
import edu.kpdteti.backend.model.response.publication.DownloadPublicationResponse;
import edu.kpdteti.backend.service.PublicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicationController {

    private PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @DeleteMapping(ApiPath.PUBLICATION)
    public ResponseEntity<DeletePublicationResponse> deletePublication(@RequestParam Long publicationId) {
        return new ResponseEntity<>(publicationService.deletePublication(publicationId), HttpStatus.OK);
    }

    @GetMapping(ApiPath.PUBLICATION_DOWNLOAD)
    public ResponseEntity<DownloadPublicationResponse> downloadPublication(@RequestParam Long publicationId) {
        return new ResponseEntity<>(publicationService.downloadPublication(publicationId), HttpStatus.OK);
    }

//    @GetMapping(ApiPath.TOPIC_PUBLICATIONS)
//    public ResponseEntity<GetPublicationByAuthorResponse> getPublicationByAuthor(@RequestParam Long authorId) {
//        return new ResponseEntity<>(publicationService.getPublicationByAuthor(authorId), HttpStatus.OK);
//    }

}
