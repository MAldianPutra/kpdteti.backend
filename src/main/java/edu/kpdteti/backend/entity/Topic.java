package edu.kpdteti.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    public static final String PUBLICATION_ID = "id";
    public static final String TABLE_TOPIC_PUBLICATION = "topics_publications";

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = TOPIC_PARENT_ID, nullable = false)
    private TopicParent topicParent;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = TABLE_TOPIC_PUBLICATION,
            joinColumns = {
                    @JoinColumn(name = "topic_id", referencedColumnName = TOPIC_ID,
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "publication_id", referencedColumnName = PUBLICATION_ID,
                            nullable = false, updatable = false)})
    private Set<Publication> publications = new HashSet<>();

}
