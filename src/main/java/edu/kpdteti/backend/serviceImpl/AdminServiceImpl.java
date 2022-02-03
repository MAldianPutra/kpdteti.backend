package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.*;
import edu.kpdteti.backend.enums.IdGeneratorEnum;
import edu.kpdteti.backend.enums.UserRoleEnum;
import edu.kpdteti.backend.model.request.admin.PostAuthorRequest;
import edu.kpdteti.backend.model.request.admin.PostTopicRequest;
import edu.kpdteti.backend.model.response.admin.*;
import edu.kpdteti.backend.repository.*;
import edu.kpdteti.backend.service.AdminService;
import edu.kpdteti.backend.util.IdGeneratorUtil;
import edu.kpdteti.backend.util.populate.PopulateAuthorUtil;
import edu.kpdteti.backend.util.populate.PopulatePublicationUtil;
import edu.kpdteti.backend.util.populate.PopulateTopicUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final PublicationRepository publicationRepository;
    private final ClassificationRepository classificationRepository;
    private final IdGeneratorUtil idGeneratorUtil;
    private final PopulatePublicationUtil populatePublicationUtil;
    private final PopulateAuthorUtil populateAuthorUtil;
    private final PopulateTopicUtil populateTopicUtil;

    @Autowired
    public AdminServiceImpl(AuthorRepository authorRepository,
                            UserRepository userRepository,
                            TopicRepository topicRepository,
                            PublicationRepository publicationRepository,
                            ClassificationRepository classificationRepository,
                            IdGeneratorUtil idGeneratorUtil,
                            PopulatePublicationUtil populatePublicationUtil,
                            PopulateAuthorUtil populateAuthorUtil,
                            PopulateTopicUtil populateTopicUtil) {
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.publicationRepository = publicationRepository;
        this.classificationRepository = classificationRepository;
        this.idGeneratorUtil = idGeneratorUtil;
        this.populatePublicationUtil = populatePublicationUtil;
        this.populateAuthorUtil = populateAuthorUtil;
        this.populateTopicUtil = populateTopicUtil;
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
                .authorId(idGeneratorUtil.generateId(IdGeneratorEnum.AUTHOR))
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
    public PostTopicResponse postTopic(PostTopicRequest request) {
        Topic topic = Topic.builder()
                .topicId(idGeneratorUtil.generateId(IdGeneratorEnum.TOPIC))
                .topicCreatedAt(LocalDateTime.now())
                .topicLastUpdated(LocalDateTime.now())
                .build();
        if(request.getOptTopicParentId() != null) {
            Topic topicParent = topicRepository.findByTopicParentId(request.getOptTopicParentId());
            if(topicParent == null) {
                throw new EntityNotFoundException("Topic Parent not found with id " + request.getOptTopicParentId());
            }
            topic.setTopicId(request.getOptTopicParentId());
        }
        BeanUtils.copyProperties(request, topic);
        Topic savedTopic = topicRepository.save(topic);
        PostTopicResponse response = new PostTopicResponse();
        BeanUtils.copyProperties(savedTopic, response);
        return response;
    }

    @Override
    public List<PopulateAuthorResponse> populateAuthor() {
        List<Author> authors = populateAuthorUtil.populateAuthor();
        List<PopulateAuthorResponse> responses = new ArrayList<>();
        authors.forEach(author -> {
            authorRepository.save(author);
            PopulateAuthorResponse response = new PopulateAuthorResponse();
            BeanUtils.copyProperties(author, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public List<PopulateTopicResponse> populateTopic() {
        List<Topic> topics = populateTopicUtil.populateTopic();
        List<PopulateTopicResponse> responses = new ArrayList<>();
        topics.forEach(topic -> {
            topicRepository.save(topic);
            PopulateTopicResponse response = new PopulateTopicResponse();
            BeanUtils.copyProperties(topic, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public PopulatePublicationResponse populatePublication() {
        List<Author> authors = authorRepository.findAll();
        List<User> users = userRepository.findAllByUserRoleEnum(UserRoleEnum.ROLE_ADMIN);
        List<Pair<Publication, Classification>> requests = populatePublicationUtil.populatePublication(authors, users);
        requests.forEach(request -> {
            publicationRepository.save(request.getFirst());
            classificationRepository.save(request.getSecond());
        });
        return PopulatePublicationResponse.builder()
                .message("Success")
                .build();
    }
}
