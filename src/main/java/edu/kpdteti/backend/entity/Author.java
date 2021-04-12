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
@Table(name = Author.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = Author.AUTHOR_ID),
        @UniqueConstraint(columnNames = Author.AUTHOR_EMAIl)
})
public class Author {

    public static final String TABLE_NAME = "authors";
    public static final String AUTHOR_ID = "id";
    public static final String AUTHOR_NAME = "name";
    public static final String AUTHOR_EMAIl = "email";
    public static final String AUTHOR_FACULTY = "faculty";
    public static final String AUTHOR_UNIVERSITY = "university";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";
    public static final String PUBLICATION_ID = "id";
    public static final String TABLE_AUTHOR_PUBLICATION = "authors_publications";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = AUTHOR_ID)
    private Long authorId;

    @Column(name = AUTHOR_NAME)
    private String authorName;

    @Column(name = AUTHOR_EMAIl)
    private String authorEmail;

    @Column(name = AUTHOR_FACULTY)
    private String authorFaculty;

    @Column(name = AUTHOR_UNIVERSITY)
    private String authorUniversity;

    @Column(name = CREATED_AT)
    private LocalDateTime authorCreatedAt;

    @Column(name = LAST_UPDATED)
    private LocalDateTime authorLastUpdated;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = TABLE_AUTHOR_PUBLICATION,
        joinColumns = {
            @JoinColumn(name = "author_id", referencedColumnName = AUTHOR_ID,
                nullable = false, updatable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "publication_id", referencedColumnName = PUBLICATION_ID,
                nullable = false, updatable = false)})
    private Set<Publication> publications = new HashSet<>();

}
