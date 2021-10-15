package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Topic;
import edu.kpdteti.backend.entity.TopicParent;
import edu.kpdteti.backend.entity.dto.TopicParentDto;
import edu.kpdteti.backend.enums.IdGeneratorEnum;
import edu.kpdteti.backend.model.request.admin.PostAuthorRequest;
import edu.kpdteti.backend.model.request.admin.PostTopicParentRequest;
import edu.kpdteti.backend.model.request.admin.PostTopicRequest;
import edu.kpdteti.backend.model.response.admin.*;
import edu.kpdteti.backend.repository.AuthorRepository;
import edu.kpdteti.backend.repository.TopicParentRepository;
import edu.kpdteti.backend.repository.TopicRepository;
import edu.kpdteti.backend.service.AdminService;
import edu.kpdteti.backend.util.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {

    private final AuthorRepository authorRepository;
    private final TopicParentRepository topicParentRepository;
    private final TopicRepository topicRepository;
    private final IdGenerator idGenerator;

    @Autowired
    public AdminServiceImpl(AuthorRepository authorRepository, TopicParentRepository topicParentRepository, TopicRepository topicRepository, IdGenerator idGenerator) {
        this.authorRepository = authorRepository;
        this.topicParentRepository = topicParentRepository;
        this.topicRepository = topicRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public DeleteAuthorResponse deleteAuthor(String authorId) {
        if(authorRepository.existsById(authorId)) {
            authorRepository.deleteById(authorId);
            return DeleteAuthorResponse.builder()
                    .message("Success")
                    .build();
        } else {
            throw new EntityNotFoundException("Author not found with id " + authorId);
        }
    }

    @Override
    public DeleteTopicParentResponse deleteTopicParent(String topicParentId) {
        if(topicParentRepository.existsById(topicParentId)) {
            topicParentRepository.deleteById(topicParentId);
            return DeleteTopicParentResponse.builder()
                    .message("Success")
                    .build();
        } else {
            throw new EntityNotFoundException("Topic Parent not found with id " + topicParentId);
        }
    }

    @Override
    public DeleteTopicResponse deleteTopic(String topicId) {
        if(topicRepository.existsById(topicId)) {
            topicRepository.deleteById(topicId);
            return DeleteTopicResponse.builder()
                    .message("Success")
                    .build();
        } else {
            throw new EntityNotFoundException("Topic not found with id " + topicId);
        }
    }

    @Override
    public PostAuthorResponse postAuthor(PostAuthorRequest request) {
        Author author = Author.builder()
                .authorId(idGenerator.generateId(IdGeneratorEnum.AUTHOR))
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
                .topicParentId(idGenerator.generateId(IdGeneratorEnum.TOPIC_PARENT))
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
        if(topicParent == null) {
            throw new EntityNotFoundException("Topic Parent not found with id " + request.getTopicParentId());
        }
        TopicParentDto topicParentDto = new TopicParentDto();
        BeanUtils.copyProperties(topicParent, topicParentDto);
        Topic topic = Topic.builder()
                .topicId(idGenerator.generateId(IdGeneratorEnum.TOPIC))
                .topicParentDto(topicParentDto)
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
