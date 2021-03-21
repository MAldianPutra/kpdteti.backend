package edu.kpdteti.backend.entity.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
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
