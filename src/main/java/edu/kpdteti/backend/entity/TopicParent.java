package edu.kpdteti.backend.entity;

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
@Document(collection = TopicParent.COLLECTION_NAME)
public class TopicParent {

    public static final String COLLECTION_NAME = "topicParents";
    public static final String TOPIC_PARENT_ID = "id";
    public static final String TOPIC_PARENT_NAME = "name";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    @Field(value = TOPIC_PARENT_ID)
    private String topicParentId;

    @Field(value = TOPIC_PARENT_NAME)
    private String topicParentName;

    @Field(value = CREATED_AT)
    private LocalDateTime topicParentCreatedAt;

    @Field(value = LAST_UPDATED)
    private LocalDateTime topicParentLastUpdated;


}
