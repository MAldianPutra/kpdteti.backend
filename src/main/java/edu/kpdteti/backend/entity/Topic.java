package edu.kpdteti.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    public static final String TOPIC_PARENT_ID = "topicParentId";
    public static final String TOPIC_NAME = "name";
    public static final String TOPIC_LABEL = "label";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    private String topicId;

    @Field(value = TOPIC_PARENT_ID)
    private String topicParentId;

    @Field(value = TOPIC_NAME)
    @Indexed(unique = true)
    private String topicName;

    @Field(value = TOPIC_LABEL)
    @Indexed(unique = true)
    private Integer topicLabel;

    @Field(value = CREATED_AT)
    private LocalDateTime topicCreatedAt;

    @Field(value = LAST_UPDATED)
    private LocalDateTime topicLastUpdated;

}
