package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.response.topicParent.GetAllTopicParentsResponse;
import edu.kpdteti.backend.service.TopicParentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicParentController {

    private final TopicParentService topicParentService;

    @Autowired
    public TopicParentController(TopicParentService topicParentService) {
        this.topicParentService = topicParentService;
    }

    @GetMapping(ApiPath.TOPIC_PARENT)
    public ResponseEntity<List<GetAllTopicParentsResponse>> getAllTopicParent(){
        return new ResponseEntity<>(topicParentService.getAllTopicParents(), HttpStatus.OK);
    }
}
