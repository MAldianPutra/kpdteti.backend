package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.enums.SearchTypeEnum;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.SearchPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;
import edu.kpdteti.backend.service.PublicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
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

    @GetMapping(ApiPath.SEARCH_PUBLICATION)
    public ResponseEntity<List<SearchPublicationResponse>> searchPublication(@RequestParam String searchKey,
                                                                             @RequestParam SearchTypeEnum searchType) {
        return new ResponseEntity<>(publicationService.searchPublication(searchKey, searchType), HttpStatus.OK);
    }

    @GetMapping(ApiPath.ALL_PUBLICATION)
    public ResponseEntity<List<GetAllPublicationResponse>> getAllPublications() {
        return new ResponseEntity<>(publicationService.getAllPublications(), HttpStatus.OK);
    }

    @PostMapping(ApiPath.POST_PUBLICATION)
    public ResponseEntity<PostPublicationResponse> postPublication(@Valid @RequestBody PostPublicationRequest request) throws SAXException, JAXBException, IOException, URISyntaxException {
        return new ResponseEntity<>(publicationService.postPublication(request), HttpStatus.OK);
    }

    @PostMapping(value = ApiPath.PUBLICATION_UPLOAD, consumes = "multipart/form-data")
    public ResponseEntity<UploadPublicationResponse> uploadPublication(@RequestParam ("id") String publicationId,
                                                                       @RequestParam ("file") MultipartFile file) throws IOException, URISyntaxException {
        return new ResponseEntity<>(publicationService.uploadPublication(publicationId, file), HttpStatus.OK);
    }

    @PutMapping(ApiPath.PUBLICATION)
    public ResponseEntity<UpdatePublicationResponse> updatePublication(@Valid @RequestBody UpdatePublicationRequest request) {
        return new ResponseEntity<>(publicationService.updatePublication(request), HttpStatus.OK);
    }

}
