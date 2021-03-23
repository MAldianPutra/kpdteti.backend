package edu.kpdteti.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = Publication.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = Publication.PUBLICATION_ID)
})
public class Publication {

    public static final String TABLE_NAME = "publications";
    public static final String PUBLICATION_ID = "id";
    public static final String USER_ID = "userId";
    public static final String PUBLICATION_DATE = "publicationDate";
    public static final String PUBLICATION_PUBLISHER = "publisher";
    public static final String PUBLICATION_DESCRIPTION = "description";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = PUBLICATION_ID)
    private Long publicationId;

    @Column(name = PUBLICATION_DATE)
    private String publicationDate;

    @Column(name = PUBLICATION_PUBLISHER)
    private String publicationPublisher;

    @Column(name = PUBLICATION_DESCRIPTION)
    private String publicationDescription;

    @Column(name = CREATED_AT)
    private String publicationCreatedAt;

    @Column(name = LAST_UPDATED)
    private String publicationLastUpdated;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_ID)
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "publication", fetch = FetchType.LAZY)
    private Classification classification;

    @JsonIgnore
    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private Set<PublicationTopic> publicationTopics;

    @JsonIgnore
    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private Set<PublicationAuthor> publicationAuthors;


}
