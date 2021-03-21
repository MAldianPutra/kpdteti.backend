package edu.kpdteti.backend.entity.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = Topic.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = Topic.TOPIC_ID),
        @UniqueConstraint(columnNames = Topic.TOPIC_NAME)
})
public class Topic {

    public static final String TABLE_NAME = "topics";
    public static final String TOPIC_ID = "id";
    public static final String TOPIC_PARENT_ID = "parentId";
    public static final String TOPIC_NAME = "name";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = TOPIC_ID)
    private Long topicId;

    @Column(name = TOPIC_NAME)
    private String topicName;

    @Column(name = CREATED_AT)
    private LocalDateTime topicCreatedAt;

    @Column(name = LAST_UPDATED)
    private LocalDateTime topicLastUpdated;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TOPIC_PARENT_ID)
    private TopicParent topicParent;

    @JsonIgnore
    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private Set<PublicationTopic> publicationTopics;

}
