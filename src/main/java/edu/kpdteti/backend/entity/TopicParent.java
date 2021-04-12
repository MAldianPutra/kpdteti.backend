package edu.kpdteti.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = TopicParent.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = TopicParent.TOPIC_PARENT_ID),
        @UniqueConstraint(columnNames = TopicParent.TOPIC_PARENT_NAME)
})
public class TopicParent {

    public static final String TABLE_NAME = "topicParents";
    public static final String TOPIC_PARENT_ID = "id";
    public static final String TOPIC_PARENT_NAME = "name";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = TOPIC_PARENT_ID)
    private Long topicParentId;

    @Column(name = TOPIC_PARENT_NAME)
    private String topicParentName;

    @Column(name = CREATED_AT)
    private LocalDateTime topicParentCreatedAt;

    @Column(name = LAST_UPDATED)
    private LocalDateTime topicParentLastUpdated;

    @JsonIgnore
    @OneToMany(mappedBy = "topicParent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Topic> topics;

}
