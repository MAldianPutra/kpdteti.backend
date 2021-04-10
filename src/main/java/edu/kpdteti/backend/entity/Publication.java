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
@Table(name = Publication.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = Publication.PUBLICATION_ID)
})
public class Publication {

    public static final String TABLE_NAME = "publications";
    public static final String PUBLICATION_ID = "id";
    public static final String USER_ID = "userId";
    public static final String PUBLICATION_TITLE = "publicationTitle";
    public static final String PUBLICATION_DATE = "publicationDate";
    public static final String PUBLICATION_PUBLISHER = "publisher";
    public static final String PUBLICATION_DESCRIPTION = "description";
    public static final String OTHER_AUTHOR = "otherAuthor";
    public static final String PUBLICATION_PATH = "publicationPath";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = PUBLICATION_ID)
    private Long publicationId;

    @Column(name = PUBLICATION_TITLE)
    private String publicationTitle;

    @Column(name = PUBLICATION_DATE)
    private String publicationDate;

    @Column(name = PUBLICATION_PUBLISHER)
    private String publicationPublisher;

    @Column(name = PUBLICATION_DESCRIPTION)
    private String publicationDescription;

    @Column(name = OTHER_AUTHOR)
    private String otherAuthor;

    @Column(name = PUBLICATION_PATH)
    private String publicationPath;

    @Column(name = CREATED_AT)
    private LocalDateTime publicationCreatedAt;

    @Column(name = LAST_UPDATED)
    private LocalDateTime publicationLastUpdated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = USER_ID, nullable = false)
    private User user;

    @OneToOne(mappedBy = "publication", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Classification classification;

    @ManyToMany(mappedBy = "publications", fetch = FetchType.LAZY)
    private Set<Topic> topics;

    @ManyToMany(mappedBy = "publications", fetch = FetchType.LAZY)
    private Set<Author> authors;


}
