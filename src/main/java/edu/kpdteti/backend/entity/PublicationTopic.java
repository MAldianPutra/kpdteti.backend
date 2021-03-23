package edu.kpdteti.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = PublicationTopic.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = PublicationTopic.PUBLICATION_ID)
})
public class PublicationTopic {

    public static final String TABLE_NAME = "publicationTopics";
    public static final String PUBLICATION_TOPIC_ID = "id";
    public static final String PUBLICATION_ID = "publicationId";
    public static final String TOPIC_ID = "topicId";
    public static final String PERCENTAGE = "percentage";
    public static final String CREATED_AT = "createdAt";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = PUBLICATION_TOPIC_ID)
    private Long publicationTopicId;

    @Column(name = PERCENTAGE)
    private Float percentage;

    @Column(name = CREATED_AT)
    private LocalDateTime publicationTopicCreatedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PUBLICATION_ID)
    private Publication publication;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TOPIC_ID)
    private Topic topic;

}
