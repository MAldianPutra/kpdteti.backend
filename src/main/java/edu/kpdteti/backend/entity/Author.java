package edu.kpdteti.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Author.COLLECTION_NAME)
public class Author {

    public static final String COLLECTION_NAME = "authors";
    public static final String AUTHOR_NAME = "name";
    public static final String AUTHOR_EMAIl = "email";
    public static final String AUTHOR_FACULTY = "faculty";
    public static final String AUTHOR_UNIVERSITY = "university";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    private String authorId;

    @Field(value = AUTHOR_NAME)
    @Indexed(unique = true)
    private String authorName;

    @Field(value = AUTHOR_EMAIl)
    @Indexed(unique = true)
    private String authorEmail;

    @Field(value = AUTHOR_FACULTY)
    private String authorFaculty;

    @Field(value = AUTHOR_UNIVERSITY)
    private String authorUniversity;

    @Field(value = CREATED_AT)
    private LocalDateTime authorCreatedAt;

    @Field(value = LAST_UPDATED)
    private LocalDateTime authorLastUpdated;

}
