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

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<PublicationAuthor> publicationAuthors;

}
