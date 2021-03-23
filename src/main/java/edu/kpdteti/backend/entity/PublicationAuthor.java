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
@Table(name = PublicationAuthor.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = PublicationAuthor.PUBLICATION_AUTHOR_ID)
})
public class PublicationAuthor {

    public static final String TABLE_NAME = "publicationAuthors";
    public static final String PUBLICATION_AUTHOR_ID = "id";
    public static final String PUBLICATION_ID = "publicationId";
    public static final String AUTHOR_ID = "authorId";
    public static final String CREATED_AT = "createdAt";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = PUBLICATION_AUTHOR_ID)
    private Long publicationAuthorId;

    @Column(name = CREATED_AT)
    private LocalDateTime publicationAuthorCreatedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PUBLICATION_ID)
    private Publication publication;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = AUTHOR_ID)
    private Author author;

}
