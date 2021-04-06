package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.response.topic.GetTopicByTopicParentResponse;
import edu.kpdteti.backend.model.response.topic.GetTopicResponse;
import edu.kpdteti.backend.service.TopicService;
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
public class TopicController {

    private TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping(ApiPath.TOPIC_BY_TOPIC_PARENT)
    public ResponseEntity<GetTopicByTopicParentResponse> getTopicByTopicParent(@RequestParam Long topicParentId){
        return new ResponseEntity<>(topicService.getTopicByTopicParent(topicParentId), HttpStatus.OK);
    }

    @GetMapping(ApiPath.TOPIC)
    private ResponseEntity<GetTopicResponse> getTopic(@RequestParam Long topicId) {
        return new ResponseEntity<>(topicService.getTopic(topicId), HttpStatus.OK);
    }

}
