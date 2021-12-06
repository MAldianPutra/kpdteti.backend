package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Classification;
import edu.kpdteti.backend.entity.Publication;
import edu.kpdteti.backend.entity.Topic;
import edu.kpdteti.backend.entity.dto.AuthorDto;
import edu.kpdteti.backend.entity.dto.ClassificationDto;
import edu.kpdteti.backend.entity.dto.ClassificationReportDto;
import edu.kpdteti.backend.entity.dto.TopicDto;
import edu.kpdteti.backend.enums.IdGeneratorEnum;
import edu.kpdteti.backend.enums.SearchTypeEnum;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.SearchPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;
import edu.kpdteti.backend.repository.AuthorRepository;
import edu.kpdteti.backend.repository.ClassificationRepository;
import edu.kpdteti.backend.repository.PublicationRepository;
import edu.kpdteti.backend.repository.TopicRepository;
import edu.kpdteti.backend.service.PublicationService;
import edu.kpdteti.backend.util.FileUploadUtil;
import edu.kpdteti.backend.util.IdGeneratorUtil;
import edu.kpdteti.backend.util.MLModelUtil;
import edu.kpdteti.backend.util.TextPreprocessingUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final AuthorRepository authorRepository;
    private final TopicRepository topicRepository;
    private final ClassificationRepository classificationRepository;
    private final FileUploadUtil fileUploadUtil;
    private final IdGeneratorUtil idGeneratorUtil;
    private final MLModelUtil mlModelUtil;
    private final TextPreprocessingUtil textPreprocessingUtil;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository, AuthorRepository authorRepository,
                                  TopicRepository topicRepository, ClassificationRepository classificationRepository,
                                  FileUploadUtil fileUploadUtil, IdGeneratorUtil idGeneratorUtil, MLModelUtil mlModelUtil,
                                  TextPreprocessingUtil textPreprocessingUtil) {
        this.publicationRepository = publicationRepository;
        this.authorRepository = authorRepository;
        this.topicRepository = topicRepository;
        this.classificationRepository = classificationRepository;
        this.fileUploadUtil = fileUploadUtil;
        this.idGeneratorUtil = idGeneratorUtil;
        this.mlModelUtil = mlModelUtil;
        this.textPreprocessingUtil = textPreprocessingUtil;
    }

    @Override
    public DeletePublicationResponse deletePublication(String publicationId) {
        if (publicationRepository.existsById(publicationId)) {
            publicationRepository.deleteById(publicationId);
            return DeletePublicationResponse.builder()
                    .message("Success")
                    .build();
        } else {
            throw new EntityNotFoundException("Publication not found with id " + publicationId);
        }
    }

    @Override
    public DownloadPublicationResponse downloadPublication(String publicationId) {
        Publication publication = publicationRepository.findByPublicationId(publicationId);
        if (publication == null) {
            throw new EntityNotFoundException("Publication not found with id " + publicationId);
        }
        DownloadPublicationResponse response = new DownloadPublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }

    @Override
    public List<GetPublicationsByAuthorResponse> getPublicationsByAuthor(String authorId) {
        List<Publication> publications = publicationRepository.findAllByAuthorDto_AuthorId(authorId);
        if (publications.isEmpty()) {
            throw new EntityNotFoundException("Publication not found with authorId " + authorId);
        }
        List<GetPublicationsByAuthorResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetPublicationsByAuthorResponse response = new GetPublicationsByAuthorResponse();
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public List<GetPublicationsByTopicResponse> getPublicationsByTopic(String topicId) {
        List<Publication> publications = publicationRepository.findAllByTopicDto_TopicId(topicId);
        if (publications.isEmpty()) {
            throw new EntityNotFoundException("Publication not found with topicId " + topicId);
        }
        List<GetPublicationsByTopicResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetPublicationsByTopicResponse response = new GetPublicationsByTopicResponse();
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public GetPublicationResponse getPublication(String publicationId) {
        Publication publication = publicationRepository.findByPublicationId(publicationId);
        if (publication == null) {
            throw new EntityNotFoundException("Publication not found with id " + publicationId);
        }
        GetPublicationResponse response = new GetPublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }

    @Override
    public List<SearchPublicationResponse> searchPublication(String searchKey, SearchTypeEnum searchType) {
        List<Publication> publications = new ArrayList<>();
        switch (searchType) {
            case TITLE:
                publications = publicationRepository.findAllByPublicationTitleContaining(searchKey);
                if(publications.isEmpty()) {
                    throw new EntityNotFoundException("Publication not found with title " + searchKey);
                }
                break;
            case TOPIC:
                publications = publicationRepository.findAllByTopicDto_TopicNameContaining(searchKey);
                if(publications.isEmpty()) {
                    throw new EntityNotFoundException("Publication not found with topic " + searchKey);
                }
                break;
            case AUTHOR:
                publications = publicationRepository.findAllByAuthorDto_AuthorNameContaining(searchKey);
                if(publications.isEmpty()) {
                    throw new EntityNotFoundException("Publication not found with author " + searchKey);
                }
                break;
        }
        List<SearchPublicationResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            SearchPublicationResponse response = new SearchPublicationResponse();
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public List<GetAllPublicationResponse> getAllPublications() {
        List<Publication> publications = publicationRepository.findAll();
        if (publications.isEmpty()) {
            throw new EntityNotFoundException("No Publication in database");
        }
        List<GetAllPublicationResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetAllPublicationResponse response = new GetAllPublicationResponse();
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public PostPublicationResponse postPublication(PostPublicationRequest request) throws URISyntaxException, SAXException, IOException, JAXBException {
        // Text Preprocessing
        String concatText = textPreprocessingUtil.concatAndLowercaseText(request.getPublicationTitle(),
                request.getPublicationAbstract(), request.getPublicationKeyword());
        String symbolRemovedText = textPreprocessingUtil.symbolRemover(concatText);
        String lemmatizedText = textPreprocessingUtil.lemmatizer(symbolRemovedText);

        // ML Model to get Classification
        Map<String, ?> predictProbability = mlModelUtil.predictText(lemmatizedText);
        Integer topicWithHighestProbability = (Integer) predictProbability.get("Label");
        List<Integer> predictResults = new ArrayList<>();
        predictResults.add(topicWithHighestProbability);

        // Get Classification Report
        ClassificationReportDto classificationReportDto = ClassificationReportDto.builder()
                .concatText(concatText)
                .symbolRemovedText(symbolRemovedText)
                .lemmatizedText(lemmatizedText)
                .build();

        // Build
        Classification classification = Classification.builder()
                .classificationId(idGeneratorUtil.generateId(IdGeneratorEnum.CLASSIFICATION))
                .classificationReport(classificationReportDto)
                .predictProbability(predictProbability)
                .predictResults(predictResults)
                .classificationCreatedAt(LocalDateTime.now())
                .classificationLastUpdated(LocalDateTime.now())
                .build();

        // Get Author Ids and Dtos
        List<AuthorDto> authorDtos = new ArrayList<>();
        request.getAuthorIds().forEach(id -> {
            Author author = authorRepository.findByAuthorId(id);
            if (author == null) {
                throw new EntityNotFoundException("Author not found with id " + id);
            }
            AuthorDto authorDto = new AuthorDto();
            BeanUtils.copyProperties(author, authorDto);
            authorDtos.add(authorDto);
        });

        // Get Topic Ids and Dtos
        List<TopicDto> topicDtos = new ArrayList<>();
        predictResults.forEach(integer -> {
            Topic topic = topicRepository.findByTopicLabel(integer);
            if (topic == null) {
                throw new EntityNotFoundException("Topic not found with label " + integer);
            }
            TopicDto topicDto = new TopicDto();
            BeanUtils.copyProperties(topic, topicDto);
            topicDtos.add(topicDto);
        });

        // Get Classification Dto
        ClassificationDto classificationDto = new ClassificationDto();
        BeanUtils.copyProperties(classification, classificationDto);

        // Upload Publication
        String publicationPath = " ";

        // Build Publication
        Publication publication = Publication.builder()
                .publicationId(idGeneratorUtil.generateId(IdGeneratorEnum.PUBLICATION))
                .publicationPath(publicationPath)
                .classificationDto(classificationDto)
                .authorDto(authorDtos)
                .topicDto(topicDtos)
                .publicationCreatedAt(LocalDateTime.now())
                .publicationLastUpdated(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(request, publication);

        // Save the Publication and Classification
        Publication savedPublication = publicationRepository.save(publication);
        classificationRepository.save(classification);
        PostPublicationResponse response = new PostPublicationResponse();

        // Build and Return Response
        BeanUtils.copyProperties(savedPublication, response);
        return response;
    }

    @Override
    public UploadPublicationResponse uploadPublication(String publicationId, MultipartFile file) throws IOException, URISyntaxException {
        Publication publication = publicationRepository.findByPublicationId(publicationId);
        if (publication == null) {
            throw new EntityNotFoundException("Publication not found with id " + publicationId);
        }
        publication.setPublicationPath(fileUploadUtil.uploadFile(publicationId, file));
        publicationRepository.save(publication);
        return UploadPublicationResponse.builder()
                .message("Success")
                .publicationId(publicationId)
                .publicationPath(publication.getPublicationPath())
                .build();
    }

    @Override
    public UpdatePublicationResponse updatePublication(UpdatePublicationRequest request) {
        Publication publication = publicationRepository.findByPublicationId(request.getPublicationId());
        if (publication == null) {
            throw new EntityNotFoundException("Publication not found with id " + request.getPublicationId());
        }
        BeanUtils.copyProperties(request, publication);
        publication.setPublicationLastUpdated(LocalDateTime.now());
        publicationRepository.save(publication);
        UpdatePublicationResponse response = new UpdatePublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }
}
