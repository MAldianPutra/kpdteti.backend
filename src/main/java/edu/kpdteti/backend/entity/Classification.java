package edu.kpdteti.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = Topic.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = Classification.CLASSIFICATION_ID),
        @UniqueConstraint(columnNames = Classification.PUBLICATION_ID)
})
public class Classification {

    public static final String TABLE_NAME = "classifications";
    public static final String CLASSIFICATION_ID = "id";
    public static final String PUBLICATION_ID = "publicationId";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CLASSIFICATION_ID)
    private Long classificationId;

    @Column(name = CREATED_AT)
    private LocalDateTime classificationCreatedAt;

    @Column(name = LAST_UPDATED)
    private LocalDateTime classificationLastUpdated;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PUBLICATION_ID)
    private Publication publication;

}
