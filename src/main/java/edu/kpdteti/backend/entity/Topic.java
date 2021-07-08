package edu.kpdteti.backend.entity;

import edu.kpdteti.backend.entity.dto.TopicParentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Topic.COLLECTION_NAME)
public class Topic {

    public static final String COLLECTION_NAME = "topic";
    public static final String TOPIC_ID = "id";
    public static final String TOPIC_PARENT = "topicParent";
    public static final String TOPIC_NAME = "name";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    @Field(value = TOPIC_ID)
    private String topicId;

    @Field(value = TOPIC_NAME)
    private String topicName;

    @Field(value = TOPIC_PARENT)
    private TopicParentDto topicParentDto;

    @Field(value = CREATED_AT)
    private LocalDateTime topicCreatedAt;

    @Field(value = LAST_UPDATED)
    private LocalDateTime topicLastUpdated;

}
