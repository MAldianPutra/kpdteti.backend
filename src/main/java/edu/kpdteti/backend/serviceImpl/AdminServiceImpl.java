package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Topic;
import edu.kpdteti.backend.entity.TopicParent;
import edu.kpdteti.backend.model.request.admin.*;
import edu.kpdteti.backend.model.response.admin.*;
import edu.kpdteti.backend.repository.AuthorRepository;
import edu.kpdteti.backend.repository.TopicParentRepository;
import edu.kpdteti.backend.repository.TopicRepository;
import edu.kpdteti.backend.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {

    private final AuthorRepository authorRepository;
    private final TopicParentRepository topicParentRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public AdminServiceImpl(AuthorRepository authorRepository, TopicParentRepository topicParentRepository, TopicRepository topicRepository) {
        this.authorRepository = authorRepository;
        this.topicParentRepository = topicParentRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public DeleteAuthorResponse deleteAuthor(Long authorId) {
        try {
            authorRepository.deleteById(authorId);
            return DeleteAuthorResponse.builder()
                    .message("Success")
                    .build();
        } catch (IllegalArgumentException ex) {
            return DeleteAuthorResponse.builder()
                    .message("Failed, authorId not found.")
                    .build();
        }
    }

    @Override
    public DeleteTopicParentResponse deleteTopicParent(Long topicParentId) {
        try {
            topicParentRepository.deleteById(topicParentId);
            return DeleteTopicParentResponse.builder()
                    .message("Success")
                    .build();
        } catch (IllegalArgumentException ex) {
            return DeleteTopicParentResponse.builder()
                    .message("Failed, topicParentId not found.")
                    .build();
        }
    }

    @Override
    public DeleteTopicResponse deleteTopic(Long topicId) {
        try {
            topicRepository.deleteById(topicId);
            return DeleteTopicResponse.builder()
                    .message("Success")
                    .build();
        } catch (IllegalArgumentException ex) {
            return DeleteTopicResponse.builder()
                    .message("Failed, topicId not found.")
                    .build();
        }
    }

    @Override
    public PostAuthorResponse postAuthor(PostAuthorRequest request) {
        Author author = Author.builder()
                .authorCreatedAt(LocalDateTime.now())
                .authorLastUpdated(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(request, author);
        Author savedAuthor = authorRepository.save(author);
        PostAuthorResponse response = new PostAuthorResponse();
        BeanUtils.copyProperties(savedAuthor, response);
        return response;
    }

    @Override
    public PostTopicParentResponse postTopicParent(PostTopicParentRequest request) {
        TopicParent topicParent = TopicParent.builder()
                .topicParentCreatedAt(LocalDateTime.now())
                .topicParentLastUpdated(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(request, topicParent);
        TopicParent savedTopicParent = topicParentRepository.save(topicParent);
        PostTopicParentResponse response = new PostTopicParentResponse();
        BeanUtils.copyProperties(savedTopicParent, response);
        return response;
    }

    @Override
    public PostTopicResponse postTopic(PostTopicRequest request) {
        TopicParent topicParent = topicParentRepository.findByTopicParentId(request.getTopicParentId());
        Topic topic = Topic.builder()
                .topicParent(topicParent)
                .topicCreatedAt(LocalDateTime.now())
                .topicLastUpdated(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(request, topic);
        Topic savedTopic = topicRepository.save(topic);
        PostTopicResponse response = new PostTopicResponse();
        BeanUtils.copyProperties(savedTopic, response);
        return response;
    }
}
