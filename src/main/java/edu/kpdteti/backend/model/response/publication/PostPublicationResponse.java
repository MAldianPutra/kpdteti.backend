package edu.kpdteti.backend.model.response.publication;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPublicationResponse {

    private Long publicationId;
    private String publicationTitle;
    private String publicationDate;
    private String publicationDescription;
    private String otherAuthor;
    private Set<Topic> topics;
    private Set<Author> authors;

}
