package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Classification;
import edu.kpdteti.backend.entity.Publication;
import edu.kpdteti.backend.enums.SearchTypeEnum;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;
import edu.kpdteti.backend.repository.ClassificationRepository;
import edu.kpdteti.backend.repository.PublicationRepository;
import edu.kpdteti.backend.service.PublicationService;
import edu.kpdteti.backend.util.FileUploadUtil;
import edu.kpdteti.backend.util.PostPublicationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
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

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final ClassificationRepository classificationRepository;
    private final FileUploadUtil fileUploadUtil;
    private final PostPublicationUtil postPublicationUtil;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository,
                                  ClassificationRepository classificationRepository,
                                  FileUploadUtil fileUploadUtil, PostPublicationUtil postPublicationUtil) {
        this.publicationRepository = publicationRepository;
        this.classificationRepository = classificationRepository;
        this.fileUploadUtil = fileUploadUtil;
        this.postPublicationUtil = postPublicationUtil;
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
        if (publication.getPublicationPath() == null) {
            throw new EntityNotFoundException("File not found with id " + publicationId);
        }
        return DownloadPublicationResponse.builder()
                .publicationPath(publication.getPublicationPath())
                .publicationTitle(publication.getPublicationTitle() + ".pdf")
                .build();
    }

    @Override
    public List<GetPublicationsByAuthorResponse> getPublicationsByAuthor(String authorId, Integer page) {
        Page<Publication> publications = publicationRepository.findAllByAuthorDto_AuthorId(authorId, PageRequest.of(page, 10, Sort.by("publicationTitle").ascending()));
        if (publications.isEmpty()) {
            throw new EntityNotFoundException("Publication not found with authorId " + authorId);
        }
        Integer numberOfPage = publications.getTotalPages();
        List<GetPublicationsByAuthorResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetPublicationsByAuthorResponse response = new GetPublicationsByAuthorResponse();
            response.setNumberOfPage(numberOfPage);
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public List<GetPublicationsByTopicResponse> getPublicationsByTopic(String topicId, Integer page) {
        Page<Publication> publications = publicationRepository.findAllByTopicDto_TopicId(topicId, PageRequest.of(page, 10, Sort.by("publicationTitle").ascending()));
        if (publications.isEmpty()) {
            throw new EntityNotFoundException("Publication not found with topicId " + topicId);
        }
        Integer numberOfPage = publications.getTotalPages();
        List<GetPublicationsByTopicResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetPublicationsByTopicResponse response = new GetPublicationsByTopicResponse();
            response.setNumberOfPage(numberOfPage);
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
    public List<SearchPublicationResponse> searchPublication(String searchKey, SearchTypeEnum searchType, Integer page) {
        List<Publication> publications = new ArrayList<>();
        Page<Publication> publicationsPage = new PageImpl<>(publications);
        switch (searchType) {
            case TITLE:
                publicationsPage = publicationRepository.findAllByPublicationTitleContaining(searchKey, PageRequest.of(page, 10, Sort.by("publicationTitle").ascending()));
                if (publicationsPage.isEmpty()) {
                    throw new EntityNotFoundException("Publication not found with title " + searchKey);
                }
                break;
            case TOPIC:
                publicationsPage = publicationRepository.findAllByTopicDto_TopicNameContaining(searchKey, PageRequest.of(page, 10, Sort.by("publicationTitle").ascending()));
                if (publicationsPage.isEmpty()) {
                    throw new EntityNotFoundException("Publication not found with topic " + searchKey);
                }
                break;
            case AUTHOR:
                publicationsPage = publicationRepository.findAllByAuthorDto_AuthorNameContaining(searchKey, PageRequest.of(page, 10, Sort.by("publicationTitle").ascending()));
                if (publicationsPage.isEmpty()) {
                    throw new EntityNotFoundException("Publication not found with author " + searchKey);
                }
                break;
        }
        Integer numberOfPage = publicationsPage.getTotalPages();
        List<SearchPublicationResponse> responses = new ArrayList<>();
        publicationsPage.forEach(publication -> {
            SearchPublicationResponse response = new SearchPublicationResponse();
            response.setNumberOfPage(numberOfPage);
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public List<GetAllPublicationResponse> getAllPublications(Integer page) {
        Page<Publication> publications = publicationRepository.findAll(PageRequest.of(page, 20, Sort.by("publicationTitle").ascending()));
        if (publications.isEmpty()) {
            throw new EntityNotFoundException("No Publication in database");
        }
        Integer numberOfPage = publications.getTotalPages();
        List<GetAllPublicationResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetAllPublicationResponse response = new GetAllPublicationResponse();
            response.setNumberOfPage(numberOfPage);
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public PostPublicationResponse postPublication(PostPublicationRequest request) throws URISyntaxException, SAXException, IOException, JAXBException {
        Pair<Publication, Classification> postPublicationUtilResponse = postPublicationUtil.postPublication(request);
        Publication savedPublication = publicationRepository.save(postPublicationUtilResponse.getFirst());
        classificationRepository.save(postPublicationUtilResponse.getSecond());
        PostPublicationResponse response = new PostPublicationResponse();
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
