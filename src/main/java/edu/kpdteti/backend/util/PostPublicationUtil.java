package edu.kpdteti.backend.util;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Classification;
import edu.kpdteti.backend.entity.Publication;
import edu.kpdteti.backend.entity.Topic;
import edu.kpdteti.backend.entity.dto.AuthorDto;
import edu.kpdteti.backend.entity.dto.ClassificationDto;
import edu.kpdteti.backend.entity.dto.ClassificationReportDto;
import edu.kpdteti.backend.entity.dto.TopicDto;
import edu.kpdteti.backend.enums.IdGeneratorEnum;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.repository.AuthorRepository;
import edu.kpdteti.backend.repository.TopicRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostPublicationUtil {

    private final IdGeneratorUtil idGeneratorUtil;
    private final MLModelUtil mlModelUtil;
    private final TextPreprocessingUtil textPreprocessingUtil;
    private final AuthorRepository authorRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public PostPublicationUtil(IdGeneratorUtil idGeneratorUtil, MLModelUtil mlModelUtil,
                               TextPreprocessingUtil textPreprocessingUtil,
                               AuthorRepository authorRepository,
                               TopicRepository topicRepository) {
        this.idGeneratorUtil = idGeneratorUtil;
        this.mlModelUtil = mlModelUtil;
        this.textPreprocessingUtil = textPreprocessingUtil;
        this.authorRepository = authorRepository;
        this.topicRepository = topicRepository;
    }

    public Pair<Publication, Classification> postPublication(PostPublicationRequest request) throws URISyntaxException, SAXException, IOException, JAXBException {
        // Text Preprocessing
        String concatText = textPreprocessingUtil.concatAndLowercaseText(request.getPublicationTitle(),
                request.getPublicationAbstract(), request.getPublicationKeyword());
        String symbolRemovedText = textPreprocessingUtil.symbolRemover(concatText);
        String lemmatizedText = textPreprocessingUtil.lemmatizer(symbolRemovedText);

        // ML Model to get Classification
        Map<String, ?> predictProbability = mlModelUtil.predictText(lemmatizedText);

        // Get Topic with Highest Probability
        Integer topicWithHighestProbability = (Integer) predictProbability.get("Label");
        List<Integer> predictResults = new ArrayList<>();
        predictResults.add(topicWithHighestProbability);

        // Get TopicProbability
        DecimalFormat decimalFormatter = new DecimalFormat("#.####");
        List<String> probability = new ArrayList<>();
        predictProbability.forEach(
                (key, value) -> probability.add(decimalFormatter.format(value)));

        Map<String, String> topicProbability = new HashMap<>();
        topicProbability.put("Computer System Organization", probability.get(1));
        topicProbability.put("Networks", probability.get(2));
        topicProbability.put("Software and its Engineering", probability.get(3));
        topicProbability.put("Theory of Computation", probability.get(4));
        topicProbability.put("Mathematics of Computing", probability.get(5));
        topicProbability.put("Information System", probability.get(6));
        topicProbability.put("Security and Privacy", probability.get(7));
        topicProbability.put("Human-centered Computing", probability.get(8));
        topicProbability.put("Computing Methodologies", probability.get(9));
        topicProbability.put("Applied Computing", probability.get(10));

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
                .topicProbability(topicProbability)
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

        return Pair.of(publication, classification);

    }

}
