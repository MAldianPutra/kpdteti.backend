package edu.kpdteti.backend.entity;

import edu.kpdteti.backend.entity.dto.AuthorDto;
import edu.kpdteti.backend.entity.dto.ClassificationDto;
import edu.kpdteti.backend.entity.dto.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Publication.COLLECTION_NAME)
public class Publication {

    public static final String COLLECTION_NAME = "publications";
    public static final String USER_ID = "userId";
    public static final String CLASSIFICATION = "classification";
    public static final String AUTHOR = "author";
    public static final String TOPIC = "topic";
    public static final String PUBLICATION_TITLE = "publicationTitle";
    public static final String PUBLICATION_DATE = "publicationDate";
    public static final String PUBLICATION_PUBLISHER = "publisher";
    public static final String PUBLICATION_ABSTRACT = "abstract";
    public static final String PUBLICATION_KEYWORD = "keyword";
    public static final String OTHER_AUTHOR = "otherAuthor";
    public static final String PUBLICATION_PATH = "publicationPath";
    public static final String SAVED_BOOL = "savedBoolean";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    private String publicationId;

    @Field(value = USER_ID)
    private String userId;

    @Field(value = CLASSIFICATION)
    private ClassificationDto classificationDto;

    @Field(value = AUTHOR)
    private List<AuthorDto> authorDto;

    @Field(value = TOPIC)
    private List<TopicDto> topicDto;

    @Field(value = PUBLICATION_TITLE)
    @Indexed(unique = true)
    private String publicationTitle;

    @Field(value = PUBLICATION_DATE)
    private String publicationDate;

    @Field(value = PUBLICATION_PUBLISHER)
    private String publicationPublisher;

    @Field(value = PUBLICATION_ABSTRACT)
    private String publicationAbstract;

    @Field(value = PUBLICATION_KEYWORD)
    private String publicationKeyword;

    @Field(value = OTHER_AUTHOR)
    private List<String> otherAuthors;

    @Field(value = PUBLICATION_PATH)
    private String publicationPath;

    @Field(value = SAVED_BOOL)
    private Boolean savePDF;

    @Field(value = CREATED_AT)
    private LocalDateTime publicationCreatedAt;

    @Field(value = LAST_UPDATED)
    private LocalDateTime publicationLastUpdated;

}
